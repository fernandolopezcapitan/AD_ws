package salesianostriana.com.ad.def2weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import salesianostriana.com.ad.def2weather.modelOpenWeather.OpenWeatherDaily;

/**
 * Created by flopez on 12/11/2015.
 */
public class HoyFragment extends Fragment {
    //private static String apiWeather = "7625620ff79c19a235c230dcf2faa56a";
    //private static String apiGoogle = "AIzaSyBHntekX8fl61Bj1n9aUYbaKxVQa3frT-o";
    Context vPrueba;
    private String nombre_ciudad;

    // la coletilla "units=metric" del final es para que muestre la temperatura en ºC
    //public final static String URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=Sevilla,es&appid="+apiWeather+"&lang=es&units=metric";
    final String URL_BASE_IMG_WEATHER = "http://openweathermap.org/img/w/";
    final String EXTENSION_IMG_WEATHER = ".png";

    ProgressDialog progressDialog;

    TextView txtCiudad, txtFechaHora, txtTiempo, txtTemperatura, txtMaxima, txtMinima, txtViento, txtLluvia, txtAmanecer, txtAnochecer;
    ImageView imgTiempo;
    FloatingActionButton floatingActionButton;

    SharedPreferences prefs;
    String URL_REQUEST;

    List<String> listaCiudades = new ArrayList<String>();

    public HoyFragment() {
        super();
    }

    public HoyFragment(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vPrueba = container.getContext();
        View rootView = inflater.inflate(R.layout.fragment_hoy, container, false);

        //prefs = getActivity().getSharedPreferences("NuevasPreferencias", Context.MODE_APPEND);
        //final SharedPreferences.Editor editor = prefs.edit();

        txtCiudad = (TextView) rootView.findViewById(R.id.txtCiudad);
        txtFechaHora = (TextView) rootView.findViewById(R.id.txtFechaHora);
        txtTiempo = (TextView) rootView.findViewById(R.id.txtTiempo);
        txtTemperatura = (TextView) rootView.findViewById(R.id.txtTemperatura);
        txtMaxima = (TextView) rootView.findViewById(R.id.txtTemperaturaMaxima);
        txtMinima = (TextView) rootView.findViewById(R.id.txtTemperaturaMinima);
        txtViento = (TextView) rootView.findViewById(R.id.txtViento);
        txtLluvia = (TextView) rootView.findViewById(R.id.txtLluvia);
        txtAmanecer = (TextView) rootView.findViewById(R.id.txtAmanecer);
        txtAnochecer = (TextView) rootView.findViewById(R.id.txtAnochecer);
        imgTiempo = (ImageView) rootView.findViewById(R.id.imgTiempo);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab);

        new GetDataTask().execute();

        /*
        Utils.cargarArray(getActivity());
        if(Utils.listadoCiudadesFav.contains(nombre_ciudad)){
            floatingActionButton.setImageResource(android.R.drawable.star_big_on);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(floatingActionButton.getDrawable().equals(android.R.drawable.star_big_off)){
                }else {
                    if (Utils.listadoCiudadesFav.contains(nombre_ciudad)) {
                        Toast.makeText(getActivity(), "La ciudad asignada ya está en favoritos", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Utils.listadoCiudadesFav.contains(nombre_ciudad)) {
                            Toast.makeText(getActivity(), "Ya está en sus favoritos", Toast.LENGTH_SHORT).show();
                        } else {
                            Utils.listadoCiudadesFav.add(nombre_ciudad);
                            Utils.guardarArray(getActivity());
                            Log.i("Añadido a favoritos", nombre_ciudad);

                            floatingActionButton.setImageResource(android.R.drawable.star_big_on);
                        }
                    }
                }
                }
            });
        */
        return rootView;
    }

    private class GetDataTask extends AsyncTask<Void, Void, OpenWeatherDaily> {

        @Override
        protected OpenWeatherDaily doInBackground(Void... params) {
            String apiWeather = "7625620ff79c19a235c230dcf2faa56a";
            if (nombre_ciudad == null) {
                URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=Sevilla,Spain&units=metric&appid="+apiWeather+"&lang=es";
            } else {
                URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q="+nombre_ciudad.replace(" ","%20").replace("España","Spain")+"&units=metric&appid="+apiWeather+"&lang=es";
            }
            //String URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q="+nombre_ciudad.replace(" ","%20").replace("España","Spain")+"&units=metric&appid="+apiWeather+"&lang=es";

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
            //progressDialog.dismiss();

            DateFormat dfFechayHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dfFechayHora.setTimeZone(TimeZone.getTimeZone("GMT+1"));

            DateFormat dfHora = new SimpleDateFormat("HH:mm");
            dfHora.setTimeZone(TimeZone.getTimeZone("GMT+1"));

            if (openWeatherDaily!= null) {


                DecimalFormat df = new DecimalFormat("0.0");

                txtCiudad.setText(openWeatherDaily.getName());
                txtFechaHora.setText("Actualizado a \t" + dfFechayHora.format(openWeatherDaily.getDt()));
                txtTiempo.setText(openWeatherDaily.getWeather().get(0).getDescription());
                //Parte entera de la temperatura
                txtTemperatura.setText(Math.round(openWeatherDaily.getMain().getTemp()) + "ºC");
                txtMaxima.setText(df.format(openWeatherDaily.getMain().getTempMax())+"ºC");
                txtMinima.setText(df.format(openWeatherDaily.getMain().getTempMin())+"ºC");
                txtViento.setText(openWeatherDaily.getWind().getSpeed()+"Km/h");
                txtLluvia.setText(openWeatherDaily.getMain().getHumidity()+"%");

                txtAmanecer.setText(dfHora.format(openWeatherDaily.getSys().getSunrise()));
                txtAnochecer.setText(dfHora.format(openWeatherDaily.getSys().getSunset()));

                String url_image = URL_BASE_IMG_WEATHER + openWeatherDaily.getWeather().get(0).getIcon() + EXTENSION_IMG_WEATHER;

                //final String URL_BASE_IMG_WEATHER = "http://openweathermap.org/img/w/";
                //final String EXTENSION_IMG_WEATHER = ".png";
                Picasso.with(getActivity())
                        .load(Utils.imgTiempo(url_image))
                        .fit()
                        .into(imgTiempo);
                mostrarElementosUI();

                Log.i("TIEMPO",url_image);

            } else {
                Toast.makeText(getActivity(), "Error en la descarga y procesamiento de la información", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ocultarElementosUI();
        }
    }
    public void mostrarElementosUI(){

        txtCiudad.setVisibility(View.VISIBLE);
        txtFechaHora.setVisibility(View.VISIBLE);
        txtTiempo.setVisibility(View.VISIBLE);
        txtTemperatura.setVisibility(View.VISIBLE);
        txtAmanecer.setVisibility(View.VISIBLE);
        txtAnochecer.setVisibility(View.VISIBLE);
        txtMaxima.setVisibility(View.VISIBLE);
        txtMinima.setVisibility(View.VISIBLE);
        txtLluvia.setVisibility(View.VISIBLE);

        imgTiempo.setVisibility(View.VISIBLE);
    }


    public void ocultarElementosUI(){

        txtCiudad.setVisibility(View.INVISIBLE);
        txtFechaHora.setVisibility(View.INVISIBLE);
        txtTiempo.setVisibility(View.INVISIBLE);
        txtTemperatura.setVisibility(View.INVISIBLE);
        txtAmanecer.setVisibility(View.INVISIBLE);
        txtAnochecer.setVisibility(View.INVISIBLE);
        txtMaxima.setVisibility(View.INVISIBLE);
        txtMinima.setVisibility(View.INVISIBLE);
        txtLluvia.setVisibility(View.INVISIBLE);

        imgTiempo.setVisibility(View.INVISIBLE);

    }
}
