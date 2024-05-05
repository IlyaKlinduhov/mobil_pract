package ru.mirea.klinduhovir.mireaproject.ip;

import com.google.gson.annotations.SerializedName;

public class IpResponse {
    @SerializedName("loc")
    private String loc;

    public double getLat(){
        return Double.parseDouble(loc.substring(0, loc.indexOf(",")));
    }

    public double getLon(){
        return Double.parseDouble(loc.substring(loc.indexOf(",")+1));
    }
}
