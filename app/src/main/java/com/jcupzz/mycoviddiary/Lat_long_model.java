package com.jcupzz.mycoviddiary;

public class Lat_long_model {

    String lat;
    String Lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

    public Lat_long_model(String lat, String lon) {
        this.lat = lat;
        Lon = lon;
    }
}
