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
    public static String API_REQUEST = "https://api.themoviedb.org/";
    private static final String API_KEY= "2696829a81b1b5827d515ff121700838";

    public void requestFilm(@NonNull final String filmName, @NonNull final FilmLoadListener filmLoadListener,
                            @NonNull final  String apiRequest) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiRequest)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        final SourceClient client = retrofit.create(SourceClient.class);
        retrofit2.Call<ApiObject> call = client.getSuggestionObject(API_KEY, filmName);
        call.enqueue(new retrofit2.Callback<ApiObject>() {
            @Override
            public void onResponse(retrofit2.Call<ApiObject> call, retrofit2.Response<ApiObject> response) {
                if (response.body().errorObject == null ) {
                    if (response.body().results !=null) {
                        suggestions.clear();
                        suggestions.addAll(Arrays.asList(response.body().results));
                        filmLoadListener.onSearchResultsLoaded(filmName, suggestions);
                        return;
                    }
                    if (!response.body().success) {
                        filmLoadListener.onSearchResultsError(filmName, response.body().status_message);
                        return;
                    }
                }
                filmLoadListener.onSearchResultsError(filmName, response.body().errorObject.message);
            }

            @Override
            public void onFailure(retrofit2.Call<ApiObject> call, Throwable t) {
                filmLoadListener.onSearchResultsError(filmName, t.getMessage());
            }
        });
    }
}
