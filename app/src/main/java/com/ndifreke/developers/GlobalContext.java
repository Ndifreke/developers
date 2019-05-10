package com.ndifreke.developers;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
