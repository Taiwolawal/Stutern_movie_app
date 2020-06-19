package com.example.android.stutern_movieapp.requests;

import com.example.android.stutern_movieapp.requests.responses.PopularMoviesResponse;
import com.example.android.stutern_movieapp.requests.responses.SearchMoviesResponse;
import com.example.android.stutern_movieapp.requests.responses.SelectedMovieResponse;
import com.example.android.stutern_movieapp.requests.responses.TopRatedMoviesResponse;
import com.example.android.stutern_movieapp.requests.responses.UpcomingMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    // SEARCH
    @GET("search/movie")
    Call<SearchMoviesResponse> searchMovies(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );

    // GET UPCOMING MOVIES REQUEST
    @GET("movie/upcoming")
    Call<UpcomingMoviesResponse> getUpcomingMovies(
            @Query("api_key") String key,
            @Query("page") String page
    );

    // GET POPULAR MOVIES REQUEST
    @GET("movie/popular")
    Call<PopularMoviesResponse> getPopularMovies(
            @Query("api_key") String key,
            @Query("page") String page
    );

    // GET TOP RATED MOVIES REQUEST
    @GET("movie/top_rated")
    Call<TopRatedMoviesResponse> getTopRatedMovies(
            @Query("api_key") String key,
            @Query("page") String page
    );

    //GET MOVIE SELECTED
    @GET("movie/{movie_id}")
    Call<SelectedMovieResponse> getSelectedMovie(
            @Query("movie_id") String id,
            @Query("api_key") String key
    );

}
