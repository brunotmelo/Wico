package com.wico.network;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;
import com.wico.datatypes.Answer;
import com.wico.datatypes.Question;
import com.wico.datatypes.WicoPage;
import com.wico.network.interfaces.ParseInitializer;

public class OfflineParseInitializer implements ParseInitializer {
    @Override
    public void initialize(Context context) {
        Parse.enableLocalDatastore(context);
        registerParseSubclasses();
    }

    private void registerParseSubclasses(){
        ParseObject.registerSubclass(Question.class);
        ParseObject.registerSubclass(Answer.class);
        ParseObject.registerSubclass(WicoPage.class);
    }
}
