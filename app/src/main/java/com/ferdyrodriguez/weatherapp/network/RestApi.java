package com.ferdyrodriguez.weatherapp.network;

import com.ferdyrodriguez.weatherapp.weathercast.repository.model.WeatherCastResponse;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by ferdyrod on 6/26/17.
 */

public interface RestApi {

    @GET("data/2.5/weather?")
    Call<WeatherCastResponse> getWeatherCast();

}
