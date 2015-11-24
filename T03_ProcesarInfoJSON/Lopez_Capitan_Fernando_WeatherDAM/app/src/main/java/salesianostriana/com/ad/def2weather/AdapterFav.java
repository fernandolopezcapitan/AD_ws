package salesianostriana.com.ad.def2weather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by flopez on 12/11/2015.
 */
public class AdapterFav extends RecyclerView.Adapter<AdapterFav.ViewHolder>{

    private ArrayList<ItemFavoritos> mDataset;
    Context contexto;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public ImageButton btn_ver;
        public ImageButton btn_borrar;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView)v.findViewById(R.id.txtCiudadFav);
            btn_ver = (ImageButton) v.findViewById(R.id.imgAtras);
            btn_borrar = (ImageButton) v.findViewById(R.id.imgBorrar);

        }

    }



    public AdapterFav(ArrayList<ItemFavoritos> myDataset) {

        mDataset = myDataset;
    }

    @Override
    public AdapterFav.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_favorito, viewGroup, false);

        contexto = v.getContext();

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterFav.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nombre.setText(mDataset.get(position).getCiudad());

        holder.btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto, MainActivity.class);
                i.putExtra("titulo",Utils.listadoCiudadesFav.get(position));
                contexto.startActivity(i);
                ((Activity)contexto).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
