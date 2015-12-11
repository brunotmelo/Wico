package com.wico.ui;


import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.parse.ParseException;
import com.wico.R;
import com.wico.datatypes.WicoPage;
import com.wico.network.ParseConnector;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CreatePageTest {

    private final static String TEST_PAGE_ID = "whatever";
    private final static String TEST_CHILDREN_PAGE_TITLE = "computer science";
    private final static String TEST_CHILDREN_PAGE_CONTENT = "the best course of the world";

    private WicoPage savedPage;

    @Rule
    public ActivityTestRule<CreatePageActivity> mActivityRule =
            new ActivityTestRule<>(CreatePageActivity.class);

    @Before
    public void setUp(){
        initActivity();
        savePage();
        getPage();
    }

    private void initActivity(){
        Intent intent = new Intent();
        intent.putExtra("parentId", TEST_PAGE_ID);
        mActivityRule.launchActivity(intent);
        initParse(mActivityRule.getActivity());
    }

    private void initParse(Context context){
        ParseConnector connector = new ParseConnector();
        connector.initialize(context);
    }

    private void savePage(){
        onView(withId(R.id.cp_titleEditText))
                .perform(typeText(TEST_CHILDREN_PAGE_TITLE), closeSoftKeyboard());
        onView(withId(R.id.cp_contentEditText))
                .perform(typeText(TEST_CHILDREN_PAGE_CONTENT), closeSoftKeyboard());
        onView(withId(R.id.cp_fab))
                .perform(click());
    }

    private void getPage(){
        ParseConnector connector = new ParseConnector();
        ArrayList<WicoPage> pages = connector.getChildrenPages(TEST_PAGE_ID);
        savedPage = pages.get(0);
    }

    @Test
    public void testSavedPage(){
        assertTrue(savedPageMatchesPassedValues());
    }

    private boolean savedPageMatchesPassedValues(){
        boolean equals = true;
        if(!TEST_CHILDREN_PAGE_TITLE.equals(savedPage.getTitle())){
            equals = false;
        }if(!TEST_CHILDREN_PAGE_CONTENT.equals(savedPage.getContent())){
            equals = true;
        }
        return equals;
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
