package com.tiufanov.denis.pixformancetask.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiufanov.denis.pixformancetask.OnFullInfoShow;
import com.tiufanov.denis.pixformancetask.R;

/**
 * A placeholder fragment containing a suggestion view.
 */
public class SuggestionFragment extends Fragment {

    private OnFullInfoShow onFullInfoShow;

    /**
     * The fragment argument representing the details about selected film.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SuggestionFragment newInstance(int sectionNumber) {
        SuggestionFragment fragment = new SuggestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void setOnFullInfoShow (@NonNull final OnFullInfoShow onFullInfoShow) {
        this.onFullInfoShow = onFullInfoShow;
    }
}
