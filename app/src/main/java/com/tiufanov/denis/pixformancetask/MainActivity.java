package com.tiufanov.denis.pixformancetask;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.tiufanov.denis.pixformancetask.fragment.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // Create the adapter that will return a fragment for each of the two
    // primary sections of the activity.
    /*
        The {@link android.support.v4.view.PagerAdapter} that will provide
        fragments for each of the sections. We use a
        {@link FragmentPagerAdapter} derivative, which will keep every
        loaded fragment in memory. If this becomes too memory intensive, it
        may be best to switch to a
        {@link android.support.v4.app.FragmentStatePagerAdapter}.
    */
        SectionsPagerAdapter mSectionsPagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}
