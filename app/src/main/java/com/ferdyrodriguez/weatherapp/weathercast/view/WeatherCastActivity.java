package com.ferdyrodriguez.weatherapp.weathercast.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ferdyrodriguez.weatherapp.R;
import com.ferdyrodriguez.weatherapp.app.BaseActivity;
import com.ferdyrodriguez.weatherapp.weathercast.presenter.WeatherCastPresenter;
import com.ferdyrodriguez.weatherapp.weathercast.presenter.WeatherCastPresenterImpl;
import com.ferdyrodriguez.weatherapp.weathercast.repository.model.WeatherCastResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherCastActivity extends BaseActivity implements WeatherCastView, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private static final int MY_PERMISSION_LOCATION = 100;
    private static final String TAG = WeatherCastActivity.class.getSimpleName();

    @BindView(R.id.txtCity)
    TextView city;
    @BindView(R.id.txtTemp)
    TextView temp;
    @BindView(R.id.minTemp)
    TextView mintemp;
    @BindView(R.id.maxTemp)
    TextView maxtemp;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.btnGoToMap)
    Button goToMap;

    private GoogleApiClient mGoogleApiClient;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private WeatherCastPresenter presenter;
    private String mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_cast);
        ButterKnife.bind(this);

        presenter = new WeatherCastPresenterImpl(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_LOCATION);
            return;
        } else {
            showLoading();
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        goToMap.setEnabled(false);
        getLocation();
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_LOCATION);
            return;
        } else {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location != null) {
                                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                try {
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    if (addresses.size() > 0){
                                        mCity = addresses.get(0).getLocality();
                                        presenter.getWeatherCast(mCity);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.d(TAG, "onSuccess: lat " + String.valueOf(location.getLatitude()));
                                Log.d(TAG, "onSuccess: lon " + String.valueOf(location.getLongitude()));
                            }
                        }
                    });
        }

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        goToMap.setEnabled(true);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeatherCast(WeatherCastResponse response) {
        city.setText(response.getName());
        temp.setText(String.valueOf(response.getMain().getTemp()));
        mintemp.setText(String.valueOf(response.getMain().getTempMin()));
        maxtemp.setText(String.valueOf(response.getMain().getTempMax()));
    }

    @OnClick(R.id.btnGoToMap)
    @Override
    public void navigateToMap() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showLoading();

                } else {
                    Toast.makeText(this, R.string.need_permission, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        showError(connectionResult.getErrorMessage());
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }
}