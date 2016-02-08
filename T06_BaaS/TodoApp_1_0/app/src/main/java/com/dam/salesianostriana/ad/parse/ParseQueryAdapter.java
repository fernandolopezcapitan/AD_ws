package com.dam.salesianostriana.ad.parse;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;

import java.text.SimpleDateFormat;

/**
 * Created by flopez on 01/02/2016.
 */
public class ParseQueryAdapter extends com.parse.ParseQueryAdapter<ParseObject> {

    public ParseQueryAdapter(Context context, String className) {
        super(context, className);
    }

    public ParseQueryAdapter(MainActivity context, QueryFactory<ParseObject> queryFactory) {
        super(context, queryFactory);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.list_item, null);
        }

        super.getItemView(object, v, parent);

        TextView dia = (TextView) v.findViewById(R.id.tv_dia);
        TextView concepto = (TextView) v.findViewById(R.id.tv_concepto);
        //TextView hora = (TextView) v.findViewById(R.id.tv_hora);

        // Formatear dia dd-MM-yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy           hh:mm a");
        String formatDia = sdf.format(object.getDate("Fecha"));

        // Formatear hora hh:mm a
        //SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm a");
        //String formatHora = sdf1.format(object.getDate("Fecha"));


        dia.setText(formatDia);
        //hora.setText(formatHora);
        concepto.setText(object.getString("Concepto"));

        return v;
    }
}
