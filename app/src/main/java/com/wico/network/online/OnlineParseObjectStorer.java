package com.wico.network.online;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.exceptions.WicoParseException;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;

public class OnlineParseObjectStorer implements ParseObjectStorer {


    @Override
    public void store(ParseObject object){
        try {
            save(object);
        } catch (ParseException exception){
            throw new WicoParseException();
        }
    }

    private void save(ParseObject object) throws ParseException{
        object.save();
        if(object instanceof Answer){
            Answer answer = (Answer)object;
            updateAnswersForQuestion(answer.getQuestionId());
        }
    }

    private void updateAnswersForQuestion(String questionId) {
        ParseObjectRetriever retriever = new OnlineParseObjectRetriever();
        Question question = retriever.getQuestion(questionId);
        question.addAnswer();
        try {
            question.save();
        } catch (ParseException e) {
            throw new WicoParseException();
        }
    }
}
