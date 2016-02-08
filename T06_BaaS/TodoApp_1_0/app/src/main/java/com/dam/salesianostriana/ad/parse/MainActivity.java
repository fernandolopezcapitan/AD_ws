package com.dam.salesianostriana.ad.parse;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/////////////////////////////////////////////////////////////////////////////////////////
// Para poder ver algunas las notas creadas entrar como:
// usuario: flopez
// pass: 123456
/////////////////////////////////////////////////////////////////////////////////////////
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ListView listaNotas;
    Spinner sp_orden, sp_fecha;
    ParseQueryAdapter adapter;
    ParseUser currentUser;
    String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
        startActivityForResult(builder.build(), 0);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            objectId = currentUser.getObjectId();

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoNuevaNota dialogo = new DialogoNuevaNota(objectId);
                dialogo.show(fragmentManager, "tagAlerta");

            }
        });

        listaNotas = (ListView) findViewById(R.id.listView);
        sp_orden = (Spinner) findViewById(R.id.sp_orden);
        sp_fecha =(Spinner) findViewById(R.id.sp_fecha);



        // Al abrir el activity consulta todas las notas, sin ningún filtro
        adapter = new ParseQueryAdapter(MainActivity.this,"Todo");

        listaNotas.setAdapter(adapter);

        // La siguiente línea de código conecta el evento LongClick sobre un elemento
        // del ListView, con el Menú Contextual
        registerForContextMenu(listaNotas);


        // Adaptador del spinner sp_orden
        adaptadorSpinnerOrden();

        // Adaptador del spinner sp_fecha
        adaptadorSpinnerFecha();


        //adapter.notifyDataSetChanged();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info;
        try {
            // Casts the incoming data object into the type for AdapterView objects.
            info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        } catch (ClassCastException e) {
            // If the menu object can't be cast, logs an error.
            Log.e("TAG", "bad menuInfo", e);
            return;
        }



    }

    private void adaptadorSpinnerOrden() {
        ArrayAdapter<CharSequence> adapter_orden = ArrayAdapter.createFromResource(this,R.array.Orden,android.R.layout.simple_spinner_item);
        adapter_orden.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_orden.setAdapter(adapter_orden);
        //Asociamos al spinner sp_orden al evento OnItemSelectedListener
        sp_orden.setOnItemSelectedListener(this);
    }

    private void adaptadorSpinnerFecha() {
        ArrayAdapter<CharSequence> adapter_fecha = ArrayAdapter.createFromResource(this,R.array.Fecha,android.R.layout.simple_spinner_item);
        adapter_fecha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_fecha.setAdapter(adapter_fecha);
        //Asociamos al spinner sp_fecha al evento OnItemSelectedListener
        sp_fecha.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        final String seleccOrden = (String) sp_orden.getSelectedItem();
        final String seleccFecha = (String) sp_fecha.getSelectedItem();

        adapter = new ParseQueryAdapter(MainActivity.this, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            @Override
            public ParseQuery<ParseObject> create() {
                ParseQuery query = new ParseQuery("Todo");
                query.whereEqualTo("user_id", ParseUser.getCurrentUser());


                if (seleccFecha.equalsIgnoreCase("Hoy")) {

                    query.whereGreaterThanOrEqualTo("Fecha", hoyHoraCero());
                    query.whereLessThanOrEqualTo("Fecha", hoyHora24());

                    if(seleccOrden.equalsIgnoreCase("Ascendente")){
                        query.orderByAscending("Concepto");
                    } else if (seleccOrden.equalsIgnoreCase("Descendente")){
                        query.orderByDescending("Concepto");
                    }
                } else if (seleccFecha.equalsIgnoreCase("Ayer y anteriores")){

                    query.whereLessThanOrEqualTo("Fecha", ayerHora24());

                    if(seleccOrden.equalsIgnoreCase("Ascendente")){
                        query.orderByAscending("Concepto");
                    } else if (seleccOrden.equalsIgnoreCase("Descendente")){
                        query.orderByDescending("Concepto");
                    }
                } else if(seleccFecha.equalsIgnoreCase("Mañana y posteriores")){

                    query.whereGreaterThanOrEqualTo("Fecha", mñnHoraCero());

                    if(seleccOrden.equalsIgnoreCase("Ascendente")){
                        query.orderByAscending("Concepto");
                    } else if (seleccOrden.equalsIgnoreCase("Descendente")){
                        query.orderByDescending("Concepto");
                    }
                } else if (seleccFecha.equalsIgnoreCase("Todas")){

                    if(seleccOrden.equalsIgnoreCase("Ascendente")){
                        query.orderByAscending("Concepto");
                    } else if (seleccOrden.equalsIgnoreCase("Descendente")){
                        query.orderByDescending("Concepto");
                    }
                }

                return query;
            }
        });
        listaNotas.setAdapter(adapter);

    }

    private Date mñnHoraCero() {
        Calendar mñn = new GregorianCalendar();

        mñn.add(Calendar.DAY_OF_MONTH, 1);
        mñn.set(Calendar.HOUR_OF_DAY, 0);
        mñn.set(Calendar.MINUTE, 0);
        mñn.set(Calendar.SECOND, 0);

        Date mñnHora24 = mñn.getTime();

        return mñnHora24;
    }

    private Date ayerHora24() {
        Calendar ayer = new GregorianCalendar();

        ayer.add(Calendar.DAY_OF_MONTH,-1);
        ayer.set(Calendar.HOUR_OF_DAY, 23);
        ayer.set(Calendar.MINUTE, 59);
        ayer.set(Calendar.SECOND, 59);

        Date ayerHora24 = ayer.getTime();

        return ayerHora24;
    }

    private Date hoyHora24() {
        Calendar hoy = new GregorianCalendar();

        hoy.set(Calendar.HOUR_OF_DAY, 23);
        hoy.set(Calendar.MINUTE, 59);
        hoy.set(Calendar.SECOND, 59);

        Date hoyHora24 = hoy.getTime();
        return hoyHora24;
    }

    private Date hoyHoraCero() {
        Calendar hoy = new GregorianCalendar();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        hoy.set(Calendar.MINUTE, 0);
        hoy.set(Calendar.SECOND, 0);

        Date hoyHora0 = hoy.getTime();
        return hoyHora0;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String mensaje = "";
        switch(id) {

            case R.id.logout: mensaje = "Cerrar sesión";

                ParseUser.logOut();
                ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
                startActivityForResult(builder.build(), 0);
                MainActivity.this.finish();
                break;

        }

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

}
