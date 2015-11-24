package salesianostriana.com.ad.def2weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import salesianostriana.com.ad.def2weather.modelGooglePlaces.Predictions;
import salesianostriana.com.ad.def2weather.modelGooglePlaces.Region;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static Toolbar toolbar;
    TabLayout tabLayout;
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyBHntekX8fl61Bj1n9aUYbaKxVQa3frT-o";
    //private static final String API_KEY = "AIzaSyCAfWAIEXkY9aNcEPhR6z0s2pFFiojQjqg";
    AutoCompleteTextView ciudad;
    /**
     * Servirá para almacenar una lista con los datos obtenidos de la consulta al Api de Google.
     */
    List<Predictions> listaRegiones;
    /**
     * Almacenará el nombre de la ciudad que se quiere buscar.
     */
    private String nombre_ciudad;

    public static void colocarTitulo(String titulo){
        toolbar.setTitle(titulo);
    }
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemPronostico> ciudades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ciudad = (AutoCompleteTextView) findViewById(R.id.editCiudad);


        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            nombre_ciudad = extras.getString("titulo");
        }
        toolbar.setTitle("WeatherFLC");
        setSupportActionBar(toolbar);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Get the SearchView and set the searchable configuration
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint("Busca tu ciudad");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ciudad.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ciudad.showDropDown();
                    }
                }, 0);
                ciudad.setText(newText);
                new GooglePlaceTask().execute(ciudad.getText().toString());

                return false;
            }
        });
        return true;
    }
    class GooglePlaceTask extends AsyncTask<String, Void, List<Predictions>> {

        @Override
        protected List<Predictions> doInBackground(String... params) {
            List<Predictions> result = null;
            URL url = null;

            try {
                if(params[0] != null) {

                    url = new URL(PLACES_API_BASE + TYPE_AUTOCOMPLETE
                            + OUT_JSON + "?input=" + params[0].replace(" ","%20")
                            + "&types=(cities)&language=es_ES&key=" + API_KEY);

                    BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));

                    Gson gson = new Gson();

                    Region region = gson.fromJson(r, Region.class);

                    result = Arrays.asList(region.getPredictions());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(List<Predictions> predictionses) {
            super.onPostExecute(predictionses);

            if (predictionses != null) {

                listaRegiones = predictionses;
                ciudad.setAdapter(new AutoComplete(MainActivity.this, android.R.layout.simple_list_item_1));
                ciudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String str = (String) parent.getItemAtPosition(position);
                        MainActivity.this.finish();
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        i.putExtra("titulo", str);
                        startActivity(i);
                    }
                });
            }

        }
    }

    class AutoComplete extends ArrayAdapter implements Filterable {

        public AutoComplete(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return listaRegiones.size();
        }

        @Override
        public Object getItem(int index) {
            return listaRegiones.get(index).getDescription();
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {

                        filterResults.values = listaRegiones;
                        filterResults.count = listaRegiones.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new HoyFragment(nombre_ciudad);
            }else if(position==1){
                return new PronosticoFragment(nombre_ciudad);
            }else if(position==2){
                return new FavoritosFragment();
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a HoyFragment (defined as a static inner class below).
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
                    return "HOY";
                case 1:
                    return "PRONÓSTICO";
                case 2:
                    return "FAVORITOS";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        final String URL_BASE_IMG_WEATHER = "http://openweathermap.org/img/w/";
        final String EXTENSION_IMG_WEATHER = ".png";

        ProgressDialog progressDialog;

        private String nombre_ciudad;

        TextView txtCiudad, txtFechaHora, txtTiempo, txtTemperatura, txtAmanecer, txtViento, txtLluvia, txtAnochecer, txtMaxima, txtMinima, txtHumedad;
        ImageView imgTiempo;


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        public PlaceholderFragment(String nombre_ciudad) {
            this.nombre_ciudad = nombre_ciudad;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_hoy, container, false);

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

            //new GetDataTask().execute();
            return rootView;
        }
    }
}
