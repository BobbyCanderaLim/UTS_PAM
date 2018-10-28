package com.example.bobby.mymovie.API;

import com.example.bobby.mymovie.model.DetailMovieResponse;
import com.example.bobby.mymovie.model.GenreMovieResponse;
import com.example.bobby.mymovie.model.PopularMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    String apiKey = null;
    @GET("3/movie/popular?language=en-US&page=1")

    Call<PopularMovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("page") String page);


    @GET("3/genre/movie/list?language=en-US")
    Call<GenreMovieResponse> getGenre(
            @Query("api_key") String apiKey

    );
    @GET("3/movie/{movie_id}/videos?language=en-US")
    Call<DetailMovieResponse> getDetailMovie(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );
}
