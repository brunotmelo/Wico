package com.wico.util;

import com.wico.network.OnlineParseInitializer;
import com.wico.network.OnlineParseObjectRetriever;
import com.wico.network.OnlineParseObjectStorer;
import com.wico.network.ParseInitializer;
import com.wico.network.ParseObjectRetriever;
import com.wico.network.ParseObjectStorer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ParseConnectorModule {

    @Provides @Singleton
    public ParseInitializer provideInitializer() {
        return new OnlineParseInitializer();
    }

    @Provides @Singleton
    public ParseObjectRetriever provideParseRetriever(){
        return new OnlineParseObjectRetriever();
    }

    @Provides @Singleton
    public ParseObjectStorer provideParseStorer(){
        return new OnlineParseObjectStorer();
    }
}

