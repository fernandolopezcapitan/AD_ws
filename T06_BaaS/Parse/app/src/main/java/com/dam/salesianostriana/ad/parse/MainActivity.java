package com.dam.salesianostriana.ad.parse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    List<Notas> notas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        lista = (ListView) findViewById(R.id.listView);
//
//        ArrayAdapter<Notas> adaptador = new ArrayAdapter<Notas>(this,
//                android.R.layout.simple_list_item_1,notas);
//
//        lista.setAdapter(adaptador);
//
//        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "Seleccionado: " + notas.get(position),
//                        Toast.LENGTH_SHORT).show();
//
//                //view.animate().setDuration(1000).rotationX(360);
//            }
//        });


//        // Código de almacenamiento de un objeto
//        // Instanciamos un ParseObject("Todo")
//        ParseObject object = new ParseObject("Todo");
//
//        // Asignamos valores
//        object.put("Fecha", new Date());
//        object.put("Concepto","Tomar un café");
//
//        // Almacenamos el objeto en la nube, usando un hilo secundario
//        object.saveInBackground();

        // Código de RECUPERACIÓN DE UN OBJETO
        // si es de un objeto, get(objectId)
        // si es de una lista de objeto, find()
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Todo");
//        query.getInBackground("Dxq4MUzQ1b", new GetCallback<ParseObject>() {
//            public void done(ParseObject object, ParseException e) {
//                if (e == null) {
//                    // object will be your game score
//                    Log.d("FECHA", object.get("Fecha").toString());
//                    Log.d("CONCEPTO", object.get("Concepto").toString());
//                } else {
//                    // something went wrong
//                    Log.e("ERROR",Integer.toString(e.getCode()));
//                }
//            }
//        });

        // Codigo de recuperación de todos los objetos de una misma clase
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Todo");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null) {
//                    for (ParseObject object : objects) {
//                        Log.d("OBJECTID", object.getObjectId());
//                        Log.d("FECHA", object.get("Fecha").toString());
//                        Log.d("CONCEPTO", object.get("Concepto").toString());
//                        //notas.add("OBJECTID: "+object.getObjectId()+"\nFECHA: "+object.get("Fecha").toString()+"\nCONCEPTO: "+object.get("Concepto").toString());
//                    }
//
//                } else {
//
//                    Log.e("ERROR",Integer.toString(e.getCode()));
//                }
//            }
//        });

        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this, "Todo");
        adapter.setTextKey("Fecha");
        adapter.setTextKey("Concepto");
        //adapter.setImageKey("photo");

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);







    }

// Hacer una clase adaptador



    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(this, R.layout.activity_main, null);
        }

        // Take advantage of ParseQueryAdapter's getItemView logic for
        // populating the main TextView/ImageView.
        // The IDs in your custom layout must match what ParseQueryAdapter expects
        // if it will be populating a TextView or ImageView for you.
        super.getItemView(object, v, parent);

        // Do additional configuration before returning the View.
        TextView descriptionView = (TextView) v.findViewById(R.id.listView);
        descriptionView.setText(object.getString("Concepto"));
        return v;
    }
}
