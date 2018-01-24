package com.tiufanov.denis.pixformancetask.fragment;

import android.app.AlertDialog;
import android.content.Context;
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

import com.tiufanov.denis.pixformancetask.FilmLoadListener;
import com.tiufanov.denis.pixformancetask.FilmsRepository;
import com.tiufanov.denis.pixformancetask.FullInfoShowListener;
import com.tiufanov.denis.pixformancetask.R;
import com.tiufanov.denis.pixformancetask.SuggestionObject;
import com.tiufanov.denis.pixformancetask.SuggestionsRecyclerAdapter;
import com.tiufanov.denis.pixformancetask.databinding.FragmentMainBinding;

import java.util.ArrayList;

/**
 * A placeholder fragmentMainBinding containing searching list and shows query results.
 */
public class SearchingFragment extends Fragment implements FilmLoadListener {

    private SuggestionsListAdapter suggestionsListAdapter;
    private FragmentMainBinding fragmentMainBinding;
    private FilmsRepository repository = new FilmsRepository();
    private FullInfoShowListener fullInfoShowListener;

    public SearchingFragment() {
        Log.d("SearchView", "created");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main, container, false);
        if (getActivity() == null || getActivity().getApplicationContext() == null) {
            return fragmentMainBinding.getRoot();
        }
        suggestionsListAdapter = new SuggestionsListAdapter(getActivity().getApplicationContext());
        fragmentMainBinding.successfulSuggestions.setAdapter(suggestionsListAdapter);
        fragmentMainBinding.searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentMainBinding.successfulSuggestions.bringToFront();
                fragmentMainBinding.successfulSuggestions.setVisibility(View.VISIBLE);
            }
        });
        fragmentMainBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fragmentMainBinding.successfulSuggestions.setVisibility(View.INVISIBLE);
                searchFilm(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        fragmentMainBinding.successfulSuggestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentMainBinding.successfulSuggestions.setVisibility(View.INVISIBLE);
                fragmentMainBinding.searchView.setQuery(suggestionsListAdapter.getItem(position), true);
            }
        });

        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fullInfoShowListener = (FullInfoShowListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
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
    }

    private void searchFilm(@NonNull final String filmName) {
        repository.requestFilm(filmName, this);
    }

    //FilmLoadListener region
    @Override
    public void onSearchResultsLoaded(@NonNull final String filmName,
                                      @NonNull final ArrayList<SuggestionObject> suggestions) {
        Log.d("Valid answer", suggestions.toString());
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (suggestions.isEmpty()) {
                    AlertDialog.Builder alertDialog =
                            new AlertDialog.Builder(fragmentMainBinding.getRoot().getContext());
                    alertDialog.setMessage("Ooops... Nothing were found. Try to find more common films.");
                    alertDialog.create().show();
                    return;
                }
                suggestionsListAdapter.moveSuggestionToTopPosition(filmName);
                suggestionsListAdapter.notifyDataSetChanged();
                fragmentMainBinding.suggestionsRecyclerView.setVisibility(View.VISIBLE);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                        fragmentMainBinding.suggestionsRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
                fragmentMainBinding.suggestionsRecyclerView.addItemDecoration(dividerItemDecoration);
                fragmentMainBinding.suggestionsRecyclerView.setLayoutManager(
                        new LinearLayoutManager(fragmentMainBinding.getRoot().getContext()));
                fragmentMainBinding.suggestionsRecyclerView.setAdapter(
                        new SuggestionsRecyclerAdapter(fragmentMainBinding.getRoot().getContext(), suggestions, fullInfoShowListener));
            }
        });

    }

    @Override
    public void onSearchResultsError(@NonNull final String filmName, @NonNull final String error) {
        Log.d("Fail answer", error);
    }
    //FilmLoadListener end
}
