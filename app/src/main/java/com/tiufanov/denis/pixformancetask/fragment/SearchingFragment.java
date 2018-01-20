package com.tiufanov.denis.pixformancetask.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.tiufanov.denis.pixformancetask.R;
import com.tiufanov.denis.pixformancetask.databinding.FragmentMainBinding;

/**
 * A placeholder fragment containing searching list and shows query results.
 */
public class SearchingFragment extends Fragment {

    private SuggestionsListAdapter suggestionsListAdapter;
    private FragmentMainBinding fragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        suggestionsListAdapter = new SuggestionsListAdapter(getActivity().getApplicationContext());
        fragment = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main, container, true);
        fragment.successfulSuggestions.setAdapter(suggestionsListAdapter);
        fragment.searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.successfulSuggestions.setVisibility(View.VISIBLE);
            }
        });
        fragment.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fragment.successfulSuggestions.setVisibility(View.INVISIBLE);
                searchFilm(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        fragment.successfulSuggestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragment.successfulSuggestions.setVisibility(View.INVISIBLE);
                fragment.searchView.setQuery(suggestionsListAdapter.getItem(position), true);
            }
        });

        return fragment.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
//        suggestionsListAdapter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        suggestionsListAdapter.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (fragment != null) {
            ViewGroup parent = (ViewGroup) fragment.getRoot().getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
        }
    }

    private void searchFilm (@NonNull final String filmName) {
        //TODO: Sending request to server
        suggestionsListAdapter.moveSuggestionToTopPosition(filmName);
        suggestionsListAdapter.notifyDataSetChanged();
    }
}
