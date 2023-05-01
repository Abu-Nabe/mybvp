package com.example.mybvp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PreferenceUtils {

    public PreferenceUtils(){

    }

    public static boolean saveType(String type, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.key_type, type);
        prefsEditor.apply();
        return true;
    }

    public static String getType(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.key_type, null);
    }
}
