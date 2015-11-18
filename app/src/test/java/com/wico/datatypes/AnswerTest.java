package com.wico.datatypes;

import com.parse.ParseObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnswerTest {

    private Answer answer;
    private final String ANSWER_CONTENT = "computer science is fun.";
    private final String ANSWER_AUTHOR = "Wico";
    private final String QUESTION_PARENT_ID = "4XpHgyJ5G7";



    @Before
    public void initializeAnswer(){
        ParseObject.registerSubclass(Answer.class);
        answer = new Answer.Builder().content(ANSWER_CONTENT).parentQuestionId(QUESTION_PARENT_ID).build();
    }

    @Test
    public void testSavedAnswerContent(){
        final String expected = ANSWER_CONTENT;
        final String reality = answer.getContent();
        assertEquals(expected,reality);
    }

    @Test
    public void testSavedAnswerAuthor(){
        final String expected = ANSWER_AUTHOR;
        final String reality = answer.getAuthor();
        assertEquals(expected, reality);
    }

    @Test
    public void testSavedAnswerId(){
        final String expected = QUESTION_PARENT_ID;
        final String reality = answer.getQuestionId();
        assertEquals(expected, reality);
    }
}
