package com.ferdyrodriguez.weatherapp.weathercast.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ferdyrodriguez.weatherapp.R;

public class WeatherCastActivity extends AppCompatActivity implements WeatherCastView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_cast);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void hideError() {

    }

    @Override
    public void showWeatherCast() {

    }
}