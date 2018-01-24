package com.tiufanov.denis.pixformancetask.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(@NonNull final FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(final int position) {
        if (position == 1) {
            return new SuggestionFragment();
        }
        return new SearchingFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
