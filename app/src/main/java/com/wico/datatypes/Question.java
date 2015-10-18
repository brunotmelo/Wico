package com.wico.datatypes;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albertbarr on 10/7/15.
 */
public class Question {

    String title;
    String content;
    JSONArray awnsers;

    public Question(String title, String content) {
        this.title = title;
        this. content = content;
    }

    public void add(){
    }



    private void testQuestionStorage(){
        Question testQuestion = new Question(content, title);
        ParseObject testObject = new ParseObject("Question");
        testObject.put("content", content);
        testObject.put("title",title);
        testObject.put();
        testObject.saveInBackground();
    }
    private void testQueryForContent(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
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
    private void testQueryForTitle(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Title");
        query.whereExists("title");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    Log.d("title", "Retrieved" + content);
                } else {
                    Log.d("title", "Error: " + e.getMessage());
                }
            }
        });
    }
}