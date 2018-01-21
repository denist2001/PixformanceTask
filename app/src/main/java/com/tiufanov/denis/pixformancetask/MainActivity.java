package com.tiufanov.denis.pixformancetask;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.ViewGroup;

import com.tiufanov.denis.pixformancetask.databinding.ActivityMainBinding;
import com.tiufanov.denis.pixformancetask.fragment.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity /*implements OnFullInfoShow*/ {

    private ActivityMainBinding activityMainBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        SectionsPagerAdapter mSectionsPagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());
        activityMainBinder.container.setAdapter(mSectionsPagerAdapter);
    }

    /*@Override
    public void showFullInfoAboutFilm(@NonNull SuggestionObject filmInfo) {
        replaceFragmentWithAnimation();
    }

    public void replaceFragmentWithAnimation(Fragment fragment, Direction swipeDirection){
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
