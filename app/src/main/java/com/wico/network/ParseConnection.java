package com.wico.network;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;

import java.util.ArrayList;
import java.util.List;

public class ParseConnection {


    public ParseConnection(Context context){
        Parse.enableLocalDatastore(context);
        Parse.initialize(context, "rvro91QbTePbPJKwAfB5TcMjoXzVH8ewSawqk7uk", "8W1XCtK31EAh9EXY5Fp7kbePKkT7eDO92DdxmHEr");
    }

    public void storeQuestion(Question question){
        ParseObject testObject = new ParseObject("Question");
        testObject.put("title", question.getTitle());
        testObject.put("content", question.getContent());
        testObject.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    //objectSavedSuccessfully();
                } else {
                    //objectSaveDidNotSucceed();
                }
            }
        });;

    }



    public ArrayList<Question> getQuestions(){
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
