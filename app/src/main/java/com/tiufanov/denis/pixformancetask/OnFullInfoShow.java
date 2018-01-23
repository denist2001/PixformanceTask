package com.tiufanov.denis.pixformancetask;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public interface OnFullInfoShow {
    void showFullInfoAboutFilm(@NonNull final SuggestionObject filmInfo);
}
