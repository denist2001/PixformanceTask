package com.tiufanov.denis.pixformancetask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    @SerializedName("error")
    @Expose
    public final ErrorObject errorObject;

    @SerializedName("success")
    @Expose
    public final boolean success;

    @SerializedName("status_message")
    @Expose
    public final String status_message;

    public ApiObject(final int page,
                     final int totalResults,
                     @NonNull final SuggestionObject[] results,
                     @Nullable final ErrorObject errorObject,
                     final boolean success,
                     @Nullable final String status_message) {
        this.page = page;
        this.totalResults = totalResults;
        this.results = results;
        this.errorObject = errorObject;
        this.success = success;
        this.status_message = status_message;
    }
}
