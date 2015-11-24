package com.salesianostriana.dam.ad.lecturaformatoespecial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.DataInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    int magico, ancho, alto;
    String comentarios;
    byte gris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Para leer los otros ficheros basta con cambiar "fichero1" por el nombre del fichero a leer
        DataInputStream dis = new DataInputStream(getResources().openRawResource(R.raw.fichero4));

        try {
            while ((magico=dis.readInt())!=0){
                Log.i("Lectura","Lectura de ficheros");
                comentarios = dis.readUTF();
                ancho = dis.readInt();
                alto = dis.readInt();
                gris = dis.readByte();
                Log.i("Fichero","Código mágico "+magico+"" +
                        "\nComentarios "+comentarios+"" +
                        "\nAncho de la imagen en pixels "+ancho+ "" +
                        "\nAlto de la imagen en pixels "+alto+ "" +
                        "\nNivel de gris "+gris);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
