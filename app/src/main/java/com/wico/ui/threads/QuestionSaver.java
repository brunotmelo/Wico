package com.wico.ui.threads;

import com.wico.datatypes.Question;
import com.wico.network.ParseConnector;
import com.wico.ui.CreateQuestionActivity;

public class QuestionSaver extends Thread{

    private CreateQuestionActivity savingQuestionActivity;
    private Question question;

    public QuestionSaver(CreateQuestionActivity caller, Question question){
        savingQuestionActivity = caller;
        this.question = question;
    }

    public void run(){
        ParseConnector parse = new ParseConnector();
        parse.store(question);
        savingQuestionActivity.onQuestionSaved();
    }
}
