package com.example.victorhinaux.timetofit;

/**
 * Created by mathp on 07/01/2019.
 */

public class YoutubeVideos {

    String videoUrl;

    public YoutubeVideos(){

    }

        public YoutubeVideos(String videoUrl){ this.videoUrl = videoUrl;}

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
