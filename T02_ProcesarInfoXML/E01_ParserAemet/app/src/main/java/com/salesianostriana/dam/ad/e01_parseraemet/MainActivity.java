package com.salesianostriana.dam.ad.e01_parseraemet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.salesianostriana.dam.ad.e01_parseraemet.models.Root;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemPronostico> dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Serializer serializer = new Persister();

        Root prediccion = new Root();

        try {
            prediccion = serializer.read(Root.class,getResources().openRawResource(R.raw.forecast));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("OUTPUT", prediccion.toString());

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dias = new ArrayList();

        //Recogida de datos (8 dias en pronóstico)
        /*
        for (int i = 0; i < 8; i++){
            String fecha =
        }*/

        mAdapter = new AdapterPronostico(dias);
        mRecyclerView.setAdapter(mAdapter);

    }

}
