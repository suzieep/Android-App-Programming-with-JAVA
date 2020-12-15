package com.example.finalexam;

public class Data_Model_map {
    private String title_listed;
    private String latitude_listed;
    private String longitude_listed;

    public Data_Model_map(String title_listed, String latitude_listed, String longitude_listed){
        this.title_listed = title_listed;
        this.latitude_listed = latitude_listed;
        this.longitude_listed = longitude_listed;
    }

    public String getTitleName()
    {
        return this.title_listed;
    }
    public String getLatitude()
    {
        return this.latitude_listed;
    }
    public String getLongitude()
    {
        return this.longitude_listed;
    }
}