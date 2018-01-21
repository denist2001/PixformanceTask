package com.tiufanov.denis.pixformancetask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiObject {

    @SerializedName("page")
    @Expose
    public final int page;

    @SerializedName("total_results")
    @Expose
    public final int total_results;

    @SerializedName("results")
    @Expose
    public final SuggestionObject[] results;

    public ApiObject(final int page,
                     final int total_results,
                     final SuggestionObject[] results) {
        this.page = page;
        this.total_results = total_results;
        this.results = results;
    }
}
