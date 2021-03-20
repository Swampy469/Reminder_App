package com.swampy.aggiungereelementi;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedClass{

    private final Context context;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;

    public SharedClass(Context context){
        this.context = context;
    }

    public SharedPreferences sharedPreferences(){
        return shared = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public Boolean NightMode(){
        return sharedPreferences().getBoolean("NightMode",false);
    }

    public void setNighMode(Boolean bool){
        editor = sharedPreferences().edit();
        editor.putBoolean("NightMode", bool);
        editor.apply();
    }
}
