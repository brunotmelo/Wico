package com.wico.ui;


import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityTestCase;

import com.wico.R;
import com.wico.TestWicoApp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WicoPageActivityTest extends ActivityTestCase{

    @Rule
    public ActivityTestRule<PageActivity> mActivityRule =
            new ActivityTestRule<>(PageActivity.class);

    @Before
    public void setUp(){
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        TestWicoApp app = (TestWicoApp) instrumentation.getTargetContext()
                .getApplicationContext();
        System.out.println(app.getClass().toString());
    }

    @Test
    public void openPageTest() {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        onView(withId(R.id.pv_markdownText)).check(matches(withText("markdown")));
    }

}
