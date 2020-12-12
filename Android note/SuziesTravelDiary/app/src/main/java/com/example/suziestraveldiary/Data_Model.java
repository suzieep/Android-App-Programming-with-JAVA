package com.example.suziestraveldiary;

public class Data_Model {
    private String image_listed;
    private String title_listed;
    private String detail_listed;
    private String latitude_listed;
    private String longitude_listed;

    public Data_Model(String image_listed, String title_listed, String detail_listed, String latitude_listed, String longitude_listed){
        this.image_listed = image_listed;
        this.title_listed = title_listed;
        this.detail_listed = detail_listed;
        this.latitude_listed = latitude_listed;
        this.longitude_listed = longitude_listed;
    }

    public String getImage()
    {

        return this.image_listed;
    }

    public String getTitleName()
    {
        return this.title_listed;
    }

    public String getDetail()
    {
        return this.detail_listed;
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