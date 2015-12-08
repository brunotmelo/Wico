package com.wico.network.offline;

import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;
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
        return null;
    }

    @Override
    public ArrayList<Answer> getAnswersForQuestion(String questionId) {
        return null;
    }
}
