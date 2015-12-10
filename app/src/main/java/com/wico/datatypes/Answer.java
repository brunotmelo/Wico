package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Answer")
public class Answer extends ParseObject {

    public static final class Builder{
        private String content;
        private String parentQuestionId;
        private String author;

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder parentQuestionId(String parentQuestionId){
            this.parentQuestionId = parentQuestionId;
            return this;
        }

        public Builder author(String author){
            this.author = author;
            return this;
        }

        public Answer build() {
            return new Answer(this);
        }
    }

    public Answer(){
        //Mandatory empty constructor for parseObjects.
    }

    private Answer(Builder builder){
        put("content",builder.content);
        put("parentQuestionId",builder.parentQuestionId);
        put("author",builder.author);
    }

    public String getContent() {
        return getString("content");
    }

    public String getQuestionId(){
        return getString("parentQuestionId");
    }

    public String getAuthor(){
        return getString("author");
    }

}
