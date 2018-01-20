package com.tiufanov.denis.pixformancetask.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tiufanov.denis.pixformancetask.R;

/**
 * A placeholder fragment containing searching list and shows query results.
 */
public class SearchingFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SearchingFragment newInstance(int sectionNumber) {
        SearchingFragment fragment = new SearchingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        final SuggestionsListAdapter suggestionsListAdapter = new SuggestionsListAdapter();
        final ListView suggestionsListView = fragment.findViewById(R.id.successful_suggestions);
        suggestionsListView.setAdapter(suggestionsListAdapter);
//DataBindingUtil.
        final SearchView searchView = fragment.findViewById(R.id.search_view);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestionsListView.setVisibility(View.VISIBLE);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                suggestionsListView.setVisibility(View.INVISIBLE);
                searchFilm(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        suggestionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                suggestionsListView.setVisibility(View.INVISIBLE);
                searchView.setQuery(suggestionsListAdapter.getItem(position), true);
            }
        });

        return fragment;
    }

    private void searchFilm (@NonNull final String filmName) {
        //TODO: Sending request to server
        Log.d("Searching", filmName);
    }
}
