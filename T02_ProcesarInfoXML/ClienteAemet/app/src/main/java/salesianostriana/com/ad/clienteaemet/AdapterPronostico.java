package salesianostriana.com.ad.clienteaemet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import salesianostriana.com.ad.clienteaemet.pojoAemet.Aemet;
import salesianostriana.com.ad.clienteaemet.pojoAemet.Dia;

/**
 * Created by flopez on 28/11/2015.
 */
public class AdapterPronostico extends RecyclerView.Adapter<AdapterPronostico.ViewHolder>{

    TextView textViewciudad;
    TextView textViewfecha;
    String url;
    List<Dia> mDataset = new ArrayList<Dia>();
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fecha;
        public TextView precipitacion_media;
        public TextView cota_nieve_minima;
        public TextView estado_cielo;
        public TextView temperatura_max;
        public TextView temperatura_min;
        public TextView sensacion_max;
        public TextView sensacion_min;

        public ViewHolder(View v) {
            super(v);

            fecha = (TextView)v.findViewById(R.id.txt_fecha);
            precipitacion_media = (TextView)v.findViewById(R.id.txt_precipitacion_media);
            cota_nieve_minima = (TextView)v.findViewById(R.id.txt_cota_nieve_minima);
            estado_cielo = (TextView)v.findViewById(R.id.txt_estado_cielo);
            temperatura_max = (TextView)v.findViewById(R.id.txt_temperatura_max);
            temperatura_min = (TextView)v.findViewById(R.id.txt_temperatura_min);
            sensacion_max = (TextView)v.findViewById(R.id.txt_sensacion_max);
            sensacion_min = (TextView)v.findViewById(R.id.txt_sensacion_min);
        }
    }
    /*
    public AdapterPronostico(List<Dia> myDataset) {
        mDataset = myDataset;
    }*/
    public AdapterPronostico(final Context context, String url, final TextView textViewciudad, final TextView textViewfecha) {
        this.context = context;
        this.url = url;
        this.textViewciudad = textViewciudad;
        this.textViewfecha = textViewfecha;


        VolleyAplication.requestQueue.add(new XmlCustomRequest<Aemet>(url, Aemet.class, null, new Response.Listener<Aemet>() {
            @Override
            public void onResponse(Aemet response) {
                Log.i("TIEMPO", response.toString());

                textViewciudad.setText(response.getNombre() + response.getProvincia());
                textViewfecha.setText(response.getElaborado());
                mDataset = response.getPrediccion().getDias();
                notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error en la descarga de datos", Toast.LENGTH_LONG).show();
                    }
                })
        );

    }

    @Override
    public AdapterPronostico.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_item_pronostico, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterPronostico.ViewHolder holder, int position) {

        holder.fecha.setText(mDataset.get(position).getFecha());
        holder.precipitacion_media.setText((CharSequence) mDataset.get(position).getProb_precipitacion());
        holder.cota_nieve_minima.setText((CharSequence) mDataset.get(position).getCota_nieve_prov());
        holder.estado_cielo.setText(mDataset.get(position).getEstado_cielo().get(position).getDescripcion());
        holder.temperatura_max.setText(mDataset.get(position).getTemperatura().getMaxima());
        holder.temperatura_min.setText(mDataset.get(position).getTemperatura().getMinima());
        holder.sensacion_max.setText(mDataset.get(position).getSens_termica().getMaxima());
        holder.sensacion_min.setText(mDataset.get(position).getSens_termica().getMinima());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
