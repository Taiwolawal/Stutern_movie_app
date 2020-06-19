package com.example.android.stutern_movieapp.requests.responses;

import com.example.android.stutern_movieapp.models.MovieClass;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchMoviesResponse {

    @SerializedName("page")
    @Expose()
    private int page;

    @SerializedName("total_results")
    @Expose()
    private int total_results;

    @SerializedName("total_pages")
    @Expose()
    private  int total_pages;

    @SerializedName("results")
    @Expose()
    private List<MovieClass> movies;

    @Override
    public String toString() {
        return "SearchMoviesResponse{" +
                "page=" + page +
                ", total_results=" + total_results +
                ", total_pages=" + total_pages +
                ", movies=" + movies +
                '}';
    }

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<MovieClass> getMovies() {
        return movies;
    }
}
