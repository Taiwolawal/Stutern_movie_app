package com.example.android.stutern_movieapp;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android.stutern_movieapp.adapters.MovieRecyclerAdapter;
import com.example.android.stutern_movieapp.models.MovieClass;
import com.example.android.stutern_movieapp.viewmodels.MovieListViewModel;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

public class MovieListActivity extends BaseActivity {
    private static final String TAG = "MovieListActivity";

    private MovieListViewModel mMovieListViewModel;
    private SearchView mMovieSearch;
    MovieRecyclerAdapter mMovieAdapter;
    MovieRecyclerAdapter mAdapterLoadPopularMovies;
    MovieRecyclerAdapter mAdapterUpcomingMovies;
    MovieRecyclerAdapter mAdapterTopRatedMovies;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        mMovieSearch =findViewById(R.id.movie_search);

        mMovieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        mMovieListViewModel.getMovies().observe(this, new Observer<List<MovieClass>>() {
            @Override
            public void onChanged(List<MovieClass> movieClasses) {
                if(movieClasses != null){
                    for(MovieClass movieClass: movieClasses){
                        Log.d(TAG, "onChanged: " + movieClass.getOriginal_title());

                    }
                    mMovieAdapter.setMovies(movieClasses);
                }
            }
        });



        // subscribeObserver();
        loadPopularMovies();
        loadUpcomingMovies();
        loadTopRatedMovies();
    }


//    private void subscribeObserver(){
//        mMovieListViewModel.getMovies().observe(this, movieClasses -> {
//            if(movieClasses != null){
//                mMovieAdapter.setMovies(movieClasses);
//            }
//        });
//    }

    private void searchMoviesApi (String query, int page){
        mMovieListViewModel.searchMoviesApi(query, page);
    }

    private void upComingMoviesApi (int page){
        mMovieListViewModel.upComingMoviesApi(page);
    }

    private void topRatedMoviesApi (int page){
        mMovieListViewModel.topRatedMoviesApi(page);
    }

    private void popularMoviesApi (int page){
        mMovieListViewModel.popularMoviesApi(page);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadUpcomingMovies(){
        Log.d(TAG, "loadUpcomingMovies: " + getTitle() );
        upComingMoviesApi(1);
        mAdapterUpcomingMovies = new MovieRecyclerAdapter(getApplicationContext());
        MultiSnapRecyclerView firstRecyclerView = (MultiSnapRecyclerView) findViewById(R.id.first_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(mAdapterUpcomingMovies);
        Log.d(TAG, "loadUpcomingMovies: in loading movies " );
    }

    private void loadPopularMovies(){
        popularMoviesApi(1);
        mAdapterLoadPopularMovies = new MovieRecyclerAdapter(getApplicationContext());
        MultiSnapRecyclerView secondRecyclerView = (MultiSnapRecyclerView) findViewById(R.id.second_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(firstManager);
        secondRecyclerView.setAdapter(mAdapterLoadPopularMovies);
        Log.d(TAG, "loadPopularMovies: in popular");
    }

    private void loadTopRatedMovies(){
        topRatedMoviesApi(1);
        mAdapterTopRatedMovies = new MovieRecyclerAdapter(getApplicationContext());
        MultiSnapRecyclerView thirdRecyclerView = (MultiSnapRecyclerView) findViewById(R.id.third_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        thirdRecyclerView.setLayoutManager(firstManager);
        thirdRecyclerView.setAdapter(mAdapterTopRatedMovies);
        Log.d(TAG, "loadTopRatedMovies: in toprated");
    }







}
