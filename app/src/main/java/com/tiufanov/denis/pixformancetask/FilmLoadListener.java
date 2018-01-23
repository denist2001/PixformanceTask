package com.tiufanov.denis.pixformancetask;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public interface FilmLoadListener {
    void onSearchResultsLoaded(@NonNull final String filmName, @NonNull final ArrayList<SuggestionObject> suggestions);
    void onSearchResultsError(@NonNull final String filmName, @NonNull final String error);
}
