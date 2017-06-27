package com.ferdyrodriguez.weatherapp.map.presenter;

import com.ferdyrodriguez.weatherapp.map.view.MapView;

/**
 * Created by ferdyrod on 6/27/17.
 */

public class MapPresenterImpl implements MapPresenter {

    private MapView view;

    public MapPresenterImpl(MapView view) {
        this.view = view;

    }

    @Override
    public void addMarker(double latitude, double longitude) {
        view.showMarker(latitude, longitude);
    }
}
