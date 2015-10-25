package com.wico.ui.threads;

import com.wico.datatypes.Question;
import com.wico.network.ParseConnector;
import com.wico.ui.CreateQuestionActivity;

public class QuestionSaver extends Thread{
    private CreateQuestionActivity savingQuestionActivity;
    private boolean saved;
    private Question question;

    public QuestionSaver(CreateQuestionActivity caller, Question question){
        savingQuestionActivity = caller;
        this.question = question;

        saved = false;
    }

    public void run(){
        ParseConnector parse = new ParseConnector();
        parse.storeQuestion(question);
        savingQuestionActivity.onQuestionSaved();
    }

}
