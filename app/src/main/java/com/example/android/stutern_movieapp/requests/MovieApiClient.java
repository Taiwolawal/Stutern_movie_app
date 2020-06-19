package com.example.android.stutern_movieapp.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.stutern_movieapp.AppExecutors;
import com.example.android.stutern_movieapp.models.MovieClass;
import com.example.android.stutern_movieapp.requests.responses.PopularMoviesResponse;
import com.example.android.stutern_movieapp.requests.responses.SearchMoviesResponse;
import com.example.android.stutern_movieapp.requests.responses.TopRatedMoviesResponse;
import com.example.android.stutern_movieapp.requests.responses.UpcomingMoviesResponse;
import com.example.android.stutern_movieapp.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.android.stutern_movieapp.util.Constants.NETWORK_TIMEOUT;

public class MovieApiClient {

    private static final String TAG = "MovieApiClient";
    private static MovieApiClient instance;
    private MutableLiveData<List<MovieClass>> mMovies;
    private RetrieveSearchMoviesRunnable mRetrieveSearchMoviesRunnable;
    private RetrieveUpcomingMoviesRunnable mRetrieveUpcomingMoviesRunnable;
    private RetrievePopularMoviesRunnable mRetrievePopularMoviesRunnable;
    private RetrieveTopRatedMoviesRunnable mRetrieveTopRatedMoviesRunnable;


    public  static MovieApiClient getInstance(){
        if(instance == null){
            instance = new MovieApiClient();
        }
        return  instance;
    }

    private MovieApiClient (){
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieClass>> getMovies(){
        return  mMovies;
    }

    public void searchMoviesApi(String query, int page){
        if(mRetrieveSearchMoviesRunnable != null){
            mRetrieveSearchMoviesRunnable = null;
        }
        mRetrieveSearchMoviesRunnable = new RetrieveSearchMoviesRunnable(query, page);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveSearchMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS );
    }


    private class RetrieveSearchMoviesRunnable implements Runnable{

        private String query;
        private int page;
        boolean cancelRequest;

        public RetrieveSearchMoviesRunnable(String query, int page) {
            this.query = query;
            this.page = page;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getMovies(query, page).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieClass> list = new ArrayList<>(((SearchMoviesResponse)response.body()).getMovies());
                    if(page == 1){
                        mMovies.postValue(list);
                    }
                    else{
                        List<MovieClass> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }
                else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error );
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<SearchMoviesResponse> getMovies (String query, int page){
            return ServiceGenerator.getMovieApi().searchMovies(Constants.API_KEY, query, String.valueOf(page));
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: cancelling the search request." );
            cancelRequest = true;
        }
    }

    public void upComingMoviesApi(int page){
        if(mRetrieveUpcomingMoviesRunnable != null){
            mRetrieveUpcomingMoviesRunnable = null;
        }
        mRetrieveUpcomingMoviesRunnable = new RetrieveUpcomingMoviesRunnable(page);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveUpcomingMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS );
    }

    private class RetrieveUpcomingMoviesRunnable implements Runnable{

        private int page;
        boolean cancelRequest;

        public RetrieveUpcomingMoviesRunnable(int page) {
            this.page = page;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getUpcomingMovies(page).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieClass> list = new ArrayList<>(((UpcomingMoviesResponse)response.body()).getResults());
                    if(page == 1){
                        mMovies.postValue(list);
                    }
                    else{
                        List<MovieClass> currentUpcomingMovies = mMovies.getValue();
                        currentUpcomingMovies.addAll(list);
                        mMovies.postValue(currentUpcomingMovies);
                    }
                }
                else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error );
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<UpcomingMoviesResponse> getUpcomingMovies (int page){
            return ServiceGenerator.getMovieApi().getUpcomingMovies(Constants.API_KEY, String.valueOf(page));
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: cancelling the search request." );
            cancelRequest = true;
        }
    }

    public void topRatedMoviesApi(int page){
        if(mRetrieveTopRatedMoviesRunnable != null){
            mRetrieveTopRatedMoviesRunnable = null;
        }
        mRetrieveTopRatedMoviesRunnable = new RetrieveTopRatedMoviesRunnable(page);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveTopRatedMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS );
    }

    private class RetrieveTopRatedMoviesRunnable implements Runnable{

        private int page;
        boolean cancelRequest;

        public RetrieveTopRatedMoviesRunnable(int page) {
            this.page = page;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = searchTopRated(page).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieClass> list = new ArrayList<>(((TopRatedMoviesResponse)response.body()).getResults());
                    if(page == 1){
                        mMovies.postValue(list);
                    }
                    else{
                        List<MovieClass> currentTopRatedMovies = mMovies.getValue();
                        currentTopRatedMovies.addAll(list);
                        mMovies.postValue(currentTopRatedMovies);
                    }
                }
                else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error );
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<TopRatedMoviesResponse> searchTopRated (int page){
            return ServiceGenerator.getMovieApi().getTopRatedMovies(Constants.API_KEY, String.valueOf(page));
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: cancelling the search request." );
            cancelRequest = true;
        }
    }

    public void popularMoviesApi(int page){
        if(mRetrievePopularMoviesRunnable != null){
            mRetrievePopularMoviesRunnable = null;
        }
        mRetrievePopularMoviesRunnable = new RetrievePopularMoviesRunnable(page);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrievePopularMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS );
    }

    private class RetrievePopularMoviesRunnable implements Runnable{

        private int page;
        boolean cancelRequest;

        public RetrievePopularMoviesRunnable(int page) {
            this.page = page;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = searchPopularMovies(page).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieClass> list = new ArrayList<>(((PopularMoviesResponse)response.body()).getResults());
                    if(page == 1){
                        mMovies.postValue(list);
                    }
                    else{
                        List<MovieClass> currentPopularMovies = mMovies.getValue();
                        currentPopularMovies.addAll(list);
                        mMovies.postValue(currentPopularMovies);
                    }
                }
                else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error );
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<PopularMoviesResponse> searchPopularMovies (int page){
            return ServiceGenerator.getMovieApi().getPopularMovies(Constants.API_KEY, String.valueOf(page));
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: cancelling the search request." );
            cancelRequest = true;
        }
    }


}

