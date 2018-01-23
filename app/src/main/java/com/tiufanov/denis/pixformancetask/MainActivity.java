package com.tiufanov.denis.pixformancetask;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.tiufanov.denis.pixformancetask.databinding.ActivityMainBinding;
import com.tiufanov.denis.pixformancetask.fragment.SectionsPagerAdapter;
import com.tiufanov.denis.pixformancetask.fragment.SuggestionFragment;

public class MainActivity extends AppCompatActivity implements SwipeDirectionListener, FullInfoShowListener {

    private ActivityMainBinding activityMainBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinder.container.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), this));

        onSwipeDirection(Direction.RIGHT);
    }

    @Override
    public void onSwipeDirection(@NonNull Direction direction) {
        switch (direction) {
            case LEFT:
                activityMainBinder.container.setCurrentItem(1, true);
                break;
            case RIGHT:
                activityMainBinder.container.setCurrentItem(0, true);
                break;
        }
    }

    //FullInfoShowListener region
    @Override
    public void showFullInfoAboutFilm(@NonNull SuggestionObject filmInfo) {
        String tag = "android:switcher:" + activityMainBinder.container.getId() + ":" + 1;
        SuggestionFragment fragment = (SuggestionFragment) getSupportFragmentManager().findFragmentByTag(tag);
        fragment.setSuggestionObject(filmInfo);
        onSwipeDirection(Direction.LEFT);
    }
    //FullInfoShowListener end
}
