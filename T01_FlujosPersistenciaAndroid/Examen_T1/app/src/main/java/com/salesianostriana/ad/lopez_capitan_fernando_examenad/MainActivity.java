package com.salesianostriana.ad.lopez_capitan_fernando_examenad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText fecha,tarea ;
    TextView listadodetareas;
    Button anadir;
    DataInputStream dis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fecha = (EditText) findViewById(R.id.editTextFecha);
        tarea = (EditText) findViewById(R.id.editTextTarea);
        listadodetareas = (TextView) findViewById(R.id.editTextListadoTareas);
        anadir = (Button) findViewById(R.id.btn_add);

        final ArrayList<String> listado = new ArrayList<String>();

        try {
            dis= new DataInputStream(openFileInput("lista_todo.txt"));
            String fecha;
            char puntocoma;
            String tarea;
            while (dis.available() > 0) {
                fecha = dis.readUTF();
                puntocoma = dis.readChar();
                tarea = dis.readUTF();

                listado.add("["+fecha + "]\n" + tarea);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        StringBuilder list = new StringBuilder();
        for (int i = 0; i < listado.size(); i++) {
            listadodetareas.setText(list.append(listado.get(i) + "\n"));
        }

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fecha.getText().toString().isEmpty() || tarea.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Introduce fecha y tarea", Toast.LENGTH_SHORT).show();
                } else {

                    DataOutputStream dos = null;
                    try {
                        dos = new DataOutputStream(openFileOutput("lista_todo.txt", Context.MODE_APPEND));
                        dos.writeUTF(fecha.getText().toString());
                        dos.writeChar(';');
                        dos.writeUTF(tarea.getText().toString());

                        listado.add("["+fecha.getText().toString() + "]\n" + tarea.getText().toString());

                        StringBuilder list = new StringBuilder();
                        for (int i = 0; i < listado.size(); i++) {
                            listadodetareas.setText(list.append(listado.get(i) + "\n"));
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            dos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(MainActivity.this, "Tarea añadida", Toast.LENGTH_SHORT).show();
                    fecha.setText("");
                    tarea.setText("");
                }
            }
        });


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
}
