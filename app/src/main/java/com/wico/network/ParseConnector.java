package com.wico.network;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.wico.WicoApp;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;
import com.wico.network.interfaces.ParseInitializer;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;
import com.wico.exceptions.DisconectedFromParseException;
import com.wico.exceptions.WicoParseException;

import java.util.ArrayList;

import javax.inject.Inject;

public class ParseConnector {

    // The following fields cannot be private.
    // Dagger does not support private fields injection
    @Inject
    ParseInitializer initializer;
    @Inject
    ParseObjectRetriever retriever;
    @Inject
    ParseObjectStorer storer;

    private static boolean initialized = false;

    public ParseConnector(){
        WicoApp app = (WicoApp)WicoApp.getAppContext();
        app.getComponent().inject(this);
    }

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
