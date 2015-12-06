package com.wico.util;

import com.wico.network.ParseInitializer;
import com.wico.network.ParseObjectRetriever;
import com.wico.network.ParseObjectStorer;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ParseConnectorModule.class)
public interface ParseComponent {

    ParseInitializer provideInitializer();

    ParseObjectRetriever provideParseRetriever();

    ParseObjectStorer provideParseStorer();

}
