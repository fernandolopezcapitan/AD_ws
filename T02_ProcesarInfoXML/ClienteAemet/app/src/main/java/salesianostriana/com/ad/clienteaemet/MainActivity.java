package salesianostriana.com.ad.clienteaemet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import salesianostriana.com.ad.clienteaemet.pojoAemet.Aemet;

public class MainActivity extends AppCompatActivity {

    private String url_municipios = "http://www.salesianos-triana.com/dam/xml/municipios/?ciudad=Sevilla";
    private String url_aemet = "http://www.aemet.es/xml/municipios/localidad_41091.xml";
    private TextView txt_poblacion, txt_provincia, txt_fecha_hoy;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    AutoCompleteTextView ciudad;
    private ViewPager mViewPager;
    private static Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_poblacion = (TextView) findViewById(R.id.txt_poblacion);
        txt_provincia = (TextView) findViewById(R.id.txt_provincia);
        txt_fecha_hoy = (TextView) findViewById(R.id.txt_fecha_hoy);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new AdapterPronostico(MainActivity.this, url_municipios, txt_poblacion, txt_fecha_hoy));

        VolleyAplication.requestQueue.add(new XmlCustomRequest<Aemet>(url_aemet, Aemet.class, null, new Response.Listener<Aemet>() {
                    @Override
                    public void onResponse(Aemet response) {
                        Log.i("TIEMPO", response.toString());

                        txt_poblacion.setText(response.getNombre() + " " + response.getProvincia());
                        txt_fecha_hoy.setText(response.getElaborado());
                        recyclerView.setAdapter(new AdapterPronostico(MainActivity.this, response.getPrediccion().getDias()));

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error en la descarga de datos", Toast.LENGTH_LONG).show();
                    }
                })
        );

        //new DownloadTask().execute();
    }
    private class DownloadTask extends AsyncTask<Void, Void, Aemet> {

        @Override
        protected Aemet doInBackground(Void... params) {

            URL url = null;
            BufferedReader br = null;
            Aemet result = null;
            try {
                url = new URL("http://www.aemet.es/xml/municipios/localidad_41091.xml");
                InputStream is = url.openStream();

                 Serializer serializer = new Persister();
                 result = serializer.read(Aemet.class,is);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;

        }

        @Override
        protected void onPostExecute(Aemet aemet) {
            super.onPostExecute(aemet);
            Log.i("TIEMPO", aemet.toString());

        }
    }
}
