package com.swampy.aggiungereelementi;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {

    //Debugging
    private static final String TAG = "Dialog";

    private EditText testo;
    private DialogListener listener;

    @Override
    public android.app.Dialog onCreateDialog( Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        builder.setView(view).setTitle("Inserisci dei dati").setNegativeButton("Cancella", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Aggiungi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String testo_edit = testo.getText().toString();
                listener.applyText(testo_edit);
            }
        });
        testo = view.findViewById(R.id.scrivi);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
           throw  new ClassCastException(context.toString()+"errore");
        }
    }

    public interface DialogListener{
        void applyText(String testo);
    }
}
