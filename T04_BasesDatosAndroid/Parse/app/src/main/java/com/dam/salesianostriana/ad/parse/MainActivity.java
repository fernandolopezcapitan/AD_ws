package com.dam.salesianostriana.ad.parse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


    }
}
