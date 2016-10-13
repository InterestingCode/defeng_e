package com.diesel.htweather.map;

public class LocationAddress {

    public String province;

    public String city;

    public String district;

    public String address;

    public double latitude;

    public double longitude;

    public String getLocation() {
        return province + city + district;
    }

    @Override
    public String toString() {
        return "LocationAddress{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
