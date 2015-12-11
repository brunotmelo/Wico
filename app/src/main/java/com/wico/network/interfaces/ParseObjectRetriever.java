package com.wico.network.interfaces;

import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;

import java.util.ArrayList;

public interface ParseObjectRetriever {

    public WicoPage getPage(String id);

    public Question getQuestion(String questionId);

    public ArrayList<Question> getQuestions(String parentPageId);

    public ArrayList<Answer> getAnswersForQuestion(String questionId);

    public ArrayList<WicoPage> getChildrenPages(String parentPageId);
}
