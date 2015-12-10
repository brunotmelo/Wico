package com.wico.network;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.wico.exceptions.WicoParseException;
import com.wico.network.interfaces.ParseObjectStorer;

public class OfflineParseObjectStorer implements ParseObjectStorer {


    @Override
    public void store(ParseObject object) {
        try {
            object.pin();
        } catch (ParseException e) {
            throw new WicoParseException();
        }
    }

}
