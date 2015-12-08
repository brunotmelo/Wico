package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Question")
public final class Question extends ParseObject{

    public static final class Builder{
        private String title;
        private String content;
        private String parentPath;
        private String author;

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder parentPath(String parentPath){
            this.parentPath = parentPath;
            return this;
        }

        public Builder author(String author){
            this.author = author;
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
        put("parentPath",builder.parentPath);
        put("numOfAnswers",0);
        put("author", builder.author);
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

    public String getAuthor(){
        return getString("author");
    }

    public int getNumberOfAnswers(){
        return getInt("numOfAnswers");
    }


}