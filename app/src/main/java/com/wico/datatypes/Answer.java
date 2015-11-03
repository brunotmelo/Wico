package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Answer")
public class Answer extends ParseObject {

    public static final class Builder{
        private String content;


        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Answer build() {
            return new Answer(this);
        }
    }

    public Answer(){

    }

    private Answer(Builder builder){
        put("content",builder.content);
    }
    public String getAnswerContent() {
        return getString("content");
    }
}
