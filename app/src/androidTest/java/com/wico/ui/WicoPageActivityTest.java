package com.wico.ui;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wico.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.wico.util.DependencyInjector;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class WicoPageActivityTest {

    @Rule
    public ActivityTestRule<PageActivity> mActivityRule =
            new ActivityTestRule<>(PageActivity.class);

    @Before
    public void setUp(){
        DependencyInjector injector = new DependencyInjector();
        injector.inject();
    }

    @Test
    public void openPageTest() {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        onView(withId(R.id.pv_markdownText)).check(matches(withText("markdown")));
    }

}
