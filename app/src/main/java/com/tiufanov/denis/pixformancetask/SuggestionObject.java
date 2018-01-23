package com.tiufanov.denis.pixformancetask;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuggestionObject implements Parcelable {

    @SerializedName("vote_count")
    @Expose
    public final int voteCount;

    @SerializedName("id")
    @Expose
    public final int id;

    @SerializedName("video")
    @Expose
    public final boolean video;

    @SerializedName("vote_average")
    @Expose
    public final float voteAverage;

    @SerializedName("title")
    @Expose
    public final String title;

    @SerializedName("popularity")
    @Expose
    public final float popularity;

    @SerializedName("poster_path")
    @Expose
    public final String posterPath;

    @SerializedName("original_language")
    @Expose
    public final String originalLanguage;

    @SerializedName("original_title")
    @Expose
    public final String originalTitle;

    @SerializedName("genre_ids")
    @Expose
    public final int[] genreIds;

    @SerializedName("backdrop_path")
    @Expose
    public final String backdropPath;

    @SerializedName("adult")
    @Expose
    public final boolean adult;

    @SerializedName("overview")
    @Expose
    public final String overview;

    @SerializedName("release_date")
    @Expose
    public final String releaseDate;

    public SuggestionObject(final int voteCount,
                            final int id,
                            final boolean video,
                            final float voteAverage,
                            @NonNull final String title,
                            final float popularity,
                            @NonNull final String posterPath,
                            @NonNull final String originalLanguage,
                            @NonNull final String originalTitle,
                            final int[] genreIds,
                            @NonNull final String backdropPath,
                            final boolean adult,
                            @NonNull final String overview,
                            @NonNull final String releaseDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    protected SuggestionObject(Parcel in) {
        voteCount = in.readInt();
        id = in.readInt();
        video = in.readByte() != 0;
        voteAverage = in.readFloat();
        title = in.readString();
        popularity = in.readFloat();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        genreIds = in.createIntArray();
        backdropPath = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        releaseDate = in.readString();
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
        dest.writeInt(voteCount);
        dest.writeInt(id);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeFloat(voteAverage);
        dest.writeString(title);
        dest.writeFloat(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeIntArray(genreIds);
        dest.writeString(backdropPath);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }
}
