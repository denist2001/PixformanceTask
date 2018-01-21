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
    private final List<SuggestionObject> suggestionsList;
    @NonNull
    private final Context context;
    private final String pictureUrl;

    public SuggestionsRecyclerAdapter(@NonNull final Context context,
                                      ArrayList<SuggestionObject> suggestions) {
        this.context = context;
        suggestionsList = suggestions;
        pictureUrl = getPictureUrl(context.getResources().getDisplayMetrics().density);
    }

    @Override
    public SuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final SuggestionInListBinding suggestionInListBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.suggestion_in_list, parent, false);
        return new SuggestionViewHolder(suggestionInListBinding);
    }

    @Override
    public void onBindViewHolder(SuggestionViewHolder holder, int position) {
        holder.element.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: transition to new fragment
            }
        });
        SuggestionObject object = suggestionsList.get(position);
        Glide.with(context)
                .load(pictureUrl + object.poster_path)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.element.poster);
        holder.element.name.setText(object.title);
        holder.element.voteAverage.setText(String.valueOf(object.vote_average));
    }

    @Override
    public int getItemCount() {
        return suggestionsList.size();
    }

    private String getPictureUrl(final float density) {
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

        public SuggestionViewHolder(@NonNull final SuggestionInListBinding element) {
            super(element.getRoot());
            this.element = element;
        }

        public void bind(Object obj) {
            element.setVariable(BR.obj, obj);
            element.executePendingBindings();
        }
    }
}
