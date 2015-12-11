package com.salesianostriana.ad.greendaosampleproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText title, body;
    Button button;
    ListView listView;
    NoteDao noteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (EditText) findViewById(R.id.title);
        body = (EditText) findViewById(R.id.body);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().equalsIgnoreCase("") || body.getText().toString().equalsIgnoreCase("")) {

                } else {
                    Note n = new Note(null, title.getText().toString(), body.getText().toString(), new java.util.Date());
                    noteDao.insert(n);
                    listView.setAdapter(new NoteAdapter(MainActivity.this));

                }
            }
        });


        // Lineas importantes:
        // 1. Crea la base de datos
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes", null);
        // 2. Obtenemos la base de datos
        SQLiteDatabase db = helper.getWritableDatabase();
        // Nos permiten conectarnos a los CRUD
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        // Obtiene todos los objetos de este tipo y hacer los CRUD (crear, modificar, etc)
        noteDao = daoSession.getNoteDao();

        listView.setAdapter(new NoteAdapter(this));

    }

    private class NoteAdapter extends BaseAdapter {


        Context mContext;
        List<Note> data;


        public NoteAdapter(Context mContext /*,PublicWall data*/) {
            //this.data = data;
            this.mContext = mContext;
            data = noteDao.queryBuilder().list();


        }

        @Override
        public int getCount() {
            //return data.getNotes().size();
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
            //return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);

            TextView title = (TextView) row.findViewById(android.R.id.text1);
            TextView body  = (TextView) row.findViewById(android.R.id.text2);

            Note item = (Note) getItem(position);

            title.setText(item.getText());
            body.setText(item.getComment());

            return row;
        }
    }

}
