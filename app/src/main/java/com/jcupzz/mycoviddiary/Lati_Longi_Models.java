package com.jcupzz.mycoviddiary;

import com.google.firebase.Timestamp;

public class Lati_Longi_Models {

    Timestamp timestamp;

    public Lati_Longi_Models(Timestamp timestamp, String day, String date, String lati_S, String longi_S) {
        this.timestamp = timestamp;
        this.day = day;
        this.date = date;
        this.lati_S = lati_S;
        this.longi_S = longi_S;
    }

    String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    String date;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }



    public String getLati_S() {
        return lati_S;
    }

    public void setLati_S(String lati_S) {
        this.lati_S = lati_S;
    }

    public String getLongi_S() {
        return longi_S;
    }

    public void setLongi_S(String longi_S) {
        this.longi_S = longi_S;
    }

    String lati_S;
    String longi_S;


}
