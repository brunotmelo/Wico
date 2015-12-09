package com.wico.util;

import android.support.test.espresso.IdlingResource;
import android.widget.EditText;

public class WicoIdlingResource implements IdlingResource {

    private EditText edit;
    private ResourceCallback callback;

    public WicoIdlingResource(EditText edit){
        this.edit =edit;
    }

    @Override
    public String getName() {
        return "textView locked";
    }

    @Override
    public boolean isIdleNow() {
        if(edit.isEnabled()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }
}
