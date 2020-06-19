package com.example.android.stutern_movieapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MovieActivity extends BaseActivity{
    ImageView mMovieImage;
    TextView mMovieTitle;
    TextView mMovieGenre;
    TextView mMovieOverview;
    TextView mMovieReleaseDate;
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mMovieImage = findViewById(R.id.movie_poster);
        mMovieTitle = findViewById(R.id.detail_movie_title);
        mMovieGenre = findViewById(R.id.detail_movie_genre);
        mMovieOverview = findViewById(R.id.detail_movie_overview);
        mMovieReleaseDate = findViewById(R.id.detail_movie_release_date);
//



    }
}
