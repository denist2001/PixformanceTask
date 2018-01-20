package com.tiufanov.denis.pixformancetask;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.ViewGroup;

import com.tiufanov.denis.pixformancetask.databinding.ActivityMainBinding;
import com.tiufanov.denis.pixformancetask.fragment.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
    /*
        The {@link android.support.v4.view.PagerAdapter} that will provide
        fragments for each of the sections. We use a
        {@link FragmentPagerAdapter} derivative, which will keep every
        loaded fragment in memory. If this becomes too memory intensive, it
        may be best to switch to a
        {@link android.support.v4.app.FragmentStatePagerAdapter}.
    */
        // Set up the ViewPager with the sections adapter.
        SectionsPagerAdapter mSectionsPagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());
        activityMainBinder.container.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityMainBinder.container != null) {
            ViewGroup parent = (ViewGroup) activityMainBinder.container.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
        }
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
