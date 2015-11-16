package com.wico.ui.threads;


import com.wico.network.ParseConnector;
import com.wico.ui.threads.listeners.QuestionsFoundListener;

public class QuestionSearcher extends Thread {

    private String searchString;
    private QuestionsFoundListener listener;

    public QuestionSearcher(String searchString, QuestionsFoundListener listener){
        this.searchString = searchString;
        this.listener = listener;
    }

    @Override
    public void run(){
        ParseConnector parse = new ParseConnector();
        parse.searchQuestions(searchString);
        listener.onQuestionsFound();
    }

}
