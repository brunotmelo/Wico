package com.wico.ui.threads;

import com.wico.datatypes.Answer;
import com.wico.network.ParseConnector;
import com.wico.ui.threads.listeners.AnswerSavedListener;

public class AnswerSaver extends Thread{

    private Answer answer;
    private AnswerSavedListener listener;

    public AnswerSaver(Answer answer, AnswerSavedListener listener){
        this.answer = answer;
        this.listener = listener;
    }

    @Override
    public void run(){
        ParseConnector parse = new ParseConnector();
        parse.storeAnswer(answer);
        listener.onAnswerSaved();
    }

}
