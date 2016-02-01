package com.dam.salesianostriana.ad.parse;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by flopez on 01/02/2016.
 */
public class NotasAdapter extends ParseQueryAdapter<ParseObject> {


    public NotasAdapter(Context context, Class<? extends ParseObject> clazz) {
        super(context, clazz);
    }

    public NotasAdapter(Context context, String className) {
        super(context, className);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.list_item, null);
        }

        // Take advantage of ParseQueryAdapter's getItemView logic for
        // populating the main TextView/ImageView.
        // The IDs in your custom layout must match what ParseQueryAdapter expects
        // if it will be populating a TextView or ImageView for you.
        super.getItemView(object, v, parent);

        // Do additional configuration before returning the View.
        TextView fecha = (TextView) v.findViewById(R.id.tv_fecha);
        TextView concepto = (TextView) v.findViewById(R.id.tv_concepto);

        // Formatear fecha dd-MM-yyyy hh:mm
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm a");
        String format = sdf.format(object.getDate("Fecha"));

        fecha.setText(format);
        concepto.setText(object.getString("Concepto"));

        return v;
    }
}
