package com.tiufanov.denis.pixformancetask.fragment;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tiufanov.denis.pixformancetask.MoveToSearch;
import com.tiufanov.denis.pixformancetask.R;
import com.tiufanov.denis.pixformancetask.SuggestionObject;
import com.tiufanov.denis.pixformancetask.SuggestionsRecyclerAdapter;
import com.tiufanov.denis.pixformancetask.databinding.SuggestionDetailsBinding;

/**
 * A placeholder fragment containing a suggestion view.
 */
public class SuggestionFragment extends Fragment {

    private static final float MIN_DISTANCE = 250;
    private SuggestionObject suggestionObject;
    private SuggestionDetailsBinding suggestionDetailsBinding;
    private MoveToSearch moveToSearch;
    private String pictureUrl;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        pictureUrl = SuggestionsRecyclerAdapter.getPictureUrl(getResources().getDisplayMetrics().density);
        suggestionDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.suggestion_details,
                container, false);
        return suggestionDetailsBinding.getRoot();
    }

    public void setSuggestionObject (@NonNull final SuggestionObject object) {
        this.suggestionObject = object;
        drawAllFields(object);
    }

    private void drawAllFields(@NonNull final SuggestionObject object) {
        if (getContext() != null) {
            Glide.with(getContext())
                    .load(pictureUrl + object.poster_path)
                    .apply(RequestOptions.centerCropTransform())
                    .into(suggestionDetailsBinding.poster);
        }
        suggestionDetailsBinding.name.setText(object.title);
        suggestionDetailsBinding.voteAverage.setText(String.valueOf(object.vote_average));
        suggestionDetailsBinding.voteCount.setText(String.valueOf(object.vote_count));
        suggestionDetailsBinding.overview.setText(object.overview);
    }

    public void setMoveToSearch(@NonNull final MoveToSearch moveToSearch) {
        this.moveToSearch = moveToSearch;
    }
}
