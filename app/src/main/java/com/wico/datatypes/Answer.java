package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Answer")
public class Answer extends ParseObject {

    public static final class Builder{
        private String content;
        private String parentQuestionId;

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder parentQuestionId(String parentQuestionId){
            this.parentQuestionId = parentQuestionId;
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
    }

    public String getContent() {
        return getString("content");
    }

    public String getAuthor(){
        //TODO: Next iteration will have support for users
        //this is just a placeholder
        return "Wico";
    }
}
