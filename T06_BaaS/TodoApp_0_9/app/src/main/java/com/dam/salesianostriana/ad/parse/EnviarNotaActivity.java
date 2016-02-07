package com.dam.salesianostriana.ad.parse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

import java.util.Date;

public class EnviarNotaActivity extends AppCompatActivity {

    EditText nuevoConcepto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_nota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nuevoConcepto = (EditText) findViewById(R.id.nuevo_concepto);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast tostada = Toast.makeText(EnviarNotaActivity.this, "Nueva nota creada", Toast.LENGTH_SHORT);
                tostada.setGravity(Gravity.BOTTOM | Gravity.LEFT, 24, 24);
                tostada.show();

                // Creación de una nota
                ParseObject nuevaNota = new ParseObject("Todo");

                nuevaNota.put("Concepto", nuevoConcepto.getText().toString());
                nuevaNota.put("Fecha", new Date());

                nuevaNota.saveInBackground();

                Intent i = new Intent(EnviarNotaActivity.this,MainActivity.class);
                startActivity(i);
                EnviarNotaActivity.this.finish();
            }
        });
    }

}
