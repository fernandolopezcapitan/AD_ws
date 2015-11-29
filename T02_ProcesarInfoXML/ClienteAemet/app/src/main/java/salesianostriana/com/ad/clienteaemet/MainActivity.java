package salesianostriana.com.ad.clienteaemet;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import salesianostriana.com.ad.clienteaemet.pojoMunicipios.Municipios;

public class MainActivity extends AppCompatActivity {

    private String url_municipios = "http://www.salesianos-triana.com/dam/xml/municipios/?ciudad=Sevilla";
    private String url_aemet = "http://www.aemet.es/xml/municipios/localidad_41091.xml";
    private TextView txt_poblacion, txt_provincia, txt_fecha_hoy;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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
        recyclerView.setAdapter(new AdapterPronostico(MainActivity.this,"41091",txt_poblacion,txt_fecha_hoy));
    }
}
