package ru.mirea.klinduhovir.yandexmaps;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {
    private final String API_KEY = "bced2ebe-1003-49f4-a911-c645f4d9df00";

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(API_KEY);
    }
}
