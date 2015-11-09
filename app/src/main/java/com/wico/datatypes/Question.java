package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wico.exceptions.WicoParseException;

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
        answers = new ArrayList<>();
        put("answers",answers);

    }

    public void addAnswer(Answer answer){
        answers.add(answer);
        put("answers",answers);
    }

    public String getTitle(){
        return getString("title");
    }

    public String getContent(){
        return getString("content");
    }

    public String getNumberOfAwnsers(){
        return getString("numberOfAnswers");
    }


}