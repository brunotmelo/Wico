package com.wico.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.wico.R;
import com.wico.datatypes.Question;
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
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CreateQuestionTest {

    private static final String TEST_PAGE_ID = "testId";
    private static final String TEST_QUESTION_CONTENT = "content";
    private static final String TEST_QUESTION_TITLE = "title";
    private static final String TEST_QUESTION_AUTHOR = "testUser";

    @Rule
    public ActivityTestRule<CreateQuestionActivity> mActivityRule =
            new ActivityTestRule<>(CreateQuestionActivity.class);

    @Before
    public void setUp(){
        initActivity();
        loginToParse();
    }

    private void initActivity(){
        Intent intent = new Intent();
        intent.putExtra("parentPageId", TEST_PAGE_ID);
        mActivityRule.launchActivity(intent);
        initParse(mActivityRule.getActivity());
    }

    private void initParse(Context context){
        ParseConnector connector = new ParseConnector();
        connector.initialize(context);
    }

    private void loginToParse(){
        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().setUsername(TEST_QUESTION_AUTHOR);
    }

    private void saveQuestion() {
        onView(withId(R.id.titleEditText))
                .perform(typeText(TEST_QUESTION_TITLE), closeSoftKeyboard());
        onView(withId(R.id.contentEditText))
                .perform(typeText(TEST_QUESTION_CONTENT), closeSoftKeyboard());
        onView(withId(R.id.cq_fab))
                .perform(click());
    }


    @Test
    public void testUser(){
        assertEquals(TEST_QUESTION_AUTHOR, ParseUser.getCurrentUser().getUsername());
    }

    @Test
    public void testSavedQuestion(){
        saveQuestion();
        Question question = getSavedQuestion();
        assertTrue(compareSavedQuestionFields(question));
    }

    private boolean compareSavedQuestionFields(Question question){
        boolean equal = true;
        if(!question.getContent().equals(TEST_QUESTION_CONTENT)){
            equal = false;
        }if(!question.getTitle().equals(TEST_QUESTION_TITLE)){
            equal = false;
        }if(!question.getAuthor().equals(TEST_QUESTION_AUTHOR)){
            equal = false;
        }

        return equal;
    }

    private Question getSavedQuestion(){
        ParseConnector connector = new ParseConnector();
        ArrayList<Question> questions = connector.getQuestions(TEST_PAGE_ID);
        return questions.get(0);
    }

    @After
    public void clean(){
        try {
            Question.unpinAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
