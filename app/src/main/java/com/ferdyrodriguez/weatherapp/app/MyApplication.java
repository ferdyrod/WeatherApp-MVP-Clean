package com.ferdyrodriguez.weatherapp.app;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by ferdyrod on 6/27/17.
 */

public class MyApplication extends Application {

    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();

    }
}
