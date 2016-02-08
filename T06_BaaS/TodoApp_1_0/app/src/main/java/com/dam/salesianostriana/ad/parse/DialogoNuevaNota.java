package com.dam.salesianostriana.ad.parse;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

/**
 * Created by flopez on 06/02/2016.
 */
public class DialogoNuevaNota extends DialogFragment {

    String objectId;

    public DialogoNuevaNota() {
    }
    public DialogoNuevaNota(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);

        builder.setView(inflater.inflate(R.layout.nueva_nota, null))
                .setView(input)
                .setMessage("Concepto")
                .setTitle("Insertar nueva nota")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        // Creación de una nota
                        ParseObject nuevaNota = new ParseObject("Todo");

                        String nota = input.getText().toString();
                        //Log.i("NOTA",nota);
                        /*Toast tostada = Toast.makeText(getContext(), "Nueva nota creada " + objectId, Toast.LENGTH_SHORT);
                        tostada.setGravity(Gravity.BOTTOM | Gravity.LEFT, 24, 24);
                        tostada.show();*/

                        nuevaNota.put("Concepto", nota);
                        nuevaNota.put("Fecha", new Date());
                        nuevaNota.put("user_id", ParseUser.getCurrentUser());

                        nuevaNota.saveInBackground();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
