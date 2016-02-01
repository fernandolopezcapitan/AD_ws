package com.dam.salesianostriana.ad.parse;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by flopez on 21/01/2016.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "ajvuPweagHYQEH8i9wbCCiOr0HDEjfY9PoEefXR3", "EyFvsOlxAuEyCx10tXkQWePlL7uRX9a22c77qDiI");
    }
}
