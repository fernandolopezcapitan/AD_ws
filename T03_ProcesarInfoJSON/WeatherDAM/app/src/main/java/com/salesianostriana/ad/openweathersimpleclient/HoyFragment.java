package com.salesianostriana.ad.openweathersimpleclient;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HoyFragment extends Fragment {


    public HoyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hoy, container, false);

        TextView ejemplo = (TextView)v.findViewById(R.id.textViewEjemplo);
        ejemplo.setText("Estoy en Detalle Fragment");

        // CON ESTA LÍNEA DE CÓDIGO INDICO QUE ESTE FRAGMENT
        // TIENE UN MENÚ DE OPCIONES QUE DEBE SOBREESCRIBIR AL DEL ACTIVITY
        setHasOptionsMenu(true);

        return v;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
