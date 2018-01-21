package com.tiufanov.denis.pixformancetask.fragment;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.tiufanov.denis.pixformancetask.FilmsRepository;
import com.tiufanov.denis.pixformancetask.OnFilmLoaded;
import com.tiufanov.denis.pixformancetask.R;
import com.tiufanov.denis.pixformancetask.SuggestionObject;
import com.tiufanov.denis.pixformancetask.SuggestionsRecyclerAdapter;
import com.tiufanov.denis.pixformancetask.databinding.FragmentMainBinding;

import java.util.ArrayList;

/**
 * A placeholder fragment containing searching list and shows query results.
 */
public class SearchingFragment extends Fragment implements OnFilmLoaded{

    private SuggestionsListAdapter suggestionsListAdapter;
    private FragmentMainBinding fragment;
    private FilmsRepository repository = new FilmsRepository();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        suggestionsListAdapter = new SuggestionsListAdapter(getActivity().getApplicationContext());
        fragment = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main, container, false);
        fragment.successfulSuggestions.setAdapter(suggestionsListAdapter);
        fragment.searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.successfulSuggestions.bringToFront();
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
        suggestionsListAdapter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        suggestionsListAdapter.onPause();
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

    @Override
    public void onSearchResultsLoaded(@NonNull final String filmName,
                                      @NonNull final ArrayList<SuggestionObject> suggestions) {
        Log.d("Valid answer", suggestions.toString());
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                suggestionsListAdapter.moveSuggestionToTopPosition(filmName);
                suggestionsListAdapter.notifyDataSetChanged();
                fragment.suggestionsRecyclerView.setVisibility(View.VISIBLE);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                        fragment.suggestionsRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
                fragment.suggestionsRecyclerView.addItemDecoration(dividerItemDecoration);
                fragment.suggestionsRecyclerView.setLayoutManager(
                        new LinearLayoutManager(fragment.getRoot().getContext()));
                fragment.suggestionsRecyclerView.setAdapter(
                        new SuggestionsRecyclerAdapter(fragment.getRoot().getContext(), suggestions));
            }
        });

    }

    @Override
    public void onSearchResultsError(@NonNull final String filmName, @NonNull final String error) {
        Log.d("Fail answer", error);
    }

    private void searchFilm (@NonNull final String filmName) {
        repository.requestFilm(filmName, this);
    }

}
