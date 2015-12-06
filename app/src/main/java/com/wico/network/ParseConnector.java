package com.wico.network;

import android.content.Context;

import com.parse.ParseObject;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;
import com.wico.util.DaggerParseComponent;
import com.wico.util.ParseComponent;

import java.util.ArrayList;

import javax.inject.Inject;

public class ParseConnector {
    @Inject
    static ParseInitializer initializer;
    @Inject
    static ParseObjectRetriever retriever;
    @Inject
    static ParseObjectStorer storer;

    private static boolean initialized = false;

    /**Initializes connection to parse.
     * This method must be run in the first activity
     * of the application.
     */
    public void initialize(Context context){
        if(!initialized){
            injectDependencies();
            initializer.initialize(context);
            initialized = true;
        }
    }

    private void injectDependencies(){
        ParseComponent component = DaggerParseComponent.builder().build();
        initializer = component.provideInitializer();
        retriever = component.provideParseRetriever();
        storer = component.provideParseStorer();
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
