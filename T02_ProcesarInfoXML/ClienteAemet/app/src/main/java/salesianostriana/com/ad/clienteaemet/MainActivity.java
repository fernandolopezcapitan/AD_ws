package salesianostriana.com.ad.clienteaemet;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import salesianostriana.com.ad.clienteaemet.pojoMunicipios.Municipios;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Serializer serializer = new Persister();
        try {

            //http://www.salesianos-triana.com/dam/xml/municipios/?ciudad=Sevilla
            URL url = new URL("http://www.salesianos-triana.com/dam/xml/municipios/?ciudad=Sevilla");
            InputStream in = url.openStream();
            Municipios inicioMunicipios = serializer.read(Municipios.class,in,false);

            Log.i("Root", inicioMunicipios.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
