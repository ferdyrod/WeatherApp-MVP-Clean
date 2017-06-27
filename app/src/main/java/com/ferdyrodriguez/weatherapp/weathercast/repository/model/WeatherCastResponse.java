package com.ferdyrodriguez.weatherapp.weathercast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ferdyrod on 6/26/17.
 */

public class WeatherCastResponse {

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("name")
    @Expose
    private String name;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}