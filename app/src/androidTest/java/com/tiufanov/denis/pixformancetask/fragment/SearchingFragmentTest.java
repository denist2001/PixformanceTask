package com.tiufanov.denis.pixformancetask.fragment;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.widget.EditText;

import com.tiufanov.denis.pixformancetask.FilmsRepository;
import com.tiufanov.denis.pixformancetask.MainActivity;
import com.tiufanov.denis.pixformancetask.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Nonnull;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SearchingFragmentTest extends InstrumentationTestCase {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        FilmsRepository.API_REQUEST = server.url("/").toString();
        final Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.search_view))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSearchViewIsShown() throws Exception {
        onView(withId(R.id.suggestionsRecyclerView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    @Test
    public void testStartSearch_correctAnswer_willShowItem() throws Exception {
        prepareFragment("correct_answer.json");

        onView(withId(R.id.suggestionsRecyclerView)).check(matches(isDisplayed()));

        onView(withText("Sailor Moon Super S: The Movie")).check(matches(isDisplayed()));
    }

    @Test
    public void testStartSearch_emptyAnswer_willShowAlertDialog() throws Exception {

        prepareFragment("empty.json");

        onView(withText("Ooops... Nothing were found. Try to find more common films.")).check(matches(isDisplayed()));
    }

    @Test
    public void testStartSearch_errorAnswer_willShowAlertDialog() throws Exception {

        prepareFragment("error_responce.json");

        onView(withText("Server doesn't answer.")).check(matches(isDisplayed()));
    }

    @Test
    public void testStartSearch_invalidApiKey_willShowAlertDialog() throws Exception {

        prepareFragment("invalid_api_key.json");

        onView(withText("Invalid API key: You must be granted a valid key.")).check(matches(isDisplayed()));
    }

    private void prepareFragment(@Nonnull final String fileName) {

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(FileUtils.readFromResources(getInstrumentation().getTargetContext(),
                        fileName)));

        onView(withId(R.id.search_view)).perform(click());

        onView(withId(R.id.search_button)).perform(click());

        onView(withId(R.id.successful_suggestions)).check(matches(isDisplayed()));

        onView(isAssignableFrom(EditText.class)).perform(typeText("batman"), pressImeActionButton());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        server.shutdown();
    }
}
