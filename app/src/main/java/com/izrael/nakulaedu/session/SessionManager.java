package com.izrael.nakulaedu.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String KEY_NAME = "vOJo2UwWcPTSsJu5crby";
    private static final String IS_LOGGEDIN = "Pq2jl888JlWUvMOsQqwm";
    private static final String ID_SISWA = "gJXw0aAreyfUMJFbCfFW";
    private static final String LOGO = "wqj0aAreyfUMJFbCfFW";
    private static final String FOTO = "fotonwkahlhbdda";
    private static final String NAMA = "kicfKeG26u1jadrhGNWm";
    private static final String SEKOLAH = "aOgam60gF4ZBKNIX93sz";
    private static final String KODEKELAS = "wqiehjnqwjsdifjsdn";
    private static final String IDSEKOLAH = "GM1nZMcytGzkGORQ2lAI";
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

    public void set_nama(String value){
        spEditor.putString(NAMA, value);
        spEditor.commit();
    }

    public void set_SEKOLAH(String value){
        spEditor.putString(SEKOLAH, value);
        spEditor.commit();
    }

    public void set_IDSEKOLAH(String value){
        spEditor.putString(IDSEKOLAH, value);
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
    public void set_LOGO(String value){
        spEditor.putString(LOGO, value);
        spEditor.commit();
    }

    public String get_LOGO(){
        return sp.getString(LOGO,"");
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


    public String get_nama(){
        return sp.getString(NAMA,"");
    }

    public String get_SEKOLAH(){
        return sp.getString(SEKOLAH,"");
    }

    public String get_IDSEKOLAH(){
        return sp.getString(IDSEKOLAH,"");
    }
}