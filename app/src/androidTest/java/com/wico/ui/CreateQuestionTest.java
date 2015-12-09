package com.wico.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wico.R;
import com.wico.datatypes.Question;
import com.wico.network.ParseConnector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.wico.network.OfflineParseObjectStorer;
import com.wico.util.DependencyInjector;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CreateQuestionTest {

    private static final String TEST_PAGE_ID = "testId";
    private static final String TEST_QUESTION_CONTENT = "content";
    private static final String TEST_QUESTION_TITLE = "title";


    @Rule
    public ActivityTestRule<CreateQuestionActivity> mActivityRule =
            new ActivityTestRule<>(CreateQuestionActivity.class);

    @Before
    public void setUp(){
        DependencyInjector injector = new DependencyInjector();
        injector.inject();
        Espresso.registerIdlingResource(new WebViewIdlingResource(getActivity().findViewById(R.id.webview)));
        saveQuestion();
    }

    private void saveQuestion() {
        Intent intent = new Intent();
        intent.putExtra("parentPath",TEST_PAGE_ID);
        mActivityRule.launchActivity(intent);
        initParse(mActivityRule.getActivity());
        onView(withId(R.id.titleEditText))
                .perform(typeText(TEST_QUESTION_TITLE), closeSoftKeyboard());
        onView(withId(R.id.contentEditText))
                .perform(typeText(TEST_QUESTION_CONTENT), closeSoftKeyboard());
        onView(withId(R.id.cq_fab))
                .perform(click());
    }

    private void initParse(Context context){
        ParseConnector connector = new ParseConnector();
        connector.initialize(context);
    }

    @Test
    public void testSavedQuestion(){
        Question question = getSavedQuestion();
        assertTrue(compareQuestionFields(question));
    }

    private boolean compareQuestionFields(Question question){
        boolean equal = true;
        if(question.getContent() != TEST_QUESTION_CONTENT){
            equal = false;
        }if(question.getTitle() != TEST_QUESTION_TITLE){
            equal = false;
        }
        return equal;
    }


    private Question getSavedQuestion(){
        OfflineParseObjectStorer storer= (OfflineParseObjectStorer)ParseConnector.storer;
        return (Question)storer.getStoredObject();
    }
}
