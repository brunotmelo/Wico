package com.wico.network;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;
import com.wico.exceptions.WicoParseException;
import com.wico.network.interfaces.ParseObjectRetriever;

import java.util.ArrayList;

public class OfflineParseObjectRetriever implements ParseObjectRetriever {


    @Override
    public WicoPage getPage(String id) {
        return new WicoPage.Builder().title("testing page").content("markdown").path("/").build();
    }

    @Override
    public Question getQuestion(String questionId) {
        return null;
    }

    @Override
    public ArrayList<Question> getQuestions(String parentPageId) {
        ParseQuery<Question> query = createQuestionsQuery(parentPageId);
        ArrayList<Question> questions = new ArrayList<>();
        try {
            questions.addAll(query.find());
        } catch (ParseException e) {
            throw new WicoParseException();
        }
        return questions;
    }

    @Override
    public ArrayList<Answer> getAnswersForQuestion(String questionId) {
        return null;
    }

    private ParseQuery<Question> createQuestionsQuery(String pagePath) {
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereEqualTo("parentPath", pagePath);
        query.fromLocalDatastore();
        query.orderByDescending("createdAt");
        return query;
    }

    private ParseQuery<Question> createQuestionQuery(String questionId){
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.fromLocalDatastore();
        query.whereEqualTo("objectId", questionId);

        return query;
    }



}
