package com.ferdyrodriguez.weatherapp.network;

import com.ferdyrodriguez.weatherapp.app.Constants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ferdyrod on 6/26/17.
 */

public class RestApiImpl {

    public static <T> T createRetrofitService(final Class<T> clazz){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalUrl = original.url();

                        HttpUrl newUrl = originalUrl.newBuilder()
                                .addQueryParameter("APPID", Constants.APIKEY)
                                .addQueryParameter("units", Constants.UNIT)
                                .build();

                        Request.Builder requestBuilder = original.newBuilder()
                                .url(newUrl);
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                        }
                    })
                .build();

        final Retrofit restAdapter =
                new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

        return restAdapter.create(clazz);
    }
}
