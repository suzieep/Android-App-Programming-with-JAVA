package com.example.finalexam;

public class Data_Model_call {
    private String title_listed;
    private String detail_listed;

    public Data_Model_call(String title_listed, String detail_listed){

        this.title_listed = title_listed;
        this.detail_listed = detail_listed;

    }

    public String getTitleName()
    {
        return this.title_listed;
    }
    public String getDetail()
    {
        return this.detail_listed;
    }
}