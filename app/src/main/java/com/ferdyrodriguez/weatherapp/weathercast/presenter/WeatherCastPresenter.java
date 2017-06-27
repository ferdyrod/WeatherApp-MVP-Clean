package com.ferdyrodriguez.weatherapp.weathercast.presenter;

/**
 * Created by ferdyrod on 6/27/17.
 */

public interface WeatherCastPresenter {

    // to Interactor
    void getWeatherCast(String lat, String lon);
    void goToMap();

    // to view
    void showWeatherCast();
    void checkLocationPermission();
}
