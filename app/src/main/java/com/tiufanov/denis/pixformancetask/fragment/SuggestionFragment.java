package com.tiufanov.denis.pixformancetask.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.tiufanov.denis.pixformancetask.R;
import com.tiufanov.denis.pixformancetask.SuggestionObject;
import com.tiufanov.denis.pixformancetask.SuggestionsRecyclerAdapter;
import com.tiufanov.denis.pixformancetask.databinding.SuggestionDetailsBinding;

/**
 * A placeholder fragment containing a suggestion view.
 */
public class SuggestionFragment extends Fragment {

    private SuggestionDetailsBinding suggestionDetailsBinding;
    private String pictureUrl;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        pictureUrl = SuggestionsRecyclerAdapter.getPictureUrl(getResources().getDisplayMetrics().density);
        suggestionDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.suggestion_details,
                container, false);
        return suggestionDetailsBinding.getRoot();
    }

    public void setSuggestionObject (@NonNull final SuggestionObject object,
                                     @Nullable final RequestListener requestListener) {
        drawAllFields(object, requestListener);
    }

    private void drawAllFields(@NonNull final SuggestionObject object,
                               @Nullable final RequestListener requestListener) {
        if (getContext() != null) {
            Glide.with(getContext())
                    .load(pictureUrl + object.posterPath)
                    .apply(RequestOptions.centerCropTransform())
                    .listener(requestListener)
                    .into(suggestionDetailsBinding.poster);
        }
        suggestionDetailsBinding.voteAverageDescription.setVisibility(View.VISIBLE);
        suggestionDetailsBinding.voteCountDescription.setVisibility(View.VISIBLE);
        suggestionDetailsBinding.name.setText(object.title);
        suggestionDetailsBinding.voteAverage.setText(String.valueOf(object.voteAverage));
        suggestionDetailsBinding.voteCount.setText(String.valueOf(object.voteCount));
        suggestionDetailsBinding.overview.setText(object.overview);
    }
}
