package com.example.tpinmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

public class ApiToken {

    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("TOKEN",0);
        }
        return sp;
    }

    public static void setToken(Context context, String bearerToken){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("BearerToken", bearerToken);
        editor.apply();
        editor.commit();
    }

    public static String getToken(Context context){
        SharedPreferences sp = conectar(context);
        return sp.getString("BearerToken","-1");
    }

    public static void limpiar(Context context){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
