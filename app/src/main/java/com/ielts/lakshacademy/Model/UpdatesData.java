package com.ielts.lakshacademy.Model;

public class UpdatesData {

    String date,time,desc;

    public UpdatesData(String date, String time, String desc) {
        this.date = date;
        this.time = time;
        this.desc = desc;
    }

    public UpdatesData(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
