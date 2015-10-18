package com.wico;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 10/14/2015.
 */
public class Answer {

    String content;

    public Answer(String content){
        this.content = content;
    }

    private JSONArray arrayOfAnswers(){
        ParseObject testObjectAnswer = new ParseObject("TestAnswer");
        JSONArray arrayOfAnswers = testObjectAnswer.getJSONArray("TestAnswer");
        return arrayOfAnswers;

    }

    private void testAnswerStorage(){
        Answer testAnswer = new Answer("content");
        ParseObject testObject = new ParseObject("Answer");
        testObject.put("content", content);
        testObject.saveInBackground();
    }
    private void testArrayStorage(){
        ParseObject testArray = new ParseObject("Answer Array");
        ArrayList<Answer> answers = new ArrayList<Answer>();

        testArray.put("Answers", answers);
        testArray.saveInBackground();
    }
    private void testQueryForSingleAnswerContent(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Answer");
        query.whereExists("content");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    Log.d("content", "Retrieved" + content);
                } else {
                    Log.d("content", "Error: " + e.getMessage());
                }
            }
        });
    }
    private void testQueryForArrayAnswerContent(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Answer");
        query.whereExists("answers");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    Log.d("answers", "Retrieved" + content);
                } else {
                    Log.d("answers", "Error: " + e.getMessage());
                }
            }
        });
    }
}

