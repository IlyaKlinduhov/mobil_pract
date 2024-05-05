package ru.mirea.klinduhovir.mireaproject.ip;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IpClient {

    private static IpService ipService;

    public static IpService getIpService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ipinfo.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ipService = retrofit.create(IpService.class);
        return ipService;
    }

}
