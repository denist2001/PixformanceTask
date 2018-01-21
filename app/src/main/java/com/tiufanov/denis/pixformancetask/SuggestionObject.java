package com.tiufanov.denis.pixformancetask;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuggestionObject {

    @SerializedName("vote_count")
    @Expose
    public final int vote_count;

    @SerializedName("id")
    @Expose
    public final int id;

    @SerializedName("video")
    @Expose
    public final boolean video;

    @SerializedName("vote_average")
    @Expose
    public final float vote_average;

    @SerializedName("title")
    @Expose
    public final String title;

    @SerializedName("popularity")
    @Expose
    public final float popularity;

    @SerializedName("poster_path")
    @Expose
    public final String poster_path;

    @SerializedName("original_language")
    @Expose
    public final String original_language;

    @SerializedName("original_title")
    @Expose
    public final String original_title;

    @SerializedName("genre_ids")
    @Expose
    public final int[] genre_ids;

    @SerializedName("backdrop_path")
    @Expose
    public final String backdrop_path;

    @SerializedName("adult")
    @Expose
    public final boolean adult;

    @SerializedName("overview")
    @Expose
    public final String overview;

    @SerializedName("release_date")
    @Expose
    public final String release_date;

    public SuggestionObject(final int vote_count,
                            final int id,
                            final boolean video,
                            final float vote_average,
                            @NonNull final String title,
                            @NonNull final float popularity,
                            @NonNull final String poster_path,
                            @NonNull final String original_language,
                            @NonNull final String original_title,
                            final int[] genre_ids,
                            @NonNull final String backdrop_path,
                            final boolean adult,
                            @NonNull final String overview,
                            @NonNull final String release_date) {
        this.vote_count = vote_count;
        this.id = id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
    }
}
