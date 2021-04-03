package com.example.dazuoye;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class DzyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
