package com.example.android.stutern_movieapp.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class SelectedMovieResponse {


    @SerializedName("original_title")
    @Expose
    private String original_title;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("genre_ids")
    @Expose
    private  String[] genre_ids;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("production_companies")
    @Expose
    private String[] production_companies;

    @Override
    public String toString() {
        return "SelectedMovieResponse{" +
                "original_title='" + original_title + '\'' +
                ", release_date='" + release_date + '\'' +
                ", overview='" + overview + '\'' +
                ", genre_ids=" + Arrays.toString(genre_ids) +
                ", poster_path='" + poster_path + '\'' +
                ", production_companies=" + Arrays.toString(production_companies) +
                '}';
    }
}
