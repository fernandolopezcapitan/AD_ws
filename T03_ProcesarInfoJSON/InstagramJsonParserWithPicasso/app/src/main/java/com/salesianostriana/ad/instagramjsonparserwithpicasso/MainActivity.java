package com.salesianostriana.ad.instagramjsonparserwithpicasso;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.salesianostriana.ad.instagramjsonparserwithpicasso.model.Image;
import com.salesianostriana.ad.instagramjsonparserwithpicasso.model.ImageData;
import com.salesianostriana.ad.instagramjsonparserwithpicasso.model.Instagram;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ListView list;
    Instagram data;
    //Diálogo con la barra de progreso
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list = (ListView) findViewById(R.id.listView);

        new GetInstagramPopularContentTask().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class GetInstagramPopularContentTask extends AsyncTask<Void, Void, Instagram> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Descargando información...");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Instagram doInBackground(Void... params) {


            URL url = null;
            BufferedReader br = null;
            Instagram result = null;


            try {
                url = new URL("https://api.instagram.com/v1/media/popular?client_id=c15d8acfdb2a4511940092c9b208eab1");
                br = new BufferedReader(new InputStreamReader(url.openStream()));

                Gson gson = new Gson();

                result = gson.fromJson(br, Instagram.class);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;

        }

        @Override
        protected void onPostExecute(Instagram instagram) {
            super.onPostExecute(instagram);
            progressDialog.dismiss();
            data = instagram;

            list.setAdapter(new SimpleInstagramAdapter(data));

        }
    }


    private class SimpleInstagramAdapter extends BaseAdapter {


        private Instagram data;

        public SimpleInstagramAdapter(Instagram _data) {
            data = _data;
        }

        @Override
        public int getCount() {
            return data.getData().length;
        }

        @Override
        public Object getItem(int position) {
            return data.getData()[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_layout, null);
            }

            Image img =  ((ImageData) getItem(position)).getImages().getStandard_resolution();

            ImageView imgView = (ImageView) v.findViewById(R.id.imageView);

            Picasso.with(getBaseContext()).load(img.getUrl())
                    .placeholder(R.drawable.box_placeholder)
                    .into(imgView);


            return v;
        }
    }



}
