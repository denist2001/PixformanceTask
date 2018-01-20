package com.tiufanov.denis.pixformancetask.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Set;

public class SuggestionsListAdapter extends BaseAdapter {
    private static final int TEXT_SIZE = 25;
    private static final int MIN_HEIGHT_PX = 40;
    private static String SHAREDPREFS_KEY = "SuggestionsList";
    private static String SUGGESTIONS_KEY = "suggestions";
    @NonNull
    private final Context context;
    @NonNull
    private final LimitedList suggestionsList= new LimitedList();

    SuggestionsListAdapter(@NonNull final Context context) {
        this.context = context;
//        suggestionsList.addFirst("January");
//        suggestionsList.addFirst("February");
//        suggestionsList.addFirst("March");
//        suggestionsList.addFirst("April");
//        suggestionsList.addFirst("May");
//        suggestionsList.addFirst("June");
//        suggestionsList.addFirst("July");
//        suggestionsList.addFirst("August");
//        suggestionsList.addFirst("September");
//        suggestionsList.addFirst("October");
//        suggestionsList.addFirst("November");
//        suggestionsList.addFirst("December");
    }

    @Override
    public int getCount() {
        return suggestionsList.size();
    }

    @Override
    public String getItem(int position) {
        return suggestionsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        TextView suggestionTextView = new TextView(parent.getContext());
        suggestionTextView.setTextSize(TEXT_SIZE);
        suggestionTextView.setMinHeight(MIN_HEIGHT_PX);
        suggestionTextView.setText(suggestionsList.get(position));
        suggestionTextView.setPadding(10, 0, 0,0);
        return suggestionTextView;
    }

    void moveSuggestionToTopPosition(@NonNull String filmName) {
        suggestionsList.remove(filmName);
        suggestionsList.addFirst(filmName);
    }

    void onResume() {
        SharedPreferences preferences = context.getSharedPreferences(SHAREDPREFS_KEY, Context.MODE_PRIVATE);
        Set<String> suggestionsSet = new android.support.v4.util.ArraySet<>();
        suggestionsSet.addAll(suggestionsList);
        preferences.edit().putStringSet(SUGGESTIONS_KEY, suggestionsSet).apply();
    }

    void onPause() {
        SharedPreferences preferences = context.getSharedPreferences(SHAREDPREFS_KEY, Context.MODE_PRIVATE);
        Set<String> suggestionsSet = preferences.getStringSet(SUGGESTIONS_KEY,
                new android.support.v4.util.ArraySet<String>());
        suggestionsList.addAll(suggestionsSet);
    }

    private class LimitedList extends LinkedList<String> {
        private final int quantitySavedSuggestions = 10;

        @Override
        public boolean add(@NonNull String s) {
            super.add(s);
            removeOldSuggestions();
            return true;
        }

        @Override
        public void addFirst(@NonNull String s) {
            super.addFirst(s);
            removeOldSuggestions();
        }

        private void removeOldSuggestions() {
            if (getCount() > quantitySavedSuggestions) {
                removeRange(quantitySavedSuggestions, getCount());
            }
        }
    }
}
