package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("WicoPage")
public class WicoPage extends ParseObject {

    public static final class Builder{
        private String path;
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

        public Builder path(String path){
            this.path = path;
            return this;
        }

        public WicoPage build() {
            return new WicoPage(this);
        }
    }

    // The constructor, although empty,
    // is mandatory to allow an object to be used as a parse object.
    public WicoPage(){
    }

    private WicoPage(Builder builder){
        put("title", builder.title);
        put("content",builder.content);
        put("parent",builder.path);
    }





}
