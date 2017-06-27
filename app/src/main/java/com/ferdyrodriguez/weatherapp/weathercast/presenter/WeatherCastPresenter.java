package com.ferdyrodriguez.weatherapp.weathercast.presenter;

import com.ferdyrodriguez.weatherapp.weathercast.repository.model.WeatherCastResponse;

/**
 * Created by ferdyrod on 6/27/17.
 */

public interface WeatherCastPresenter {

    // to Interactor
    void getWeatherCast(String city);

    // to view
    void showWeatherCast(WeatherCastResponse response);
    void showWeatherCastError(String error);
}
