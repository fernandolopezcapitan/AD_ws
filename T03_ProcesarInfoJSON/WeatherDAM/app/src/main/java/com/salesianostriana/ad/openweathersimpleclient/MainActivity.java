package com.salesianostriana.ad.openweathersimpleclient;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.salesianostriana.ad.openweathersimpleclient.model.WeatherDayActual;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private static String apiWeather = "7625620ff79c19a235c230dcf2faa56a";
    private static String apiGoogle = "AIzaSyBHntekX8fl61Bj1n9aUYbaKxVQa3frT-o";

    // la coletilla "units=metric" del final es para que muestre la temperatura en ºC
    public final static String URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=Sevilla,es&appid="+apiWeather+"&lang=es&units=metric";
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String mensaje = "";
        switch(id) {
            case R.id.action_search: mensaje = "Buscar...";
//                String titulo = (String) item.getTitleCondensed();
//                Log.i("TITULO", titulo);
                break;
            case R.id.action_configuration: mensaje = "Configuración...";
                break;
            case R.id.action_logout: mensaje = "Salir...";
                break;
        }

        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


    private class GetDataTask extends AsyncTask<Void, Void, WeatherDayActual> {

        @Override
        protected WeatherDayActual doInBackground(Void... params) {

            WeatherDayActual result = null;


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
                result = gson.fromJson(r, WeatherDayActual.class);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(WeatherDayActual openWeatherDaily) {
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
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position==0) {
                return new HoyFragment();
            } else if(position==1) {
                return null;
                        new PronosticoFragment();
            } else if(position==2) {
                return new FavoritosFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Detalle";
                case 1:
                    return "Pronóstico";
                case 2:
                    return "Favoritos";
            }
            return null;
        }
    }


}
