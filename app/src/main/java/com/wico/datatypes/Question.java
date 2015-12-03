package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Question")
public final class Question extends ParseObject{

    public static final class Builder{
        private String title;
        private String content;
        private String parentId;

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder parentId(String parentId){
            this.parentId = parentId;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }


    // The method below (public Question()) ,
    // although empty, is mandatory to allow question object to be used as a parse object.
    public Question(){
    }

    private Question(Builder builder){
        put("title", builder.title);
        put("content",builder.content);
        put("parentId",builder.parentId);
        put("numOfAnswers",0);
    }

    public void addAnswer(){
        int numOfAnswers = getInt("numOfAnswers");
        numOfAnswers += 1;
        put("numOfAnswers", numOfAnswers);
    }

    public void removeAnswer(){
        int numOfAnswers = getInt("numOfAnswers");
        numOfAnswers -= 1;
        put("numOfAnswers",numOfAnswers);
    }

    public String getTitle(){
        return getString("title");
    }

    public String getContent(){
        return getString("content");
    }

    public int getNumberOfAnswers(){
        return getInt("numOfAnswers");
    }


}