package com.wico.util;

import com.wico.network.ParseConnector;

import com.wico.network.DaggerParseOfflineComponent;
import com.wico.network.ParseOfflineComponent;

public class DependencyInjector{

    public void inject() {
        ParseOfflineComponent offlineComponent = DaggerParseOfflineComponent.builder().build();
        ParseConnector.initializer = offlineComponent.provideInitializer();
        ParseConnector.retriever = offlineComponent.provideParseRetriever();
        ParseConnector.storer = offlineComponent.provideParseStorer();
    }
}
