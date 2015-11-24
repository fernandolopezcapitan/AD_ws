package com.salesianostriana.dam.ad.e01_parseraemet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by flopez on 22/11/2015.
 */
public class AdapterPronostico extends RecyclerView.Adapter<AdapterPronostico.ViewHolder> {

    private ArrayList<ItemPronostico> mDataset;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fecha;
        public TextView maxima;
        public TextView minima;

        public ViewHolder(View v) {
            super(v);

            fecha = (TextView)v.findViewById(R.id.txtFecha);
            maxima = (TextView)v.findViewById(R.id.maxima);
            minima = (TextView)v.findViewById(R.id.minima);
        }
    }

    public AdapterPronostico(ArrayList<ItemPronostico> dias) {
        mDataset = dias;
    }

    @Override
    public AdapterPronostico.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_temp,parent,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterPronostico.ViewHolder holder, int position) {
        holder.fecha.setText(mDataset.get(position).getFecha());
        holder.maxima.setText(mDataset.get(position).getMax());
        holder.minima.setText(mDataset.get(position).getMin());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
