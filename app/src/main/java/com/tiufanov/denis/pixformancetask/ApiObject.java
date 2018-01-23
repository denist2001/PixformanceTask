package com.tiufanov.denis.pixformancetask;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiObject {

    @SerializedName("page")
    @Expose
    public final int page;

    @SerializedName("total_results")
    @Expose
    public final int totalResults;

    @SerializedName("results")
    @Expose
    public final SuggestionObject[] results;

    public ApiObject(final int page,
                     final int totalResults,
                     @NonNull final SuggestionObject[] results) {
        this.page = page;
        this.totalResults = totalResults;
        this.results = results;
    }
}
