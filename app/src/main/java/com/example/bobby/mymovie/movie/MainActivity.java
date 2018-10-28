package com.example.bobby.mymovie.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bobby.mymovie.GMovie;
import com.example.bobby.mymovie.R;
import com.example.bobby.mymovie.model.GenreMovieModel;
import com.example.bobby.mymovie.model.PopularMovieModel;
import com.example.bobby.mymovie.model.PopularMovieResponse;
import com.example.bobby.mymovie.utility.EndlessRecyclerViewScrollListener;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.util.Log.d;

public class MainActivity extends YouTubeBaseActivity {

    private  static  final String TAG=MainActivity.class.getName();

    RecyclerView recyclerView ;
    RecyclerViewAdapter adapter;
    List<PopularMovieModel> movieModelList = new ArrayList<>();
    List<GenreMovieModel> genreMovieModelsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new RecyclerViewAdapter(movieModelList,genreMovieModelsList);
        recyclerView = findViewById(R.id.recycler_movie);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                loadMovie(""+(page+1));

            }
        };

        recyclerView.addOnScrollListener(scrollListener);

        loadMovie("1");
    }

    private void loadMovie(String page){


        GMovie.movieApi.getPopularMovies("974d3df97c2bebe0025fd2eb9e8965f7" ,page).enqueue(new Callback<PopularMovieResponse>() {
            @Override
            public void onResponse(Call<PopularMovieResponse> call, Response<PopularMovieResponse> response) {
                d("tag","populer movie response :"+ new Gson().toJsonTree(response.body()));
                movieModelList.addAll(response.body().getResults());
                adapter.notifyDataSetChanged();
                //Log.d(TAG,"isinya genre"+movieModelList.get(0).getGenreIds());

            }

            @Override
            public void onFailure(Call<PopularMovieResponse> call, Throwable t) {

            }
        });


    }
}

