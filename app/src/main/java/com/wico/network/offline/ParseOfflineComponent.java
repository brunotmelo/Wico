package com.wico.network.offline;


import com.wico.network.interfaces.ParseInitializer;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ParseConnectorOfflineModule.class)
public interface ParseOfflineComponent {

    ParseInitializer provideInitializer();

    ParseObjectRetriever provideParseRetriever();

    ParseObjectStorer provideParseStorer();

}
