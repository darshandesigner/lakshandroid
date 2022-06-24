package com.ielts.lakshacademy.Model;

public class AttendanceData {

    String date,listing,reading,writing,speaking;

    public AttendanceData(String date, String listing, String reading, String writing, String speaking) {
        this.date = date;
        this.listing = listing;
        this.reading = reading;
        this.writing = writing;
        this.speaking = speaking;
    }

    public  AttendanceData(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getListing() {
        return listing;
    }

    public void setListing(String listing) {
        this.listing = listing;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }
}
