package com.swampy.aggiungereelementi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Objects;

public class Impostazioni extends AppCompatActivity {

    SharedClass shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared = new SharedClass(Impostazioni.this);
        if(shared.NightMode()) {
            setTheme(R.style.darktheme);
        }
        else  setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Switch myswitch = findViewById(R.id.dark);

        myswitch.setChecked(shared.NightMode());

        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shared.setNighMode(isChecked);
                restartApp();
            }
        });
    }
    public void restartApp () {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Riavvio").setMessage("L'app verrà riavviata.").setPositiveButton("Riavvia", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home) startActivity(new Intent(getApplicationContext(), MainActivity.class)); finish();
        return super.onOptionsItemSelected(item);
        }
    }
