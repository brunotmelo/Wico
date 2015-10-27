package com.wico;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.parse.ParseObject;

public class QuestionTest extends ApplicationTestCase<Application> {

    public QuestionTest() {
        super(Application.class);
    }

    public void testQuestionCreation() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }

    public void testQuestionStorage() throws Exception {
        ParseObject testQuestion = new ParseObject("question");
        testQuestion.put("title", "content");
        testQuestion.saveInBackground();
    }

    public int testNumberOfAwnsers() throws Exception{
        return 5;
    }

    public String testTitle(){
       String testTitle = "testTitle";
       return testTitle;
    }

    public String testContent(){
       String testContent = "testContent";
        return testContent;
    }




}

