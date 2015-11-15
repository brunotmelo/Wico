package com.wico.util;

import com.wico.datatypes.Question;

import java.io.Serializable;

public class SerializableQuestion implements Serializable {

    private String id;
    private String title;
    private String content;



    public SerializableQuestion(Question question){
        content = question.getContent();

    }

    public Question getQuestion(){
       return null;
    }




}
