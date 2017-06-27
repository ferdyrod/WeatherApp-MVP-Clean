package com.ferdyrodriguez.weatherapp.network;

import com.ferdyrodriguez.weatherapp.app.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ferdyrod on 6/26/17.
 */

public class RestApiImpl {

    public static <T> T createRetrofitService(final Class<T> clazz){
        final Retrofit restAdapter =
                new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();

        return restAdapter.create(clazz);
    }
}
