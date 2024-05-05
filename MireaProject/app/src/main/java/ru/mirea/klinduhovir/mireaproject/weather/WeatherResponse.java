package ru.mirea.klinduhovir.mireaproject.weather;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WeatherResponse {
    @SerializedName("main")
    private CurrentWeather currentWeather;

    public int getCurrentTemp(){
        return currentWeather.getTemp()-273;
    }

    private static class CurrentWeather{
        @SerializedName("temp")
        private double temp;

        public int getTemp(){
            return (int) Math.round(temp);
        }
    }
}