package salesianostriana.com.ad.clienteaemet;

import android.app.Application;

import com.android.volley.RequestQueue;

/**
 * Created by flopez on 28/11/2015.
 */
public class VolleyAplication extends Application{

    public static RequestQueue requestQueue;


    @Override
    public void onCreate() {
        super.onCreate();

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

    }
}
