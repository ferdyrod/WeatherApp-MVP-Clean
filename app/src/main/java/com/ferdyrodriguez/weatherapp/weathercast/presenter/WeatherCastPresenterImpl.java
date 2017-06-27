package com.ferdyrodriguez.weatherapp.weathercast.presenter;

import com.ferdyrodriguez.weatherapp.weathercast.interactor.WeatherCastInteractor;
import com.ferdyrodriguez.weatherapp.weathercast.interactor.WeatherCastInteractorImpl;
import com.ferdyrodriguez.weatherapp.weathercast.repository.model.WeatherCastResponse;
import com.ferdyrodriguez.weatherapp.weathercast.view.WeatherCastView;

/**
 * Created by ferdyrod on 6/27/17.
 */

public class WeatherCastPresenterImpl implements WeatherCastPresenter {

    private WeatherCastView view;
    private WeatherCastInteractor interactor;

    public WeatherCastPresenterImpl(WeatherCastView view) {
        this.view = view;
        interactor = new WeatherCastInteractorImpl(this);
    }

    @Override
    public void getWeatherCast(String city) {
        interactor.getWeatherCast(city);
    }

    @Override
    public void showWeatherCast(WeatherCastResponse response) {
        view.hideLoading();
        view.showWeatherCast(response);
    }

    @Override
    public void showWeatherCastError(String error) {
        view.showError(error);
    }

}
