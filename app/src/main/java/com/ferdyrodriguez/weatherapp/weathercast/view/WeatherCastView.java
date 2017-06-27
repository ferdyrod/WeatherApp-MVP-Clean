package com.ferdyrodriguez.weatherapp.weathercast.view;

/**
 * Created by ferdyrod on 6/27/17.
 */

public interface WeatherCastView {

    void showLoading();
    void hideLoading();
    void showError(String message);
    void hideError();

    void showWeatherCast();

}
