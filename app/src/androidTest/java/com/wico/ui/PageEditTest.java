package com.wico.ui;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityTestCase;

import com.parse.ParseException;
import com.wico.R;
import com.wico.datatypes.WicoPage;
import com.wico.network.ParseConnector;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PageEditTest extends ActivityTestCase{

    private static final String TEST_PAGE_PARENT = "";
    private static final String TEST_PAGE_ID = "abcd1234";
    private static final String TEST_PAGE_TITLE = "Ball state Offline";
    private static final String TEST_PAGE_CONTENT = "markdown text to be rendered" ;
    private static final String TEST_PAGE_EDIT_CONTENT = "markdown text after editted";

    @Rule
    public ActivityTestRule<PageActivity> mActivityRule =
            new ActivityTestRule<>(PageActivity.class);

    @Before
    public void setUp(){
        savePage();
        Intent intent = new Intent();
        intent.putExtra("pageId", TEST_PAGE_ID);
        mActivityRule.launchActivity(intent);
    }

    private void savePage(){
        ParseConnector connector = new ParseConnector();
        WicoPage page = new WicoPage.Builder().title(TEST_PAGE_TITLE).content(TEST_PAGE_CONTENT).parentId(TEST_PAGE_PARENT).build();
        page.setObjectId(TEST_PAGE_ID);
        connector.store(page);
    }

    @Test
    public void editPageTest(){
        onView(withId(R.id.page_fab))
                .perform(click());
        onView(withId(R.id.pv_editText))
                .perform(clearText())
                .perform(typeText(TEST_PAGE_EDIT_CONTENT), closeSoftKeyboard());
        onView(withId(R.id.page_fab))
                .perform(click());
        onView(withId(R.id.pv_markdownText))
                .check(matches(withText(TEST_PAGE_EDIT_CONTENT)));
    }

    @After
    public void clean(){
        try {
            WicoPage.unpinAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
