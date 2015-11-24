package com.salesianostriana.dam.ad.actividad10_listacompra;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText concepto, cantidad;
    Button anadir, resetear;
    ListView lista;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        concepto = (EditText) findViewById(R.id.editTextConcepto);
        cantidad = (EditText) findViewById(R.id.editCantidad);
        anadir = (Button) findViewById(R.id.btn_anadir);
        resetear = (Button) findViewById(R.id.btn_resetear);
        lista = (ListView) findViewById(R.id.listView);

        final ArrayList<String> listadoProductos = new ArrayList<String>();

            try {
                dis= new DataInputStream(openFileInput("listadodelacompra.dat"));
                int unidad;
                char puntocoma;
                String descripcion;
                while (dis.available() > 0) {
                    unidad = dis.readInt();
                    puntocoma = dis.readChar();
                    descripcion = dis.readUTF();

                    listadoProductos.add(unidad + " " + descripcion);
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

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (concepto.getText().toString().isEmpty() || cantidad.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Introduce concepto y cantidad", Toast.LENGTH_SHORT).show();
                } else {

                    DataOutputStream dos = null;
                    try {
                        dos = new DataOutputStream(openFileOutput("listadodelacompra.dat", Context.MODE_APPEND));
                        dos.writeInt(Integer.parseInt(cantidad.getText().toString()));
                        dos.writeChar(';');
                        dos.writeUTF(concepto.getText().toString());



                        listadoProductos.add(Integer.parseInt(cantidad.getText().toString()) + " " + concepto.getText().toString());

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
                    Toast.makeText(MainActivity.this, "Producto añadido", Toast.LENGTH_SHORT).show();
                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listadoProductos);
                    lista.setAdapter(adapter);
                    concepto.setText("");
                    cantidad.setText("");
                }
            }
        });

        resetear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listadoProductos.isEmpty()){

                }else {
                    try {
                        concepto.setText("");
                        cantidad.setText("");
                        adapter.clear();
                        //lista.removeAllViews();
                        listadoProductos.clear();

                        dos = new DataOutputStream(openFileOutput("listadodelacompra.dat", Context.MODE_PRIVATE));

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
                    Toast.makeText(MainActivity.this, "Listado de productos eliminado", Toast.LENGTH_SHORT).show();
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
