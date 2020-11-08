package com.example.tpinmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class ApiUser {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("USER",0);
        }
        return sp;
    }

    public static void setUser(Context context, String nomApe, String mail){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("NomApe", nomApe);
        editor.putString("Mail", mail);
        editor.apply();
        editor.commit();
    }

    public static List<String> getUser(Context context){
        List<String> parametros= new ArrayList<>();
        SharedPreferences sp = conectar(context);
        parametros.add(sp.getString("NomApe","-1"));
        parametros.add(sp.getString("Mail","-1"));
        return parametros;
    }

    public static void limpiar(Context context){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}

