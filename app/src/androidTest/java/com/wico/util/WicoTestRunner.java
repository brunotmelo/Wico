package com.wico.util;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.wico.TestWicoApp;

public class WicoTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        return super.newApplication(cl, TestWicoApp.class.getName(), context);
    }


}
