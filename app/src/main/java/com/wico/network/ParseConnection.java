package com.wico.network;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wico.datatypes.Question;

import java.util.List;

/**
 * Created by Bruno on 18/10/2015.
 */
public class ParseConnection {


    public void storeQuestion(){
    }

    public Question getQuestion(){
        return null;
    }

    /*private void testQuestionStorage(){
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
    }*/


}
