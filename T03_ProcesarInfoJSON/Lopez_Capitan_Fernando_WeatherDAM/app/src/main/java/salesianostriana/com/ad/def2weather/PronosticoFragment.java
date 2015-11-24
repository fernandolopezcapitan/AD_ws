package salesianostriana.com.ad.def2weather;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import salesianostriana.com.ad.def2weather.modelPronostico.List;
import salesianostriana.com.ad.def2weather.modelPronostico.OpenWeatherPronostico;

/**
 * Created by flopez on 12/11/2015.
 */
public class PronosticoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemPronostico> dias;
    Context vPrueba;
    private String nombreCiudad;
    private static String apiWeather = "7625620ff79c19a235c230dcf2faa56a";
    private static String apiGoogle = "AIzaSyBHntekX8fl61Bj1n9aUYbaKxVQa3frT-o";
    // la coletilla "units=metric" del final es para que muestre la temperatura en ºC
    public final static String URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=Sevilla,es&appid=" + apiWeather + "&lang=es&units=metric";
    final String URL_BASE_IMG_WEATHER = "http://openweathermap.org/img/w/";
    final String EXTENSION_IMG_WEATHER = ".png";


    public PronosticoFragment() {
        //Constructor vacío
    }
    public PronosticoFragment(String nombreCiudad){
        this.nombreCiudad = nombreCiudad;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_pronostico, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        new GetDataTask().execute();
        return v;

    }

    private class GetDataTask extends AsyncTask<Void, Void, java.util.List<List>> {

        @Override
        protected java.util.List<List> doInBackground(Void... params) {

            java.util.List<List> result = null;
            URL url = null;

            try {

                if (nombreCiudad == null) {
                    url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=Sevilla,Spain&appid=3bcfcde9b7438aa7696f020ed75f5673&lang=es&units=metric");
                } else {
                    url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + nombreCiudad.replace(" ", "%20").replace("España", "Spain") + "&appid=3bcfcde9b7438aa7696f020ed75f5673&lang=es&units=metric");
                }
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

                // url = new URL("https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+params[0]+"&types=(cities)&language=es_ES&key=AIzaSyAowGYjVlmA1XJl2EZNvKCVgNF8nPk8uHE");

                BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));


                OpenWeatherPronostico op = gson.fromJson(r, OpenWeatherPronostico.class);

                result = op.getList();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(java.util.List<List> listWeather) {

            String URL_BASE_IMG_WEATHER = "http://openweathermap.org/img/w/";
            String EXTENSION_IMG_WEATHER = ".png";

            DateFormat dfFechayHora = new SimpleDateFormat("yyyy-MM-dd");
            dfFechayHora.setTimeZone(TimeZone.getTimeZone("GMT+1"));

            DateFormat dfHora = new SimpleDateFormat("HH:mm");
            dfHora.setTimeZone(TimeZone.getTimeZone("GMT+1"));


            if (listWeather != null) {

                dias = new ArrayList();
                for (int i = 0; i < listWeather.size(); i = i + 8) {

                    //txtTemperatura.setText(Math.round(openWeatherDaily.getMain().getTemp()) + "ºC");
                    String dia_semana = Utils.getDiaSemana(listWeather.get(i).getDtTxt().substring(0, 10));
                    //txtCiudad.setText(openWeatherDaily.getName());
                    //String ciudad = listWeather.get(i);
                    String url_image = URL_BASE_IMG_WEATHER + listWeather.get(i).getWeather().get(0).getIcon() + EXTENSION_IMG_WEATHER;
                    //String fecha = listWeather.get(i).getDtTxt().substring(0, 11);
                    String temp = String.valueOf(listWeather.get(i).getMain().getTemp() + "º");
                    String temp_max = String.valueOf(listWeather.get(i).getMain().getTempMax() + "º");
                    String temp_min = String.valueOf(listWeather.get(i).getMain().getTempMin() + "º");

                    //ItemPronostico(String diaSemana, String ciudad, String sol, String temperatura, String maxima, String minima)
                    dias.add(new ItemPronostico(dia_semana, nombreCiudad, Utils.imgTiempo(url_image), temp, temp_max, temp_min));

                }
                //ciudades.add(new ItemCiudad(URL_BASE_IMG_WEATHER + listWeather.get(0).getWeather().get(0).getIcon() + EXTENSION_IMG_WEATHER, listWeather.get(0).getDtTxt().substring(0, 10)));
                mAdapter = new AdapterPronostico(dias);
                mRecyclerView.setAdapter(mAdapter);

            } else {
                Toast.makeText(vPrueba, "Error en la descarga y procesamiento de la información", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }
    }
}
