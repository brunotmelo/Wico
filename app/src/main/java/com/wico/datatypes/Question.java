package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Question")
public final class Question extends ParseObject{

    public static final class Builder {
        private String title;
        private String content;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }

    public Question(){
        //this method required to use it as a parseObject
    }
    private Question(Builder builder) {
        put("title", builder.title);
        put("content",builder.content);
    }

    public String getTitle() {
        return getString("title");
    }

    public String getContent() {
        return getString("content");
    }

    public int getNumOfAwnsers(){
        return 5;
    }


}