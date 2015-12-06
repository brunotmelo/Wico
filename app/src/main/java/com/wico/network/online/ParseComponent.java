package com.wico.network.online;

import com.wico.network.interfaces.ParseInitializer;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ParseConnectorModule.class)
public interface ParseComponent {

    ParseInitializer provideInitializer();

    ParseObjectRetriever provideParseRetriever();

    ParseObjectStorer provideParseStorer();

}
