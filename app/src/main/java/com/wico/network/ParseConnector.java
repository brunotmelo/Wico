package com.wico.network;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wico.datatypes.Question;
import com.wico.exceptions.AlreadyInitializedException;
import com.wico.exceptions.DisconectedFromParseException;
import com.wico.exceptions.WicoParseException;

import java.util.ArrayList;

public class ParseConnector {

    private static boolean isConnected = false;

    public ParseConnector(){
    }

    public void initialize(Context context){
        checkNotInitialized();
        Parse.enableLocalDatastore(context);
        ParseObject.registerSubclass(Question.class);
        Parse.initialize(context, "rvro91QbTePbPJKwAfB5TcMjoXzVH8ewSawqk7uk", "8W1XCtK31EAh9EXY5Fp7kbePKkT7eDO92DdxmHEr");
        isConnected = true;
    }

    private void checkNotInitialized() {
        if(isConnected){
            throw new AlreadyInitializedException();
        }
    }

    public void storeQuestion(Question question){
        checkConnection();
        try {
            question.save();
        } catch (ParseException e) {
            throw new WicoParseException();
        }
    }

    public ArrayList<Question> getQuestions(){
        checkConnection();
        ParseQuery<Question> query = createQuery();
        ArrayList<Question> questions = new ArrayList<>();
        try{
           questions.addAll(query.find());
        }catch (ParseException e) {
            throw new WicoParseException();
        }
        return questions;
    }

    private ParseQuery<Question> createQuery() {
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereExists("content");
        query.orderByDescending("createdAt");
        return query;
    }

    private void checkConnection(){
        if(!isConnected){
            throw new DisconectedFromParseException();
        }
    }
}
