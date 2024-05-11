package ru.mirea.klinduhovir.mireaproject.map;

import java.io.Serializable;

public class Location implements Serializable {
    private double latitude;
    private double longitude;
    private String name;
    private String description;
    private String address;

    public Location(double latitude, double longitude, String name, String description, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }
}
