package com.example.applogs;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import java.util.zip.CheckedOutputStream;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Stetho.initializeWithDefaults(this);
    }

    public static Context getAppContext(){
        return context;
    }

}
