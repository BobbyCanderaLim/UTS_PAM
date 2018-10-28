package com.example.bobby.mymovie.API;

import com.google.android.youtube.player.YouTubeBaseActivity;

/**
 * Created by root on 11/30/17.
 */

public class YoutubePlayerApi extends YouTubeBaseActivity {

    private static final String API_KEY = "AIzaSyBYkHIVo9WCYInB1eVTiasWgFlcfkzjReI";

    public static String getApiKey() {
        return API_KEY;
    }
}
