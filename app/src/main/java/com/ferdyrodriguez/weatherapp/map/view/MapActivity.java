package com.ferdyrodriguez.weatherapp.map.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ferdyrodriguez.weatherapp.R;
import com.ferdyrodriguez.weatherapp.app.Constants;
import com.ferdyrodriguez.weatherapp.map.presenter.MapPresenter;
import com.ferdyrodriguez.weatherapp.map.presenter.MapPresenterImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements MapView, OnMapReadyCallback {

    private GoogleMap mMap;
    private MapPresenter presenter;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        presenter = new MapPresenterImpl(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            latitude = extras.getDouble(Constants.LATITUDE);
            longitude = extras.getDouble(Constants.LONGITUDE);
            presenter.addMarker(latitude, longitude);
        }


    }

    @Override
    public void showMarker(double latitude, double longitude) {
        LatLng position = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(position).title(getResources().getString(R.string.marker_info)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 12));

    }
}
