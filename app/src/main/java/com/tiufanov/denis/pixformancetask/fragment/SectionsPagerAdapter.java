package com.tiufanov.denis.pixformancetask.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.tiufanov.denis.pixformancetask.Direction;
import com.tiufanov.denis.pixformancetask.MoveToFilmDetails;
import com.tiufanov.denis.pixformancetask.MoveToSearch;
import com.tiufanov.denis.pixformancetask.OnSwipeDirection;
import com.tiufanov.denis.pixformancetask.SuggestionObject;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final String SUGGESTION_KEY = "Suggestion";
    private SuggestionObject suggestionObject;
    @NonNull
    private OnSwipeDirection onSwipeDirection;

    public SectionsPagerAdapter(@NonNull final FragmentManager fm,
                                @NonNull final OnSwipeDirection onSwipeDirection) {
        super(fm);
        this.onSwipeDirection = onSwipeDirection;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        if (suggestionObject != null) {
            final Bundle bundle = new Bundle();
            bundle.putParcelable(SUGGESTION_KEY, suggestionObject);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public Fragment getItem(final int position) {
        if (position == 1) {
            final SuggestionFragment suggestionFragment = new SuggestionFragment();

            final Bundle bundle = new Bundle();
            bundle.putParcelable(SUGGESTION_KEY, suggestionObject);
            suggestionFragment.setArguments(bundle);

            suggestionFragment.setMoveToSearch(new MoveToSearch() {
                @Override
                public void onMoveToSearchFragment() {
                    onSwipeDirection.onSwipeDirection(Direction.RIGHT);
                }
            });
            return suggestionFragment;
        }
        final SearchingFragment searchingFragment = new SearchingFragment();
        searchingFragment.setMoveToFilmDetails(new MoveToFilmDetails() {
            @Override
            public void onMoveToFilmDetails(@NonNull SuggestionObject suggestionObject) {
                SectionsPagerAdapter.this.suggestionObject = suggestionObject;
                onSwipeDirection.onSwipeDirection(Direction.LEFT);
            }
        });
        return searchingFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
