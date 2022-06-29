package com.ielts.lakshacademy.Model;

public class VideoData {
    String videoName,videoUrl,videoUpdatedDate;

    public VideoData(String videoName, String videoUrl, String videoUpdatedDate) {
        this.videoName = videoName;
        this.videoUrl = videoUrl;
        this.videoUpdatedDate = videoUpdatedDate;
    }

    public VideoData() {
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUpdatedDate() {
        return videoUpdatedDate;
    }

    public void setVideoUpdatedDate(String videoUpdatedDate) {
        this.videoUpdatedDate = videoUpdatedDate;
    }
}
