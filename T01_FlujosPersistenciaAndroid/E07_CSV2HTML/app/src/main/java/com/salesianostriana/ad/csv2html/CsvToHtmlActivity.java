package com.salesianostriana.ad.csv2html;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CsvToHtmlActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv_to_html);

        webView = (WebView) findViewById(R.id.webView);
        String st = "";
        StringBuilder sb = new StringBuilder(st);

        BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.ejemplocsv)));
        String lectura = "";
        sb = new StringBuilder(lectura);
        try {
            while ((lectura = br.readLine()) != null){
                if (lectura.contains(";")) {
                    lectura = "<tr><td>" + lectura.replace(";", "</td>\n<td>") + "</td></tr>\n";
                    sb = sb.append(lectura);
                }else if(lectura.contains(",")){
                    lectura = "<tr><td>"+ lectura.replace(",","</td>\n<td>") + "</td></tr>\n";
                    sb = sb.append(lectura);
                }else if(lectura.contains("\t")){
                    lectura = "<tr><td>"+ lectura.replace("\t","</td>\n<td>") + "</td></tr>\n";
                    sb = sb.append(lectura);
                }
                Log.i("Linea escrita", lectura);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        muestraHTML("<!DOCTYPE html><html lang=\"es\"><head><meta charset=\"utf-8\"></head><body><table>"+sb+"</table></body></html>");
    }

    private void muestraHTML(String html) {
        webView.loadData(html,"text/html","utf-8");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_csv_to_html, menu);
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
