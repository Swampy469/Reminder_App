package com.swampy.aggiungereelementi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener {

    private MFabButtons mFab;

    SharedClass shared;

    public ArrayList<String> lista;
    public ArrayAdapter adapter;
    public ListView view_lista;
    public TextView github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared = new SharedClass(MainActivity.this);

        if(shared.NightMode()) {
            setTheme(R.style.darktheme);
        }else  setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        github = findViewById(R.id.git_text);

        lista = SaveLoadData.LoadData_2(this);

        view_lista = findViewById(R.id.lista_activity);

        adapter = new ArrayAdapter(this,R.layout.custom_list, lista);
        view_lista.setAdapter(adapter);

        view_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                TextView textView = (TextView) view;
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                final AlertDialog.Builder alertbuilder = new AlertDialog.Builder(MainActivity.this).setCancelable(false).setNegativeButton("No", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              TextView textView = (TextView) view;
                              textView.setPaintFlags(textView.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
                          }
                      }).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              lista.remove(position);
                              view_lista.setAdapter(adapter);
                              SaveLoadData.saveData_2(lista, getApplicationContext());
                          }
                      }).setMessage("Cancellare il dato selezionato?");
                AlertDialog alert = alertbuilder.create();
                alert.show();
            }
        });
        fButtons();
        }

    public void Dialog(){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    private void fButtons() {
        FloatingActionButton fab_button_1 = findViewById(R.id.fab_button_1);
        FloatingActionButton fab_button_2 = findViewById(R.id.fab_button_2);
        FloatingActionButton fab_button_3 = findViewById(R.id.fab_button_3);
        mFab = new  MFabButtons(fab_button_1, fab_button_2, fab_button_3);

        fab_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFab.menuCheck();
            }
        });

        fab_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFab.menuCheck();
                Dialog();
            }
        });

        fab_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFab.menuCheck();
                final AlertDialog.Builder alertbuilder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Eliminare i dati ?").setMessage("Sei sicuro di voler eliminare tutti i dati?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.clear();
                        SaveLoadData.saveData_2(lista,getApplicationContext());
                    }
                }).setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog alert = alertbuilder.create();
                alert.show();
            }
        });
    }

    public void onClick(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/Swampy469"));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.item_1:
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_info, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setView(view).setTitle("Info").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
                break;

            case R.id.item_2:
                startActivity(new Intent(getApplicationContext(),Impostazioni.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void applyText(String testo) {
        if(!TextUtils.isEmpty(testo)){
            adapter.add(testo);
            SaveLoadData.saveData_2(lista,this);
            notifyUser("Salvato");

        }else {
            notifyUser("Devi inserire qualcosa");
        }
    }

    public void notifyUser(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }
}