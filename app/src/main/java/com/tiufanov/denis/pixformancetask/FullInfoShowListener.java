package com.tiufanov.denis.pixformancetask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.request.RequestListener;

public interface FullInfoShowListener {
    void showFullInfoAboutFilm(@NonNull final SuggestionObject filmInfo,
                               @Nullable final RequestListener requestListener);
}
