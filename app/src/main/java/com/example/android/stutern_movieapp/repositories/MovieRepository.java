package com.example.android.stutern_movieapp.repositories;

import androidx.lifecycle.LiveData;

import com.example.android.stutern_movieapp.models.MovieClass;
import com.example.android.stutern_movieapp.requests.MovieApiClient;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;
    private MovieApiClient mMovieApiClient;

    public  static MovieRepository getInstance(){
        if(instance==null){
            instance = new MovieRepository();
        }
        return  instance;
    }

    private MovieRepository(){
        mMovieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieClass>> getMovies() {
        return mMovieApiClient.getMovies();
    }

    public void searchMoviesApi (String query,int page){
        if(page == 0){
            page = 1;
        }
        mMovieApiClient.searchMoviesApi(query,page);
    }

    public void upComingMoviesApi (int page){
        if(page == 0){
            page = 1;
        }
        mMovieApiClient.upComingMoviesApi(page);
    }

    public void topRatedMoviesApi (int page){
        if(page == 0){
            page = 1;
        }
        mMovieApiClient.topRatedMoviesApi(page);
    }

    public void popularMoviesApi (int page){
        if(page == 0){
            page = 1;
        }
        mMovieApiClient.popularMoviesApi(page);
    }
}
