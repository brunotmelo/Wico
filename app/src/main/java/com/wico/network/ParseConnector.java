package com.wico.network;

import android.content.Context;

import com.parse.ParseObject;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;
import com.wico.network.interfaces.ParseInitializer;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;
import com.wico.network.offline.DaggerParseOfflineComponent;
import com.wico.network.offline.ParseOfflineComponent;
import com.wico.network.online.DaggerParseComponent;
import com.wico.network.online.ParseComponent;

import java.util.ArrayList;

import javax.inject.Inject;

public class ParseConnector {

    // The following fields cannot be private.
    // Dagger does not support private fields injection
    @Inject
    static ParseInitializer initializer;
    @Inject
    static ParseObjectRetriever retriever;
    @Inject
    static ParseObjectStorer storer;

    private static boolean initialized = false;
    private static boolean testing = false;


    public void setForTesting(){
        testing = true;
    }

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
        //if testing, the components will be provided by the test cases
        if(testing){
            ParseComponent component = DaggerParseComponent.builder().build();
            initializer = component.provideInitializer();
            retriever = component.provideParseRetriever();
            storer = component.provideParseStorer();
        }else{
            ParseOfflineComponent offlineComponent = DaggerParseOfflineComponent.builder().build();
            initializer = offlineComponent.provideInitializer();
            retriever = offlineComponent.provideParseRetriever();
            storer = offlineComponent.provideParseStorer();
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
