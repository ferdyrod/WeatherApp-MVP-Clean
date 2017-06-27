package com.ferdyrodriguez.weatherapp.weathercast.view;

import com.ferdyrodriguez.weatherapp.weathercast.repository.model.WeatherCastResponse;

/**
 * Created by ferdyrod on 6/27/17.
 */

public interface WeatherCastView {

    void showLoading();
    void hideLoading();
    void showError(String message);

    void showWeatherCast(WeatherCastResponse response);
    void navigateToMap();

}
