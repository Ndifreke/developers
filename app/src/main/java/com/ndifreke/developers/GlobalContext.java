package com.ndifreke.developers;

import android.app.Application;
import android.content.Context;

public class GlobalContext extends Application {
    public static Context globalContext;

public GlobalContext(){
}
    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = this;
    }
}
