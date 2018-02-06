package com.tiufanov.denis.pixformancetask.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.tiufanov.denis.pixformancetask.ApiObject;
import com.tiufanov.denis.pixformancetask.MainActivity;
import com.tiufanov.denis.pixformancetask.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SuggestionFragmentTest extends InstrumentationTestCase {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);
    private ApiObject object;

    @Before
    public void setUp() throws Exception {
        final Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
    }

    @Test
    public void setSuggestionObjectTest_verticalScreenOrientation() throws Exception {
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        checkAllFields();
    }

    @Test
    public void setSuggestionObjectTest_horizontalScreenOrientation() throws Exception {
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        final Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        checkAllFields();
    }

    private void checkAllFields() throws InterruptedException {
        final Gson gsonObject = new Gson();
        final String answer = FileUtils.readFromResources(mActivityRule.getActivity().getApplicationContext(),
                "correct_answer.json");
        object = gsonObject.fromJson(answer, ApiObject.class);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().showFullInfoAboutFilm(object.results[0], new RequestListener() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                        countDownLatch.countDown();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                        countDownLatch.countDown();
                        return false;
                    }
                });

            }
        });
        countDownLatch.await();
        onView(withId(R.id.poster)).check(matches(isDisplayed()));
        onView(withId(R.id.nestedScrollView)).perform(swipeUp());
        Thread.sleep(200);
        onView(withId(R.id.nestedScrollView)).perform(swipeUp());
        Thread.sleep(200);
        onView(withId(R.id.vote_average_description)).check(matches(isDisplayed()));
        onView(withId(R.id.nestedScrollView)).perform(swipeUp());
        Thread.sleep(200);
        onView(withId(R.id.vote_count_description)).check(matches(isDisplayed()));
        onView(withId(R.id.name)).check(matches(isDisplayed()));
        onView(withId(R.id.vote_average)).check(matches(isDisplayed()));
        onView(withId(R.id.vote_count)).check(matches(isDisplayed()));
        onView(withId(R.id.nestedScrollView)).perform(swipeUp());
        Thread.sleep(200);
        onView(withId(R.id.overview)).check(matches(isDisplayed()));
    }
}