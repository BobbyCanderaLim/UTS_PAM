package com.example.bobby.mymovie;

import android.app.Application;
import android.util.Log;

import com.example.bobby.mymovie.API.MovieApi;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 11/28/17.
 */

public class GMovie extends Application {

    public static MovieApi movieApi;
    public static YouTubePlayer youTubePlayer;
    public YouTubePlayer.OnInitializedListener getYouTubePlayer;
    String TAG;
    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieApi = retrofit.create(MovieApi.class);

      getYouTubePlayer = new YouTubePlayer.OnInitializedListener() {
          @Override
          public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
              youTubePlayer.loadVideo("zgVHtDY24t8");
              Log.d(TAG , "inilisiasi berhasil");
          }

          @Override
          public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
              Log.d(TAG, "inilisiasi gagal");//          }
          }};

}
}

