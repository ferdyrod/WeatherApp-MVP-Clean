package com.ferdyrodriguez.weatherapp.weathercast.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ferdyrod on 6/26/17.
 */

public class Main {
    @SerializedName("temp")
    @Expose
    private Integer temp;
    @SerializedName("temp_min")
    @Expose
    private Integer tempMin;
    @SerializedName("temp_max")
    @Expose
    private Integer tempMax;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

}
