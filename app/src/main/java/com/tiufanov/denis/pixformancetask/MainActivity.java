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
//        ActivityMainBinding activityMainBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.

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
        // Set up the ViewPager with the sections adapter.
    /*
        The {@link ViewPager} that will host the section contents.
    */
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(mSectionsPagerAdapter);
//        activityMainBinder.container.setAdapter(mSectionsPagerAdapter);
    }

    /*public void replaceFragmentWithAnimation(Fragment fragment, Direction swipeDirection){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_left);
        transaction.replace(R.id.container, fragment);
//        transaction.addToBackStack(tag);
        transaction.commit();
    }

    private class SwipeFragmentNeeded implements SwipeFragment {

        @Override
        public void onSwipeFragmentNeeded(Fragment fragment, Direction swipeDirection) {
            replaceFragmentWithAnimation(fragment, swipeDirection);
        }
    }*/
}
