package com.wico.ui;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.parse.ParseUser;
import com.wico.R;
import com.wico.WicoApp;
import com.wico.datatypes.Question;
import com.wico.network.ParseConnector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;

@RunWith(AndroidJUnit4.class)
public class QuestionAndAnswersTest {

    private static final String TEST_QUESTION_ID = "testId";
    private static final String TEST_QUESTION_CONTENT = "content";
    private static final String TEST_QUESTION_TITLE = "title";
    private static final String TEST_QUESTION_AUTHOR = "testUser";

    private static final String TEST_ANSWER_CONTENT = "content";

    @Rule
    public ActivityTestRule<QuestionAndAnswersActivity> mActivityRule =
            new ActivityTestRule<>(QuestionAndAnswersActivity.class);

    @Before
    public void setUp(){
        initParse();
        loginToParse();
        saveQuestion();
        initActivity();
    }

    private void initActivity(){
        Intent intent = new Intent();
        intent.putExtra("questionId", TEST_QUESTION_ID);
        mActivityRule.launchActivity(intent);
    }

    private void initParse(){
        ParseConnector connector = new ParseConnector();
        connector.initialize(WicoApp.getAppContext());
    }

    private void loginToParse(){
        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().setUsername(TEST_QUESTION_AUTHOR);
    }

    private void saveQuestion(){
        ParseConnector connector = new ParseConnector();
        Question question = new Question.Builder().title(TEST_QUESTION_TITLE).content(TEST_QUESTION_CONTENT).author(TEST_QUESTION_AUTHOR).build();
        question.setObjectId(TEST_QUESTION_ID);
        connector.store(question);
    }

    @Test
    public void testAnswer(){
        onView(withId(R.id.qa_answerbox))
                .perform(typeText(TEST_ANSWER_CONTENT), closeSoftKeyboard());
        onView(withId(R.id.qa_sendAnswerButton))
                .perform(click());
        onData(hasToString(TEST_QUESTION_AUTHOR))
                .inAdapterView(withId(R.id.qa_answerList)).atPosition(0)
                .check(matches(withText(TEST_QUESTION_CONTENT)));
    }

}
