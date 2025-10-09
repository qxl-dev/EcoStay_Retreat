package com.ecostay.util;
import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF = "eco_session";
    private static final String KEY_USER = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    
    public static void setUserId(Context ctx, int id){ 
        SharedPreferences sp=ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE); 
        sp.edit().putInt(KEY_USER,id).apply(); 
    }
    
    public static int getUserId(Context ctx){ 
        SharedPreferences sp=ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE); 
        return sp.getInt(KEY_USER,0); 
    }
    
    public static void setUserName(Context ctx, String name) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(KEY_USER_NAME, name).apply();
    }
    
    public static String getUserName(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sp.getString(KEY_USER_NAME, "");
    }
    
    public static void setUserEmail(Context ctx, String email) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(KEY_USER_EMAIL, email).apply();
    }
    
    public static String getUserEmail(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sp.getString(KEY_USER_EMAIL, "");
    }
    
    public static void clear(Context ctx){ 
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit().clear().apply(); 
    }
}
