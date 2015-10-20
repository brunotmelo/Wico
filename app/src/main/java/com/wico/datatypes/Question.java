package com.wico.datatypes;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Question {

    String title;
    String content;
    ArrayList<Answer> awnsers;


    public Question(String title, String content) {
        this.title = title;
        this. content = content;
    }

    public void addAwnser(Answer answer){
        awnsers.add(answer);
    }

    public void removeAwnser(int index){
        awnsers.remove(index);
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