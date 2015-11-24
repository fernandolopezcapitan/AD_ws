package salesianostriana.com.ad.def2weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by flopez on 12/11/2015.
 */
public class AdapterPronostico extends RecyclerView.Adapter<AdapterPronostico.ViewHolder>{

    private ArrayList<ItemPronostico> mDataset;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView diaSemana;
        static public ImageView sol;
        public TextView nombreCiudadTextView;
        public TextView temperatura;
        public TextView maxima;
        public TextView minima;

        public ViewHolder(View v) {
            super(v);

            diaSemana = (TextView)v.findViewById(R.id.txtDiaSemana);
            sol = (ImageView)v.findViewById(R.id.imgSol);
            nombreCiudadTextView = (TextView)v.findViewById(R.id.txtCiudadPro);
            temperatura = (TextView)v.findViewById(R.id.txtTemperaturaPro);
            maxima = (TextView)v.findViewById(R.id.maximaPro);
            minima = (TextView)v.findViewById(R.id.minimaPro);
        }
    }

    public AdapterPronostico(ArrayList<ItemPronostico> myDataset) {
        mDataset = myDataset;
    }


    @Override
    public AdapterPronostico.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_ciudad, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(AdapterPronostico.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.diaSemana.setText(mDataset.get(position).getDiaSemana());
        Picasso.with(context).load(mDataset.get(position).getSol()).fit().into(ViewHolder.sol);
        holder.nombreCiudadTextView.setText(mDataset.get(position).getCiudad());
        holder.temperatura.setText(mDataset.get(position).getTemperatura());
        holder.maxima.setText(mDataset.get(position).getMaxima());
        holder.minima.setText(mDataset.get(position).getMinima());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
