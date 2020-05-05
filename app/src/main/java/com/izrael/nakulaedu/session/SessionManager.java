package com.izrael.nakulaedu.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String KEY_NAME = "vOJo2UwWcPTSsJu5crby";
    private static final String IS_LOGGEDIN = "Pq2jl888JlWUvMOsQqwm";
    private static final String ID_SISWA = "gJXw0aAreyfUMJFbCfFW";
    private static final String TAHUN = "wqj0aAreyfUMJFbCfFW";
    private static final String FOTO = "fotonwkahlhbdda";
    private static final String NIK = "YxJW6t3KBIPgGYJYLINx";
    private static final String NAMA = "kicfKeG26u1jadrhGNWm";
    private static final String TELPON = "aOgam60gF4ZBKNIX93sz";
    private static final String KODEKELAS = "wqiehjnqwjsdifjsdn";
    private static final String ID_JENIS_KELAMIN = "GM1nZMcytGzkGORQ2lAI";
   private  static final String NISN = "jkaskasjssaassasa781381a";
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    public SessionManager(Context context){
        sp = context.getSharedPreferences(KEY_NAME, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }



    public void set_is_loggedin(){
        spEditor.putBoolean(IS_LOGGEDIN, true);
        spEditor.commit();
    }

    public void set_is_loggedout(){
        spEditor.putBoolean(IS_LOGGEDIN, false);
        spEditor.commit();
        spEditor.clear();
        spEditor.apply();
    }

    public void set_ID_SISWA(String value){
        spEditor.putString(ID_SISWA, value);
        spEditor.commit();
    }
    public void set_Foto(String value){
        spEditor.putString(FOTO, value);
        spEditor.commit();
    }
    public void set_NISN(String value){
        spEditor.putString(NISN, value);
        spEditor.commit();
    }

    public void set_nik(String value){
        spEditor.putString(NIK, value);
        spEditor.commit();
    }

    public void set_nama(String value){
        spEditor.putString(NAMA, value);
        spEditor.commit();
    }

    public void set_TELPON(String value){
        spEditor.putString(TELPON, value);
        spEditor.commit();
    }

    public void set_ID_JENIS_KELAMIN(String value){
        spEditor.putString(ID_JENIS_KELAMIN, value);
        spEditor.commit();
    }

    public void set_KODEKELAS(String value){
        spEditor.putString(KODEKELAS, value);
        spEditor.commit();
    }

    public String get_KODEKELAS(){
        return sp.getString(KODEKELAS,"");
    }
    public String get_Foto(){
        return sp.getString(FOTO,"");
    }
    public void set_TAHUN(String value){
        spEditor.putString(TAHUN, value);
        spEditor.commit();
    }

    public String get_TAHUN(){
        return sp.getString(TAHUN,"");
    }


    public boolean is_loggedin(){
        return sp.getBoolean(IS_LOGGEDIN,false);
    }

    public String get_ID_SISWA(){
        return sp.getString(ID_SISWA,"");
    }
    public String get_NISN(){
        return sp.getString(NISN,"");
    }

    public String get_nik(){
        return sp.getString(NIK,"");
    }

    public String get_nama(){
        return sp.getString(NAMA,"");
    }

    public String get_TELPON(){
        return sp.getString(TELPON,"");
    }

    public String get_ID_JENIS_KELAMIN(){
        return sp.getString(ID_JENIS_KELAMIN,"");
    }
}