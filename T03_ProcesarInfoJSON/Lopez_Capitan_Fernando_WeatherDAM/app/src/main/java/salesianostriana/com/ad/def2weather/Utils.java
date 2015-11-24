package salesianostriana.com.ad.def2weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Luismi on 04/11/2015.
 */
public class Utils {

    public static List<String> listadoCiudadesFav= new ArrayList<String>();
    /*
        Método que devuelve el bufferedreader asociado a una URL.
     */

    public static BufferedReader Url2BufferedReader(String url) throws IOException {

        //return new BufferedReader(new InputStreamReader((new URL(url)).openStream()));
        URL Url = new URL(url);

        InputStream is = Url.openStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        return br;

    }


    /*
        Método que devuelve el contenido de una URL como una cadena de caracteres
     */

    public static String getStringContentFromUrl(String url) throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader br =  Url2BufferedReader(url);

        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();

        return sb.toString();

    }


    public static String getDiaSemana(String fecha) {
        String valor_dia = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = null;
        try {
            fechaActual = df.parse(fecha);
        } catch (ParseException e) {
            System.err.println("No se ha podido parsear la fecha.");
            e.printStackTrace();
        }
        GregorianCalendar fechaCalendario = new GregorianCalendar();
        fechaCalendario.setTime(fechaActual);
        int diaSemana = fechaCalendario.get(Calendar.DAY_OF_WEEK);
        if (diaSemana == 1) {
            valor_dia = "Domingo";
        } else if (diaSemana == 2) {
            valor_dia = "Lunes";
        } else if (diaSemana == 3) {
            valor_dia = "Martes";
        } else if (diaSemana == 4) {
            valor_dia = "Miercoles";
        } else if (diaSemana == 5) {
            valor_dia = "Jueves";
        } else if (diaSemana == 6) {
            valor_dia = "Viernes";
        } else if (diaSemana == 7) {
            valor_dia = "Sabado";
        }
        return valor_dia;
    }
    public static void cargarArray(Context mContext){
        SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
        listadoCiudadesFav.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            listadoCiudadesFav.add(mSharedPreference1.getString("Status_" + i, null));
        }
    }
    public static int imgTiempo (String nombre){
        int imagen;
        switch (nombre){
            case "http://openweathermap.org/img/w/01d.png":
                imagen = R.drawable.a01d;
                break;
            case "http://openweathermap.org/img/w/01n.png":
                imagen = R.drawable.a01n;
                break;
            case "http://openweathermap.org/img/w/02d.png":
                imagen = R.drawable.a02d;
                break;
            case "http://openweathermap.org/img/w/02n.png":
                imagen = R.drawable.a02n;
                break;
            case "http://openweathermap.org/img/w/03d.png":
                imagen = R.drawable.a03d;
                break;
            case "http://openweathermap.org/img/w/03n.png":
                imagen = R.drawable.a03d;
                break;
            case "http://openweathermap.org/img/w/04d.png":
                imagen = R.drawable.a04d;
                break;
            case "http://openweathermap.org/img/w/04n.png":
                imagen = R.drawable.a04d;
                break;
            case "http://openweathermap.org/img/w/09d.png":
                imagen = R.drawable.a09d;
                break;
            case "http://openweathermap.org/img/w/09n.png":
                imagen = R.drawable.a09n;
                break;
            case "http://openweathermap.org/img/w/10d.png":
                imagen = R.drawable.a10d;
                break;
            case "http://openweathermap.org/img/w/10n.png":
                imagen = R.drawable.a10n;
                break;
            case "http://openweathermap.org/img/w/11d.png":
                imagen = R.drawable.a11d;
                break;
            case "http://openweathermap.org/img/w/11n.png":
                imagen = R.drawable.a11n;
                break;
            case "http://openweathermap.org/img/w/13d.png":
                imagen = R.drawable.a13d;
                break;
            case "http://openweathermap.org/img/w/13n.png":
                imagen = R.drawable.a13n;
                break;
            case "http://openweathermap.org/img/w/50d.png":
                imagen = R.drawable.a50d;
                break;
            case "http://openweathermap.org/img/w/50n.png":
                imagen = R.drawable.a50n;
                break;
            default:
                imagen = R.drawable.dunno;
                break;
        }
        return imagen;
    }

}
