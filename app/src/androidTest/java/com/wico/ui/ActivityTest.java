package com.wico.ui;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.wico.network.ParseConnector;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityTest {

    //@Rule
    //public ActivityTestRule<PageActivity> mActivityRule = new ActivityTestRule(MainActivity.class);


    @Test
    public void listGoesOverTheFold() {
        //onView(withText("Hello world!")).check(matches(isDisplayed()));
        System.out.println("hello");
        assertTrue(true);
    }

    private void injectTestFields(){

    }

}
