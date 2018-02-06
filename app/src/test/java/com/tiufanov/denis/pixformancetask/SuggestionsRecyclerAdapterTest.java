package com.tiufanov.denis.pixformancetask;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.bumptech.glide.request.RequestListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionsRecyclerAdapterTest {

    private final String pictureUrl = "https://image.tmdb.org/t/p/";
    private SuggestionsRecyclerAdapter recyclerAdapter;

    @Mock
    private Context context;
    @Mock
    private Resources resources;
    @Mock
    private DisplayMetrics displayMetrics;

    @Before
    public void setUp() throws Exception {
        when(context.getResources()).thenReturn(resources);
        when(resources.getDisplayMetrics()).thenReturn(displayMetrics);
        displayMetrics.density = 1.0F;
        recyclerAdapter = new SuggestionsRecyclerAdapter(context,
                new ArrayList<SuggestionObject>(),
                new FullInfoShowListener() {
                    @Override
                    public void showFullInfoAboutFilm(@NonNull SuggestionObject filmInfo,
                                                      @Nullable RequestListener requestListener) {
                    }
                });
    }

    @Test
    public void getPictureUrl_withdensity1_0_willReturn_w92() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(1.0F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w92"));
    }

    @Test
    public void getPictureUrl_withdensity1_5_willReturn_w92() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(1.5F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w92"));
    }

    @Test
    public void getPictureUrl_withdensity1_8_willReturn_w185() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(1.8F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w185"));
    }

    @Test
    public void getPictureUrl_withdensity2_0_willReturn_w185() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(2.0F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w185"));
    }

    @Test
    public void getPictureUrl_withdensity2_5_willReturn_w500() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(2.5F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w500"));
    }

    @Test
    public void getPictureUrl_withdensity3_0_willReturn_w500() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(3.0F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w500"));
    }

    @Test
    public void getPictureUrl_withdensity3_5_willReturn_w780() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(3.5F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w780"));
    }

    @Test
    public void getPictureUrl_withdensity4_0_willReturn_w780() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(4.0F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w780"));
    }

    @Test
    public void getPictureUrl_withdensity4_5_willReturn_w780() throws Exception {
        final String path = recyclerAdapter.getPictureUrl(4.5F);
        assertTrue(path.equalsIgnoreCase(pictureUrl + "w780"));
    }

}