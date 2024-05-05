package ru.mirea.klinduhovir.mireaproject.ip;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IpService {
    @GET("/json")
    Call<IpResponse> getIp();
}
