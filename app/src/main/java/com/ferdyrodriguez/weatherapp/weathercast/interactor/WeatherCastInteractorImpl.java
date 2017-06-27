package com.ferdyrodriguez.weatherapp.weathercast.interactor;

import com.ferdyrodriguez.weatherapp.weathercast.presenter.WeatherCastPresenter;
import com.ferdyrodriguez.weatherapp.weathercast.repository.WeatherCastRepository;
import com.ferdyrodriguez.weatherapp.weathercast.repository.WeatherRepositoryImpl;

/**
 * Created by ferdyrod on 6/27/17.
 */

public class WeatherCastInteractorImpl implements WeatherCastInteractor {

    private WeatherCastPresenter presenter;
    private WeatherCastRepository repository;

    public WeatherCastInteractorImpl(WeatherCastPresenter presenter) {
        this.presenter = presenter;
        repository = new WeatherRepositoryImpl(presenter);

    }

    @Override
    public void getWeatherCast(String city) {
        repository.getWeatherFromApi(city);

    }
}
