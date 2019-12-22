package com.example.android.quakereport;
/*
Code Is Written By  Rishi Kumar  Date :20/12/2019
 */
public class EarthquakeDetails {
    String cityName;
    String primary_location;
    String date;
    String time;

    public String getPrimary_location() {
        return primary_location;
    }

    public void setPrimary_location(String primary_location) {
        this.primary_location = primary_location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String mag;

    public EarthquakeDetails(String cityName, String primary_location, String date, String time, String mag) {
        this.cityName = cityName;
        this.primary_location = primary_location;
        this.date = date;
        this.time = time;
        this.mag = mag;
    }



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }
}
