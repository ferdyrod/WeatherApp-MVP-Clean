package com.ferdyrodriguez.weatherapp.weathercast.repository;

import android.util.Log;

import com.ferdyrodriguez.weatherapp.network.RestApi;
import com.ferdyrodriguez.weatherapp.network.RestApiImpl;
import com.ferdyrodriguez.weatherapp.weathercast.presenter.WeatherCastPresenter;
import com.ferdyrodriguez.weatherapp.weathercast.repository.model.WeatherCastResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ferdyrod on 6/27/17.
 */

public class WeatherRepositoryImpl implements WeatherCastRepository, Callback<WeatherCastResponse> {

    private WeatherCastPresenter presenter;
    private RestApi restApi;

    public WeatherRepositoryImpl(WeatherCastPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getWeatherFromApi(String city) {
        restApi = RestApiImpl.createRetrofitService(RestApi.class);
        Call<WeatherCastResponse> call = restApi.getWeatherCast(city);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<WeatherCastResponse> call, Response<WeatherCastResponse> response) {
        if(response.isSuccessful()) {
            WeatherCastResponse weatherCastResponse = response.body();
            presenter.showWeatherCast(weatherCastResponse);
        } else {
            try {
                presenter.showWeatherCastError(response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<WeatherCastResponse> call, Throwable t) {
        presenter.showWeatherCastError(t.getMessage());
        Log.d("API Call", "onFailure: " + t.getLocalizedMessage());
    }
}
