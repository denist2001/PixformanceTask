package com.tiufanov.denis.pixformancetask;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tiufanov.denis.pixformancetask.databinding.SuggestionInListBinding;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsRecyclerAdapter extends RecyclerView.Adapter<SuggestionsRecyclerAdapter.SuggestionViewHolder> {
    @NonNull
    private final FullInfoShowListener fullInfoShowListener;
    @NonNull
    private final List<SuggestionObject> suggestionsList;
    @NonNull
    private final Context context;
    private final String pictureUrl;

    public SuggestionsRecyclerAdapter(@NonNull final Context context,
                                      @NonNull final ArrayList<SuggestionObject> suggestions,
                                      @NonNull final FullInfoShowListener fullInfoShowListener) {
        this.context = context;
        suggestionsList = suggestions;
        pictureUrl = getPictureUrl(context.getResources().getDisplayMetrics().density);
        this.fullInfoShowListener = fullInfoShowListener;
    }

    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final SuggestionInListBinding suggestionInListBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.suggestion_in_list, parent, false);
        return new SuggestionViewHolder(suggestionInListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final SuggestionViewHolder holder, final int position) {
        final SuggestionObject object = suggestionsList.get(position);
        holder.element.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullInfoShowListener.showFullInfoAboutFilm(object, null);
            }
        });
        Glide.with(context)
                .load(pictureUrl + object.posterPath)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.element.poster);
        holder.element.name.setText(object.title);
        holder.element.voteAverage.setText(String.valueOf(object.voteAverage));
    }

    @Override
    public int getItemCount() {
        return suggestionsList.size();
    }

    public static String getPictureUrl(final float density) {
        final String pictureUrl = "https://image.tmdb.org/t/p/";
        if (density <= 1.5) {
            return pictureUrl + "w92";
        }
        if (density <= 2.0) {
            return pictureUrl + "w185";
        }
        if (density <= 3.0) {
            return pictureUrl + "w500";
        }
        if (density <= 4.0) {
            return pictureUrl + "w780";
        }
        return pictureUrl + "w780";
    }

    class SuggestionViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final SuggestionInListBinding element;

        SuggestionViewHolder(@NonNull final SuggestionInListBinding element) {
            super(element.getRoot());
            this.element = element;
        }

        public void bind(Object obj) {
            element.setVariable(BR.obj, obj);
            element.executePendingBindings();
        }
    }
}
