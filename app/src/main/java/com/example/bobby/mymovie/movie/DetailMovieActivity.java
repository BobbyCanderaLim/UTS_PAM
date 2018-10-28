package com.example.bobby.mymovie.movie;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.bobby.mymovie.API.YoutubePlayerApi;
import com.example.bobby.mymovie.GMovie;
import com.example.bobby.mymovie.R;
import com.example.bobby.mymovie.model.DetailMovieModel;
import com.example.bobby.mymovie.model.DetailMovieResponse;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.util.Log.d;

public class DetailMovieActivity extends AppCompatActivity {

    private static final String TAG =DetailMovieModel.class.getSimpleName();
    Toolbar toolbar;
    List<DetailMovieModel> detailMovieModelList = new ArrayList<>();

    FloatingActionButton fabPlay;
    TextView txtTitle ,txtDate,txtOverview,txtRating;
    ImageView imageViewPoster ,imageViewBackdrop;
    YouTubePlayerView youTubePlayerView;
    YoutubePlayerApi youtubePlayerApi = new YoutubePlayerApi();
    YouTubePlayer.OnInitializedListener onInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        String title,date,overview, urlImage,urlBackdrop,rating;
        final Integer idMovie;

        txtTitle = findViewById(R.id.txt_view_detail_title);
        txtOverview =findViewById(R.id.txt_view_detail_synopsis);
        txtDate = findViewById(R.id.txt_view_detail_release_date);
        txtRating = findViewById(R.id.txt_view_detail_rating);
        fabPlay = findViewById(R.id.fab_play);





        imageViewPoster = findViewById(R.id.image_view_detail_movie);
        imageViewBackdrop = findViewById(R.id.image_view_detail_movie2);

        Intent intent =getIntent();
        Intent appIntent = new Intent(Intent.ACTION_VIEW);
        title= intent.getStringExtra("title");
        date= intent.getStringExtra("date");
        overview = intent.getStringExtra("overview");
        rating = intent.getStringExtra("rating");
        idMovie = intent.getIntExtra("id",0);


        urlImage = intent.getStringExtra("urlimage");
        urlBackdrop= intent.getStringExtra("urlBackdrop");

        Glide.with(this).
                load("https://image.tmdb.org/t/p/w185"+urlImage).
                into(imageViewPoster);
        Glide.with(this).
                load("https://image.tmdb.org/t/p/w500"+urlBackdrop).
                into(imageViewBackdrop);

        txtTitle.setText(title);
        txtDate.setText(date);
        txtOverview.setText(overview);
        txtRating.setText("Average Rating:"+rating);



        fabPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTrailer(String.valueOf(idMovie));

            }
        });

        // txtOverview.setText(""+popularMovieModel.getOriginalTitle());



    }

    private void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    private void loadTrailer(String idMovie){

        GMovie.movieApi.getDetailMovie(idMovie,"3b980052023e8e6898f4578a6972740b").enqueue(new Callback<DetailMovieResponse>() {
            @Override
            public void onResponse(Call<DetailMovieResponse> call, Response<DetailMovieResponse> response) {
                d("tag","detail movie response:"+new Gson().toJsonTree(response.body()));
                detailMovieModelList.addAll(response.body().getResults());
                watchYoutubeVideo(DetailMovieActivity.this,detailMovieModelList.get(0).getKey());
            }


            @Override
            public void onFailure(Call<DetailMovieResponse> call, Throwable t) {

            }
        });
    }

}
