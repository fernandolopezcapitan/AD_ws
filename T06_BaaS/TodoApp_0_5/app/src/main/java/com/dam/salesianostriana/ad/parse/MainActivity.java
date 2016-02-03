package com.dam.salesianostriana.ad.parse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNotas = (ListView) findViewById(R.id.listView);

        ParseQueryAdapter notasAdapter = new ParseQueryAdapter(this,"Todo");
        listaNotas.setAdapter(notasAdapter);

        //notasAdapter.notifyDataSetChanged();

    }

}
