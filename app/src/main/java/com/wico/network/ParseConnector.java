package com.wico.network;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.exceptions.AlreadyInitializedException;
import com.wico.exceptions.DisconectedFromParseException;
import com.wico.exceptions.WicoParseException;

import java.util.ArrayList;

public class ParseConnector {

    private static boolean isConnected = false;

    public ParseConnector() {
    }

    /**This method must be run in the first activity
     * of the application.
     */
    public void initialize(Context context) {
        checkNotInitialized();
        Parse.enableLocalDatastore(context);
        registerParseSubclasses();
        Parse.initialize(context, "rvro91QbTePbPJKwAfB5TcMjoXzVH8ewSawqk7uk", "8W1XCtK31EAh9EXY5Fp7kbePKkT7eDO92DdxmHEr");
        isConnected = true;
    }

    public void registerParseSubclasses(){
        ParseObject.registerSubclass(Question.class);
        ParseObject.registerSubclass(Answer.class);
    }

    private void checkNotInitialized() {
        if (isConnected) {
            throw new AlreadyInitializedException();
        }
    }

    public void storeQuestion(Question question) {
        checkConnection();
        try {
            question.save();
        } catch (ParseException exception) {
            throw new WicoParseException();
        }
    }

    public void storeAnswer(Answer answer){
        checkConnection();
        try {
            answer.save();
            updateAnswersForQuestion(answer.getQuestionId());
        } catch (ParseException exception) {
            throw new WicoParseException();
        }
    }

    private void updateAnswersForQuestion(String questionId) {
        Question question = getQuestion(questionId);
        question.addAnswer();
        try {
            question.save();
        } catch (ParseException e) {
            throw new WicoParseException();
        }
    }

    private Question getQuestion(String questionId){
        ParseQuery<Question> query = createQuestionQuery();
        Question question = null;
        try {
            question = query.get(questionId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return question;
    }

    public ArrayList<Question> getQuestions() {
        checkConnection();
        ParseQuery<Question> query = createQuestionsQuery();
        ArrayList<Question> questions = new ArrayList<>();
        try {
            questions.addAll(query.find());
        } catch (ParseException e) {
            throw new WicoParseException();
        }
        return questions;
    }

    public ArrayList<Answer> getAnswersForQuestion(String questionId) {
        checkConnection();
        ParseQuery<Answer> query = createAnswerQuery(questionId);
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            answers.addAll(query.find());
        } catch (ParseException e) {
            throw new WicoParseException();
        }
        return answers;
    }

    private ParseQuery<Answer> createAnswerQuery(String questionId) {
        ParseQuery<Answer> query = ParseQuery.getQuery(Answer.class);
        query.whereEqualTo("parentQuestionId", questionId);
        query.orderByDescending("createdAt");
        return query;
    }


    private ParseQuery<Question> createQuestionsQuery() {
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereExists("content");
        query.orderByDescending("createdAt");
        return query;
    }

    private ParseQuery<Question> createQuestionQuery(){
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        return query;
    }

    private void checkConnection() {
        if (!isConnected) {
            throw new DisconectedFromParseException();
        }
    }
}
