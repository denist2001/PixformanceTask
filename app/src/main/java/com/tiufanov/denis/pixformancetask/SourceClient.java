package com.tiufanov.denis.pixformancetask;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SourceClient {
    @GET("3/search/movie")
    Call<ApiObject> getSuggestionObject(@Query("api_key") String apiKey, @Query("query") String query);
}
