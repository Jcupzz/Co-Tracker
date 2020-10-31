package com.jcupzz.cotracker;


import com.google.firebase.Timestamp;

public class Models {

    String qr_result;
    Timestamp qr_timestamp;
    String qr_day;
    String qr_date;

    public String getQr_result() {
        return qr_result;
    }

    public void setQr_result(String qr_result) {
        this.qr_result = qr_result;
    }

    public Timestamp getQr_timestamp() {
        return qr_timestamp;
    }

    public void setQr_timestamp(Timestamp qr_timestamp) {
        this.qr_timestamp = qr_timestamp;
    }

    public String getQr_day() {
        return qr_day;
    }

    public void setQr_day(String qr_day) {
        this.qr_day = qr_day;
    }

    public String getQr_date() {
        return qr_date;
    }

    public void setQr_date(String qr_date) {
        this.qr_date = qr_date;
    }

    public Models(String qr_result, Timestamp qr_timestamp, String qr_day, String qr_date) {
        this.qr_result = qr_result;
        this.qr_timestamp = qr_timestamp;
        this.qr_day = qr_day;
        this.qr_date = qr_date;
    }
}
