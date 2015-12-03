package com.wico.datatypes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("WicoPage")
public class WicoPage extends ParseObject {

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
        put("parentId",builder.parentId);
    }

    public String getTitle(){
        return getString("title");
    }

    public String getContent(){
        return getString("content");
    }
}
