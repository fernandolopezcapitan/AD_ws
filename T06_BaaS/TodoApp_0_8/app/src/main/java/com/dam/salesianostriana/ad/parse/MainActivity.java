package com.dam.salesianostriana.ad.parse;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends AppCompatActivity {

    ListView listaNotas;
    ToggleButton btn_todos, btn_hoy, btn_ayer, btn_orden;
    ParseQueryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNotas = (ListView) findViewById(R.id.listView);
        btn_todos = (ToggleButton) findViewById(R.id.toggle_todos);
        btn_hoy = (ToggleButton) findViewById(R.id.toggle_hoy);
        btn_ayer = (ToggleButton) findViewById(R.id.toggle_ayer);
        btn_orden = (ToggleButton) findViewById(R.id.toggle_ascend);

        btn_orden.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    adapter = new ParseQueryAdapter(MainActivity.this, new ParseQueryAdapter.QueryFactory<ParseObject>() {
                        @Override
                        public ParseQuery<ParseObject> create() {
                            ParseQuery query = new ParseQuery("Todo");
                            query.orderByAscending("Concepto");
                        return query;
                        }
                    });
                    listaNotas.setAdapter(adapter);
                }else {
                    adapter = new ParseQueryAdapter(MainActivity.this,"Todo");
                    listaNotas.setAdapter(adapter);
                }
            }
        });

        //ParseQueryAdapter adapter = new ParseQueryAdapter(this,"Todo");
        //listaNotas.setAdapter(adapter);

        //adapter.notifyDataSetChanged();

    }

}
