package com.dam.salesianostriana.ad.parse;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ui.ParseLoginBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ListView listaNotas;
    Spinner sp_orden, sp_fecha;
    ParseQueryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
        startActivityForResult(builder.build(), 0);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(MainActivity.this,EnviarNotaActivity.class);
                startActivity(i);
                MainActivity.this.finish();*/

                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoNuevaNota dialogo = new DialogoNuevaNota();
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
        //adapter = new ParseQueryAdapter(MainActivity.this,"Todo");
        //listaNotas.setAdapter(adapter);
    }

    public static class DialogoPersonalizado extends DialogFragment {

        //EditText nuevo_concepto;


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            //nuevo_concepto = (EditText) getView().findViewById(R.id.nueva_nota_concepto);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.nueva_nota, null))
                    .setMessage("Concepto")
                    .setTitle("Insertar nueva nota")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Toast tostada = Toast.makeText(getContext(), "Nueva nota creada", Toast.LENGTH_SHORT);
                            tostada.setGravity(Gravity.BOTTOM | Gravity.LEFT, 24, 24);
                            tostada.show();

                            // Creación de una nota
                            ParseObject nuevaNota = new ParseObject("Todo");

                            nuevaNota.put("Concepto", "AAAA"/*nuevo_concepto.getText().toString()*/);
                            nuevaNota.put("Fecha", new Date());

                            nuevaNota.saveInBackground();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            return builder.create();
        }
    }
}
