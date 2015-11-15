package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.wico.network.ParseConnector;

import java.io.Serializable;
import java.util.ArrayList;

@ParseClassName("Question")
public final class Question extends ParseObject{

    public static final class Builder{
        private String title;
        private String content;

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }

    private ArrayList<Answer> answers;

    // The method below (public Question()) ,
    // although empty, is mandatory to allow question object to be used as a parse object.
    public Question(){
    }

    private Question(Builder builder){
        put("title", builder.title);
        put("content",builder.content);

    }

    public void populateArrayTest() {
        answers = new ArrayList<>();
        Answer ans = new Answer.Builder().content("blabla1").build();
        Answer ans2 = new Answer.Builder().content("blabla2").build();
        answers.add(ans);
        answers.add(ans2);
        put("answers", answers);
        ParseConnector connector = new ParseConnector();
        //connector.updateQuestion(this);
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
        put("answers",answers);
    }

    public ArrayList<Answer> getAnswers(){
        return answers;
    }

    public String getTitle(){
        return getString("title");
    }

    public String getContent(){
        return getString("content");
    }

    public int getNumberOfAwnsers(){
        return answers.size();
    }


}