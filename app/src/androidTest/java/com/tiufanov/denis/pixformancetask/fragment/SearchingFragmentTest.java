package com.tiufanov.denis.pixformancetask.fragment;

import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.tiufanov.denis.pixformancetask.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SearchingFragmentTest extends InstrumentationTestCase {

    @Rule
    public FragmentTestRule<SearchingFragment> fragmentTestRule =
            new FragmentTestRule<>(SearchingFragment.class);
//    private MockWebServer server;

    @Before
    public void setUp() throws Exception {

        // Launch the activity to make the fragment visible
//        fragmentTestRule.launchActivity(new Intent(fragmentTestRule.getActivity(), TestActivity.class));
    }

    @Test
    public void onCreateView() throws Exception {

        onView(withId(R.id.search_view)).check(matches(isDisplayed()));
    }

    @Test
    public void onAttach() throws Exception {
    }

    @Test
    public void onResume() throws Exception {
    }

    @Test
    public void onPause() throws Exception {
    }

    @Test
    public void onDestroyView() throws Exception {
    }

    @Test
    public void onSearchResultsLoaded() throws Exception {
    }

    @Test
    public void onSearchResultsError() throws Exception {
    }

}