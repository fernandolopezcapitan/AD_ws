package com.salesianostriana.dam.ad.e02_instagramrss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesianostriana.dam.ad.e02_instagramrss.pojos.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by flopez on 24/11/2015.
 */
public class XmlAdapter extends RecyclerView.Adapter<XmlAdapter.ViewHolder>{

    private ArrayList<Item> imagenArrayList;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView fecha;
        public TextView autor;

        public ViewHolder(View v) {
            super(v);
            fecha = (TextView)v.findViewById(R.id.txt_fecha);
            autor = (TextView) v.findViewById(R.id.txt_autor);
            img = (ImageView) v.findViewById(R.id.iv_img);
        }
    }

    public XmlAdapter() {
    }

    public XmlAdapter(Context context, ArrayList<Item> imagenArrayList) {
        this.context = context;
        this.imagenArrayList = imagenArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_recycler, viewGroup, false);

        context = v.getContext();
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.fecha.setText(imagenArrayList.get(position).getPubdate());
        holder.autor.setText(imagenArrayList.get(position).getAutor());
        Picasso.with(context).load(imagenArrayList.get(position).getDescripcion()).resize(300, 300).into(holder.img);
    }

    @Override
    public int getItemCount()  {
        return imagenArrayList.size();
    }
}
