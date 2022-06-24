package com.ielts.lakshacademy.Model;

public class CounselorData {

    String profileUrl,name,ratting;

    public CounselorData(String profileUrl, String name, String ratting) {
        this.profileUrl = profileUrl;
        this.name = name;
        this.ratting = ratting;
    }

    public CounselorData() {

    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatting() {
        return ratting;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }
}
