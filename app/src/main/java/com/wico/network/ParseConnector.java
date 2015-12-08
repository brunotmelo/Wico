package com.wico.network;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wico.datatypes.Answer;
import com.wico.datatypes.WicoPage;
import com.wico.datatypes.Question;
import com.wico.exceptions.AlreadyInitializedException;
import com.wico.exceptions.DisconectedFromParseException;
import com.wico.exceptions.WicoParseException;

import java.util.ArrayList;

public class ParseConnector {

    private static boolean isConnected = false;

    public ParseConnector() {
    }

    /**Initializes connection to parse.
     * This method must be run in the first activity
     * of the application.
     */
    public void initialize(Context context) {
        if(!isConnected){
            Parse.enableLocalDatastore(context);
            registerParseSubclasses();
            Parse.initialize(context, "rvro91QbTePbPJKwAfB5TcMjoXzVH8ewSawqk7uk", "8W1XCtK31EAh9EXY5Fp7kbePKkT7eDO92DdxmHEr");
            isConnected = true;
        }
    }

    //The registration of parseObjects is mandatory
    //It has to be done before initializing parse
    private void registerParseSubclasses(){
        ParseObject.registerSubclass(Question.class);
        ParseObject.registerSubclass(Answer.class);
        ParseObject.registerSubclass(WicoPage.class);
    }

    public void storePage(WicoPage page){
        checkConnection();
        try {
            page.save();
        } catch (ParseException exception){
            throw new WicoParseException();
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
        ParseQuery<Question> query = createQuestionQuery(questionId);
        Question question = null;
        try {
            question = query.getFirst();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return question;
    }

    public WicoPage loadPage(String path){
        checkConnection();
        ParseQuery<WicoPage> query = createPageQuery(path);
        WicoPage page;
        try {
            page = query.getFirst();
        } catch (ParseException e){
            throw new WicoParseException();
        }
        return page;
    }

    public ArrayList<Question> getQuestions(String parentPageId) {
        checkConnection();
        ParseQuery<Question> query = createQuestionsQuery(parentPageId);
        ArrayList<Question> questions = new ArrayList<>();
        try {
            questions.addAll(query.find());
        } catch (ParseException e) {
            throw new WicoParseException();
        }
        return questions;
    }

    public ArrayList<Question> searchQuestions(String searchString){
        checkConnection();
        ParseQuery<Question> query = createSearchQuestionsQuery(searchString);
        ArrayList<Question> questions = new ArrayList<>();
        try {
            questions.addAll(query.find());
        } catch (ParseException e){
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

    private ParseQuery<WicoPage> createPageQuery(String pageId){
        ParseQuery<WicoPage> query = ParseQuery.getQuery(WicoPage.class);
        query.whereEqualTo("objectId", pageId);
        return query;
    }

    private ParseQuery<Question> createQuestionsQuery(String pageId) {
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereEqualTo("parentId", pageId);
        query.whereExists("content");
        query.orderByDescending("createdAt");
        return query;
    }

    private ParseQuery<Question> createQuestionQuery(String questionId){
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereEqualTo("objectId",questionId);
        return query;
    }

    private ParseQuery<Answer> createAnswerQuery(String questionId) {
        ParseQuery<Answer> query = ParseQuery.getQuery(Answer.class);
        query.whereEqualTo("parentQuestionId", questionId);
        query.orderByDescending("createdAt");
        return query;
    }

    private ParseQuery<Question> createSearchQuestionsQuery(String searchString){
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereContains("title", searchString);
        query.whereContains("content",searchString);
        return query;
    }

    private void checkConnection() {
        if (!isConnected) {
            throw new DisconectedFromParseException();
        }
    }
}
