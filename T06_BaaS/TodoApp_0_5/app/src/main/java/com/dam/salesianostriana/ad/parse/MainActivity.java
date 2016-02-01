package com.dam.salesianostriana.ad.parse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listaNotas;
    List<Notas> notas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNotas = (ListView) findViewById(R.id.listView);

        // Código de RECUPERACIÓN DE UN OBJETO
        // si es de un objeto, get(objectId)
        // si es de una listaNotas de objeto, find()
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Todo");
        query.getInBackground("Dxq4MUzQ1b", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your game score
                    Log.d("FECHA", object.get("Fecha").toString());
                    Log.d("CONCEPTO", object.get("Concepto").toString());
                } else {
                    // something went wrong
                    Log.e("ERROR",Integer.toString(e.getCode()));
                }
           }
        });

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

        //ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this, "Todo");
        //adapter.setTextKey("Fecha");
        //adapter.setTextKey("Concepto");
        //adapter.setImageKey("photo");

        NotasAdapter notasAdapter = new NotasAdapter(this,"Todo");
        listaNotas.setAdapter(notasAdapter);

        //notasAdapter.notifyDataSetChanged();

    }

}
