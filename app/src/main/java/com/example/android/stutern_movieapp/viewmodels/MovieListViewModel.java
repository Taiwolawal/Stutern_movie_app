package com.example.android.stutern_movieapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.stutern_movieapp.models.MovieClass;
import com.example.android.stutern_movieapp.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository mMovieRepository;

    public MovieListViewModel() {
        mMovieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieClass>> getMovies(){
        return mMovieRepository.getMovies();
    }

    public void searchMoviesApi (String query,int page){
        mMovieRepository.searchMoviesApi(query,page);
    }

    public void upComingMoviesApi (int page){
        mMovieRepository.upComingMoviesApi(page);
    }

    public void topRatedMoviesApi (int page){
        mMovieRepository.topRatedMoviesApi(page);
    }

    public void popularMoviesApi (int page){
        mMovieRepository.popularMoviesApi(page);
    }

}
