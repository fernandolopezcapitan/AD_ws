package com.salesianostriana.ad.openweathersimpleclient;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.salesianostriana.ad.openweathersimpleclient.model.OpenWeatherDaily;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {


    public final static String URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=Sevilla,es&appid=3bcfcde9b7438aa7696f020ed75f5673&lang=es&units=metric";
    final String URL_BASE_IMG_WEATHER = "http://openweathermap.org/img/w/";
    final String EXTENSION_IMG_WEATHER = ".png";

    ProgressDialog progressDialog;

    TextView txtCiudad, txtFechaHora, txtTiempo, txtTemperatura, txtAmanecer, txtAnochecer;
    ImageView imgTiempo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCiudad = (TextView) findViewById(R.id.txtCiudad);
        txtFechaHora = (TextView) findViewById(R.id.txtFechaHora);
        txtTiempo = (TextView) findViewById(R.id.txtTiempo);
        txtTemperatura = (TextView) findViewById(R.id.txtTemperatura);
        txtAmanecer = (TextView) findViewById(R.id.txtAmanecer);
        txtAnochecer = (TextView) findViewById(R.id.txtAnochecer);
        imgTiempo = (ImageView) findViewById(R.id.imgTiempo);




       new GetDataTask().execute();

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


    private class GetDataTask extends AsyncTask<Void, Void, OpenWeatherDaily> {

        @Override
        protected OpenWeatherDaily doInBackground(Void... params) {

            OpenWeatherDaily result = null;


            //Gson gson = new Gson();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    //return null;
                    Long dateAsLong = json.getAsLong();

                    dateAsLong = dateAsLong * 1000L;

                    return new Date(dateAsLong);


                }
            });

            Gson gson = gsonBuilder.create();

            try {
                Reader r = Utils.Url2BufferedReader(URL_REQUEST);
                result = gson.fromJson(r, OpenWeatherDaily.class);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(OpenWeatherDaily openWeatherDaily) {
            progressDialog.dismiss();

            DateFormat dfFechayHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dfFechayHora.setTimeZone(TimeZone.getTimeZone("GMT+1"));

            DateFormat dfHora = new SimpleDateFormat("HH:mm");
            dfHora.setTimeZone(TimeZone.getTimeZone("GMT+1"));


            if (openWeatherDaily!= null) {

                txtCiudad.setText(openWeatherDaily.getName());
                txtFechaHora.setText("Actualizado a \n" + dfFechayHora.format(openWeatherDaily.getDt()));
                txtTiempo.setText(openWeatherDaily.getWeather().get(0).getDescription());
                txtTemperatura.setText(openWeatherDaily.getMain().getTemp() + "º");
                txtAmanecer.setText(dfHora.format(openWeatherDaily.getSys().getSunrise()));
                txtAnochecer.setText(dfHora.format(openWeatherDaily.getSys().getSunset()));

                String url_image = URL_BASE_IMG_WEATHER + openWeatherDaily.getWeather().get(0).getIcon() + EXTENSION_IMG_WEATHER;

                Picasso.with(MainActivity.this)
                        .load(url_image)
                        .fit()
                        .into(imgTiempo);

            } else {
                Toast.makeText(MainActivity.this, "Error en la descarga y procesamiento de la información", Toast.LENGTH_LONG).show();
            }


        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Descargando datos...");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();



        }
    }



}
