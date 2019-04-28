package com.example.applogs.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static PreferenceHelper instance;
    private static SharedPreferences sPreferences;
    private static SharedPreferences.Editor sPreferenceEditor;
    public static final String KEY_USERNAME = "user_name";
    public static final String PREFERENCE_NAME = "com.example.applogs";
    public static final String KEY_TOKEN = "token";

    private PreferenceHelper() {
    }

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (PreferenceHelper.class) {
                if (instance == null) {
                    instance = new PreferenceHelper();
                    sPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
                    sPreferenceEditor = sPreferences.edit();

                }
            }
        }
        return instance;
    }

    //getter
    public String getString(String key)
    {
        return sPreferences.getString(key, null);
    }

    //setter
    public void setString(String key, String value) {
        sPreferenceEditor.putString(key, value).apply();
    }


}
