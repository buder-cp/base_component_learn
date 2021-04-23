package com.example.paging.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.example.paging.api.RetrofitClient;
import com.example.paging.model.Movie;
import com.example.paging.model.Movies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PositionalDataSource<Movie> {

    public static final int PER_PAGE = 8;

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Movie> callback) {
        int startPosition = 0;
        RetrofitClient.getInstance()
                .getApi()
                .getMovies(startPosition, PER_PAGE)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        if (response.body() != null) {
                            Log.e("loadInitial()", "startPosition:" + params.requestedStartPosition + " response:" + response.body());
                            callback.onResult(response.body().movieList, response.body().start, response.body().total);
                        }
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Movie> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getMovies(params.startPosition, PER_PAGE)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        if (response.body() != null) {
                            Log.e("loadRange()", "startPosition:" + params.startPosition + " response:" + response.body());
                            callback.onResult(response.body().movieList);
                        }
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {

                    }
                });
    }
}
