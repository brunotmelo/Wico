package com.wico;

import android.app.Application;

import com.wico.network.ParseConnector;
import com.wico.network.DaggerParseComponent;
import com.wico.network.ParseComponent;

public class WicoApp extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        injectDependencies();
    }

    private void injectDependencies(){
        ParseComponent component = DaggerParseComponent.builder().build();
        ParseConnector.initializer = component.provideInitializer();
        ParseConnector.retriever = component.provideParseRetriever();
        ParseConnector.storer = component.provideParseStorer();
    }
}
