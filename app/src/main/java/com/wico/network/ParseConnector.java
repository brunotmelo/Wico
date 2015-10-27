package com.wico.network;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.wico.datatypes.Question;
import com.wico.exceptions.WicoParseException;

import java.util.ArrayList;

public class ParseConnector {

    public void storeQuestion(Question question){
        try {
            question.save();
        } catch (ParseException exception) {
            throw new WicoParseException();
        }
    }





}
