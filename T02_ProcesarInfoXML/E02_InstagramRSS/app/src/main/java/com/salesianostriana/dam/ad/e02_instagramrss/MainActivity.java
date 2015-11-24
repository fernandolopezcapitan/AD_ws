package com.salesianostriana.dam.ad.e02_instagramrss;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.salesianostriana.dam.ad.e02_instagramrss.pojos.ItemImagen;
import com.salesianostriana.dam.ad.e02_instagramrss.pojos.Rss;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * PASO 1: Importar librerías necesarias:
 compile 'com.android.support:appcompat-v7:23.1.1'
 compile 'com.android.support:design:23.1.1'
 compile('org.simpleframework:simple-xml:2.7.1') {
 exclude group: 'stax', module: 'stax-api'
 exclude group: 'stax', module: 'stax'
 exclude group: 'xpp3', module: 'xpp3'
 }
 compile 'com.squareup.picasso:picasso:2.5.2'
 compile 'com.android.support:recyclerview-v7:23.1.0'
 compile 'com.android.support:cardview-v7:23.1.0'

    PASO 2: Añadir permisos de internet en manifest

    PASO 3: Editar layouts: activity_main

    PASO 4: Obtener xml de url, editar pojos

    PASO 5: Crear y editar list_item_recycler

    PASO 6: Crear clase adaptador

    PASO 7: Configurar MainActivity
            - Llamar en el manejador del boton a un AsyncTask, pasarle la ciudad, devolverá una clase Rss
            - Leer la url y llamar al serializer
            - onPostExecuter: cargar el arrayList con un bucle for y meterlo en el adaptador
 */

public class MainActivity extends AppCompatActivity {

    private EditText ciudad;
    private Button buscar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemImagen> imagenArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ciudad = (EditText) findViewById(R.id.et_ciudad);
        buscar = (Button) findViewById(R.id.btn_Search);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetImagesTask().execute(ciudad.getText().toString());
            }
        });
    }

    private class GetImagesTask extends AsyncTask<String,Void,Rss>{
        @Override
        protected Rss doInBackground(String... params) {
            Rss rss = null;
            Serializer serializer = new Persister();
            /**
             * Serializer serializer = new Persister();
             try {

             URL url = new URL("http://www.aemet.es/xml/municipios/localidad_41091.xml");
             InputStream in = url.openStream();
             Tiempo r = serializer.read(Tiempo.class,in,false);

             Log.i("Root",r.toString());
             */
            try {
                URL url = new URL("http://iconosquare.com/tagFeed/"+params[0]);
                InputStream is = url.openStream();
                rss = serializer.read(Rss.class,is,false);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return rss;
        }

        @Override
        protected void onPostExecute(Rss rss) {
            super.onPostExecute(rss);

            imagenArrayList = new ArrayList<>();

            for(int i = 0; i <rss.getChannel().getItem().size(); i++){
                String imagen = rss.getChannel().getItem().get(i).getDescripcion();
                String autor = rss.getChannel().getItem().get(i).getAutor();
                String fecha = rss.getChannel().getItem().get(i).getPubdate();

                imagenArrayList.add(new ItemImagen(imagen, autor, fecha));
            }

            mAdapter = new XmlAdapter(MainActivity.this, imagenArrayList);
            mRecyclerView.setAdapter(mAdapter);

            Log.i("tamaño rss", rss.getChannel().getItem().get(0).getDescripcion());
            Log.i("CHANNEL", rss.getChannel().getItem().get(0).getDescripcion());
        }
    }
}
