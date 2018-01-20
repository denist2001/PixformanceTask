package com.tiufanov.denis.pixformancetask.fragment;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SuggestionsListAdapter extends BaseAdapter {
    private final LimitedList suggestionsList;

    SuggestionsListAdapter() {
        suggestionsList= new LimitedList();
        suggestionsList.addFirst("January");
        suggestionsList.addFirst("February");
        suggestionsList.addFirst("March");
        suggestionsList.addFirst("April");
        suggestionsList.addFirst("May");
        suggestionsList.addFirst("June");
        suggestionsList.addFirst("July");
        suggestionsList.addFirst("August");
        suggestionsList.addFirst("September");
        suggestionsList.addFirst("October");
        suggestionsList.addFirst("November");
        suggestionsList.addFirst("December");
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
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        TextView suggestionTextView = new TextView(parent.getContext());
        suggestionTextView.setTextSize(25);
        params.verticalBias = 1;
        suggestionTextView.setLayoutParams(params);
        suggestionTextView.setMinHeight(50);
        suggestionTextView.setText(suggestionsList.get(position));
        return suggestionTextView;
    }

    public void moveSuggestionToFirstPosition(int position) {
        String movedString = suggestionsList.remove(position);
        suggestionsList.add(movedString);
    }

    private class LimitedList extends LinkedList<String> {

        @Override
        public boolean add(@NonNull String s) {
            super.add(s);
            if (getCount() > 10) {
                removeRange(10, getCount());
            }
            return true;
        }

        @Override
        public void addFirst(@NonNull String s) {
            super.addFirst(s);
            if (getCount() > 10) {
                removeRange(10, getCount());
            }
        }
    }
}
