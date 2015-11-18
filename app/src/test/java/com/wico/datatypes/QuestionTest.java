package com.wico.datatypes;



import com.parse.ParseObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class QuestionTest {

    private Question question;
    private static final String QUESTION_TITLE = "testQuestion";
    private static final String QUESTION_CONTENT = "testContent";
    private static final String QUESTION_PATH = "/testParent/Path";

    @Before
    public void initializeQuestion(){
        // although we are not using the internet,
        // the parse API requires registering the classes before using
        ParseObject.registerSubclass(Question.class);
        question = new Question.Builder().title(QUESTION_TITLE).content(QUESTION_CONTENT).parentPath(QUESTION_PATH).build();
    }

    @Test
    public void testQuestionTitle(){
        final String expected = QUESTION_TITLE;
        final String reality = question.getTitle();
        assertEquals(expected, reality);
    }

    @Test
    public void testQuestionContent(){
        final String expected = QUESTION_CONTENT;
        final String reality = question.getContent();
        assertEquals(expected, reality);
    }

    @Test
    public void testQuestionPath(){
        final String expected = QUESTION_PATH;
        final String reality = question.getString("parentPath");
        assertEquals(expected, reality);
    }

    @Test
    public void testAddRemoveAnswer(){
        question.addAnswer();
        question.removeAnswer();
        final int expected = 0;
        final int reality = question.getNumberOfAnswers();
        assertEquals(expected, reality);
    }









}