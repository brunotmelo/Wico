package com.wico.datatypes;

public final class Question {

    private final String title, content;

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

    private Question(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
    }



    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getNumOfAwnsers(){
        return 5;
    }


}