package com.example.android.stutern_movieapp.requests.responses;

import com.example.android.stutern_movieapp.models.MovieClass;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingMoviesResponse {

    @SerializedName("results")
    @Expose()
    private List<MovieClass> results;

    @Override
    public String toString() {
        return "UpcomingMoviesResponse{" +
                "results=" + results +
                '}';
    }

    public List<MovieClass> getResults() {
        return results;
    }
}
