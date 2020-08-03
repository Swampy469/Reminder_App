package com.swampy.aggiungereelementi;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SaveLoadData {

    public static final String FILENAME = "dati_info.dat";

    public static void saveData_2(List<String> oggetto, Context context){
        try {
            FileOutputStream file_os = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutput oos = new ObjectOutputStream(file_os);
            oos.writeObject(oggetto);
            oos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static ArrayList<String> LoadData_2(Context context){
        ArrayList<String> oggetti_lista = null;
        try{
            FileInputStream file_is = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(file_is);
            oggetti_lista = (ArrayList<String>) ois.readObject();
        }catch (FileNotFoundException e ){
            oggetti_lista = new ArrayList<>();
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return oggetti_lista;
    }

}
