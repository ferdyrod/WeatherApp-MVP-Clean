package com.ferdyrodriguez.weatherapp.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by ferdyrod on 6/27/17.
 */

public class BaseActivity extends AppCompatActivity {

    public GoogleApiClient mGoogleApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApi = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();

    }
}
