package com.tiufanov.denis.pixformancetask;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuggestionObject implements Parcelable {

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
                            final float popularity,
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

    protected SuggestionObject(Parcel in) {
        vote_count = in.readInt();
        id = in.readInt();
        video = in.readByte() != 0;
        vote_average = in.readFloat();
        title = in.readString();
        popularity = in.readFloat();
        poster_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        genre_ids = in.createIntArray();
        backdrop_path = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<SuggestionObject> CREATOR = new Creator<SuggestionObject>() {
        @Override
        public SuggestionObject createFromParcel(Parcel in) {
            return new SuggestionObject(in);
        }

        @Override
        public SuggestionObject[] newArray(int size) {
            return new SuggestionObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(vote_count);
        dest.writeInt(id);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeFloat(vote_average);
        dest.writeString(title);
        dest.writeFloat(popularity);
        dest.writeString(poster_path);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeIntArray(genre_ids);
        dest.writeString(backdrop_path);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(release_date);
    }
}
