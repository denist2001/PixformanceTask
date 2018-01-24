package com.tiufanov.denis.pixformancetask.fragment;

import android.content.Context;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionsListAdapterTest {
    @Mock
    Context context;
    SuggestionsListAdapter target;
    @Before
    public void setUp() throws Exception {
        target = new SuggestionsListAdapter(context);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void moveSuggestionToTopPosition() throws Exception {
        Assertions.assertThat(target.getCount() == 10);
        target.moveSuggestionToTopPosition("batman");
    }

    @Test
    public void onResume() throws Exception {
    }

    @Test
    public void onPause() throws Exception {
    }

}