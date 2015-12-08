package com.wico.network;

import android.content.Context;

import com.parse.ParseObject;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;
import com.wico.network.interfaces.ParseInitializer;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;

import java.util.ArrayList;

import javax.inject.Inject;

public class ParseConnector {

    // The following fields cannot be private.
    // Dagger does not support private fields injection
    @Inject
    public static ParseInitializer initializer;
    @Inject
    public static ParseObjectRetriever retriever;
    @Inject
    public static ParseObjectStorer storer;

    private static boolean initialized = false;

    /**Initializes connection to parse.
     * This method must be run in the first activity
     * of the application.
     */
    public void initialize(Context context){
        if(!initialized){
            initializer.initialize(context);
            initialized = true;
        }
    }

    public void store(ParseObject object){
        storer.store(object);
    }

    public void storeAnswer(Answer answer) {
        storer.store(answer);
    }

    public ArrayList<Question> getQuestions(String wicoPageId) {
        return retriever.getQuestions(wicoPageId);
    }


    public ArrayList<Answer> getAnswersForQuestion(String questionId) {
        return retriever.getAnswersForQuestion(questionId);
    }

    public WicoPage loadPage(String pageId) {
        return retriever.getPage(pageId);
    }
}
