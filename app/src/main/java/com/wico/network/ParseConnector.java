package com.wico.network;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.wico.datatypes.Question;
import com.wico.exceptions.WicoParseException;

import java.util.ArrayList;

public class ParseConnector {


    public ParseConnector(){
    }

    public void storeQuestion(Question question){
        try {
            question.save();
        } catch (ParseException e) {
            throw new WicoParseException();
        }
    }



    public ArrayList<Question> getQuestions(){
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereExists("content");
        query.orderByDescending("createdAt");
        ArrayList<Question> questions = new ArrayList<>();
        try{
           questions.addAll(query.find());
        }catch (ParseException e) {
            throw new WicoParseException();
        }

        return questions;

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
