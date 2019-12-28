package com.example.newversion;

public class ads {
    String ads_num;
    String ads_date ;
    String ads_time ;
    String ads_text;

    public ads(String a , String b, String c, String d) {
        ads_num = a;
        ads_date = b ;
        ads_time = c ;
        ads_text = d;

    }

    public ads() {
    }

    public String getAds_text() {
        return ads_text;
    }

    public void setAds_text(String ads_text) {
        this.ads_text = ads_text;
    }

    public String getAds_time() {
        return ads_time;
    }

    public void setAds_time(String ads_time) {
        this.ads_time = ads_time;
    }

    public String getAds_date() {
        return ads_date;
    }

    public void setAds_date(String ads_date) {
        this.ads_date = ads_date;
    }

    public String getAds_num() {
        return ads_num;
    }

    public void setAds_num(String ads_num) {
        this.ads_num = ads_num;
    }
}
