package com.salesianostriana.dam.ad.ad_listacompra4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView lista;
    private EditText concepto, cantidad;
    private Button add, save, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (TextView) findViewById(R.id.textViewLista);
        concepto = (EditText) findViewById(R.id.editTextConcepto);
        cantidad = (EditText) findViewById(R.id.editTextCantidad);
        add = (Button) findViewById(R.id.btnAdd);
        save = (Button) findViewById(R.id.btnSave);
        reset = (Button) findViewById(R.id.btnReset);
        final ArrayList<String> listaCompra = new ArrayList<String>();
        final ArrayList<Integer> listaCantidades = new ArrayList<Integer>();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cantidad.getText().toString().isEmpty() || concepto.getText().toString().isEmpty()) {
                }else {
                    listaCompra.add(concepto.getText().toString());
                    listaCantidades.add(Integer.parseInt(cantidad.getText().toString()));

                    StringBuilder carrito = new StringBuilder();
                    for (int i = 0; i < listaCantidades.size(); i++) {
                        lista.setText(carrito.append("[" + listaCantidades.get(i) + "] "+listaCompra.get(i)+"\n"));
                    }
                    concepto.setText("");
                    cantidad.setText("");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataOutputStream dos = null;
                try {
                    dos = new DataOutputStream(openFileOutput("listaCompra.dat", Context.MODE_PRIVATE));

                    for (int i = 0; i < listaCantidades.size(); i++) {
                        dos.writeInt(Integer.parseInt(listaCantidades.get(i).toString()));
                        dos.writeUTF(listaCompra.get(i).toString());
                        Log.i("PRODUCTOS AÑADIDOS: ", listaCompra.get(i));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (dos != null) {
                        try {
                            dos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                concepto.setText("");
                cantidad.setText("");
                lista.setText("");
                listaCantidades.clear();
                listaCompra.clear();
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
