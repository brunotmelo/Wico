package com.wico.network;

import com.parse.ParseObject;
import com.wico.network.interfaces.ParseObjectStorer;

public class OfflineParseObjectStorer implements ParseObjectStorer {

    private ParseObject storedObject;


    @Override
    public void store(ParseObject object) {
        storedObject = object;
        System.out.println("object saved");
    }

    public ParseObject getStoredObject(){
        return storedObject;
    }
}
