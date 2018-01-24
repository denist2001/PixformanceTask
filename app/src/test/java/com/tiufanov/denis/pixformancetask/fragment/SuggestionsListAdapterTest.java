package com.tiufanov.denis.pixformancetask.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionsListAdapterTest {

    private static String SHAREDPREFS_KEY = "SuggestionsList";
    private static String SUGGESTIONS_KEY = "suggestions";

    @Mock
    private Context context;
    @Mock
    private SharedPreferences sharedPreferences;
    @Mock
    private SharedPreferences.Editor preferencsEditor;

    private final Set suggestionsSet = new LinkedHashSet(10);

    private SuggestionsListAdapter target;

    @Before
    public void setUp() throws Exception {
        suggestionsSet.add("transporter");
        suggestionsSet.add("omen");
        suggestionsSet.add("zombie");
        suggestionsSet.add("batman");
        suggestionsSet.add("freinds");
        suggestionsSet.add("new day");
        suggestionsSet.add("superman");
        suggestionsSet.add("amelie");
        suggestionsSet.add("monsters");
        suggestionsSet.add("konstantine");
        when(context.getSharedPreferences(SHAREDPREFS_KEY, Context.MODE_PRIVATE)).thenReturn(sharedPreferences);
        when(sharedPreferences.getStringSet(eq(SUGGESTIONS_KEY), ArgumentMatchers.<String>anySet())).thenReturn(suggestionsSet);
        when(sharedPreferences.edit()).thenReturn(preferencsEditor);
        when(preferencsEditor.putStringSet(eq(SUGGESTIONS_KEY), ArgumentMatchers.<String>anySet())).thenReturn(preferencsEditor);
        target = new SuggestionsListAdapter(context);
        target.moveSuggestionToTopPosition("cars");
        target.moveSuggestionToTopPosition("transporter");
        target.moveSuggestionToTopPosition("omen");
        target.moveSuggestionToTopPosition("zombie");
        target.moveSuggestionToTopPosition("batman");
        target.moveSuggestionToTopPosition("freinds");
        target.moveSuggestionToTopPosition("new day");
        target.moveSuggestionToTopPosition("superman");
        target.moveSuggestionToTopPosition("amelie");
        target.moveSuggestionToTopPosition("monsters");
        target.moveSuggestionToTopPosition("konstantine");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void moveNewSuggestionToTopPosition() throws Exception {
        assertTrue(target.getCount() == 10);
        assertTrue(target.getItem(0).equals("konstantine"));
        assertTrue(target.getItem(9).equals("transporter"));

        target.moveSuggestionToTopPosition("advocate");

        assertTrue(target.getCount() == 10);
        assertTrue(target.getItem(0).equals("advocate"));
        assertTrue(target.getItem(9).equals("omen"));
    }


    @Test
    public void moveExistedSuggestionToTopPosition() throws Exception {
        assertTrue(target.getCount() == 10);
        assertTrue(target.getItem(6).equals("batman"));

        target.moveSuggestionToTopPosition("batman");

        assertTrue(target.getCount() == 10);
        assertTrue(target.getItem(0).equals("batman"));
    }

    @Test
    public void onResume() throws Exception {
        target.onResume();

        assertTrue(target.getItem(0).contains("transporter"));
        assertTrue(target.getItem(9).contains("konstantine"));

    }

    @Test
    public void onPause() throws Exception {
        target.onPause();

        SharedPreferences preferences = context.getSharedPreferences(SHAREDPREFS_KEY, Context.MODE_PRIVATE);
        Set<String> suggestionsSet = preferences.getStringSet(SUGGESTIONS_KEY,
                new LinkedHashSet<String>(10));

        assertTrue(suggestionsSet.contains("transporter"));
        assertTrue(suggestionsSet.contains("konstantine"));
    }

}