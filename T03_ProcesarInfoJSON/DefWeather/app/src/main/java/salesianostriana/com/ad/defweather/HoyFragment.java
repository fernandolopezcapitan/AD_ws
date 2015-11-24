package salesianostriana.com.ad.defweather;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import salesianostriana.com.ad.defweather.modelOpenWeather.OpenWeatherDaily;

/**
 * Created by flopez on 12/11/2015.
 */
public class HoyFragment extends Fragment{
    private static String apiWeather = "7625620ff79c19a235c230dcf2faa56a";
    private static String apiGoogle = "AIzaSyBHntekX8fl61Bj1n9aUYbaKxVQa3frT-o";

    // la coletilla "units=metric" del final es para que muestre la temperatura en ºC
    public final static String URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=Sevilla,es&appid="+apiWeather+"&lang=es&units=metric";
    final String URL_BASE_IMG_WEATHER = "http://openweathermap.org/img/w/";
    final String EXTENSION_IMG_WEATHER = ".png";

    ProgressDialog progressDialog;

    TextView txtCiudad, txtFechaHora, txtTiempo, txtTemperatura, txtAmanecer, txtAnochecer;
    ImageView imgTiempo;

    public HoyFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hoy, container, false);

        txtCiudad = (TextView) rootView.findViewById(R.id.txtCiudad);
        txtFechaHora = (TextView) rootView.findViewById(R.id.txtFechaHora);
        txtTiempo = (TextView) rootView.findViewById(R.id.txtTiempo);
        txtTemperatura = (TextView) rootView.findViewById(R.id.txtTemperatura);
        txtAmanecer = (TextView) rootView.findViewById(R.id.txtAmanecer);
        txtAnochecer = (TextView) rootView.findViewById(R.id.txtAnochecer);
        imgTiempo = (ImageView) rootView.findViewById(R.id.imgTiempo);

        new GetDataTask().execute();
        return rootView;
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

                Picasso.with(getActivity())
                        .load(url_image)
                        .fit()
                        .into(imgTiempo);

            } else {
                Toast.makeText(getActivity(), "Error en la descarga y procesamiento de la información", Toast.LENGTH_LONG).show();
            }


        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Descargando datos...");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }
}
