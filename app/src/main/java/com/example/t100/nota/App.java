package com.example.t100.nota;

import android.app.Application;

import io.objectbox.BoxStore;


public class App extends Application {
    private static BoxStore store;

    @Override
    public void onCreate() {
        super.onCreate();

        store = MyObjectBox.builder().androidContext(App.this).build();
    }

    public static BoxStore getBoxStore(){
        return store;
    }
}
