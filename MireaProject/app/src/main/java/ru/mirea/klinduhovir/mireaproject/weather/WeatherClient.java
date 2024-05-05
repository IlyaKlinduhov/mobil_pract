package ru.mirea.klinduhovir.mireaproject.weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {
    private static final String baseURL = "https://api.openweathermap.org/";
    private static WeatherService weatherService;

    public static WeatherService getWeatherService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherService = retrofit.create(WeatherService.class);
        return weatherService;
    }
}