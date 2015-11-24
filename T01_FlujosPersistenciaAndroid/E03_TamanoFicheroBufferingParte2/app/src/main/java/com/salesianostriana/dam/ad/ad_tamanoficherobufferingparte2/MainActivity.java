package com.salesianostriana.dam.ad.ad_tamanoficherobufferingparte2;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // Este método proporciona el código fuente para la obtención de el flujo de entrada desde una URL
    public InputStream getInputStreamFromURL(String url) throws IOException {
        URL u = new URL(url);
        URLConnection con = u.openConnection();
        InputStream is = con.getInputStream();
        return is;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Por un lado, para poder abrir una conexión de red en el hilo principal, debemos añadir
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String ruta = "http://www.salesianos-triana.com/web2/sites/default/files/img/quienes_somos/historia/trianaHist.jpg";
        long downloadBegin, downloadEnd, time;
        final int TAM = 32 * 1024;
        BufferedInputStream bis;

        InputStream is = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        try {
            bis = new BufferedInputStream(getInputStreamFromURL(ruta));

            byte[] buffer = new byte[TAM];
            int cantBytes = 0, fileSize = 0;
            downloadBegin = System.currentTimeMillis();
            Log.i("Comienzo descarga: ", String.valueOf((downloadBegin)));
            while ((cantBytes = bis.read(buffer, 0, TAM)) != -1) {
                fileSize++;
                if (fileSize % 1024 == 0)
                    Log.i("Parcial en MBytes: ", Integer.toString(fileSize / 1024) + " MBytes");
            }
            downloadEnd = System.currentTimeMillis();
            Log.i("Fin descarga: ", String.valueOf((downloadEnd)));
            time = downloadEnd - downloadBegin;
            Log.i("Tiempo descarga: ", String.valueOf((time)));
            Log.i("Tamaño en Bytes: ", Integer.toString(fileSize));

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

    // Método humanizado de tiempo de descarga de un fichero
    public static String humanizeSeconds(long time) {
        return String.format("%d min, %d seg",
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time),
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }

    // Método humanizado para el tamaño de un fichero
    public static String humanizeBytes(int fileSize) {
        if (fileSize < 1024)
            return Long.toString(fileSize) + " bytes";
        else if (fileSize >= 1024 && fileSize < 1024 * 1024)
            return Float.toString((float) (fileSize / 1024.0)) + " Kb";
        return "";
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
