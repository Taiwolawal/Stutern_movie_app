package com.example.android.stutern_movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.stutern_movieapp.R;
import com.example.android.stutern_movieapp.models.MovieClass;

import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{

    private Context context;
    private List<MovieClass> mMovieClass;

    public MovieRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup, false);

        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((MovieViewHolder)viewHolder).categoryTitle.setText(mMovieClass.get(position).getOriginal_title());
        String poster = "https://image.tmdb.org/t/p/w500" + mMovieClass.get(position).getPoster_path();
        Glide.with(context).load(poster).into(((MovieViewHolder)viewHolder).categoryImage);

    }

    @Override
    public int getItemCount() {
        if(mMovieClass != null){
            return mMovieClass.size();
        }
        return 0;
    }

    public void setMovies(List<MovieClass> movies){
        mMovieClass = movies;
        notifyDataSetChanged();

    }

}


