package com.tiufanov.denis.pixformancetask;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmsRepository{
    @NonNull
    private static final ArrayList<SuggestionObject> suggestions = new ArrayList<>();
    private static final String API_REQUEST = "https://api.themoviedb.org/";
    private static final String API_KEY= "2696829a81b1b5827d515ff121700838";

    public void requestFilm(@NonNull final String filmName, @NonNull final OnFilmLoaded onFilmLoaded) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_REQUEST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        SourceClient client = retrofit.create(SourceClient.class);
        retrofit2.Call<ApiObject> call = client.getSuggestionObject(API_KEY, filmName);
        call.enqueue(new retrofit2.Callback<ApiObject>() {
            @Override
            public void onResponse(retrofit2.Call<ApiObject> call, retrofit2.Response<ApiObject> response) {
                suggestions.clear();
                suggestions.addAll(Arrays.asList(response.body().results));
                onFilmLoaded.onSearchResultsLoaded(filmName, suggestions);
            }

            @Override
            public void onFailure(retrofit2.Call<ApiObject> call, Throwable t) {
                onFilmLoaded.onSearchResultsError(filmName, t.getMessage());
            }
        });
    }
}
