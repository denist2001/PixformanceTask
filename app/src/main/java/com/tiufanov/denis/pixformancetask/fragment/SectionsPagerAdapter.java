package com.tiufanov.denis.pixformancetask.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tiufanov.denis.pixformancetask.Direction;
import com.tiufanov.denis.pixformancetask.SwipeFragment;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
//    private final SwipeFragment swipeFragment;

    public SectionsPagerAdapter(@NonNull FragmentManager fm/*, SwipeFragment swipeFragment*/) {
        super(fm);
//        this.swipeFragment = swipeFragment;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a SearchingFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return SearchingFragment.newInstance(0);
            case 1:
                return SuggestionFragment.newInstance(999);
            default:
                return SearchingFragment.newInstance(0);
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
