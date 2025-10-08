package com.ecostay.util;
import android.content.Context;
import android.content.SharedPreferences;
public class SessionManager {
    private static final String PREF = "eco_session";
    private static final String KEY_USER = "user_id";
    public static void setUserId(Context ctx, int id){ SharedPreferences sp=ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE); sp.edit().putInt(KEY_USER,id).apply(); }
    public static int getUserId(Context ctx){ SharedPreferences sp=ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE); return sp.getInt(KEY_USER,0); }
    public static void clear(Context ctx){ ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit().clear().apply(); }
}
