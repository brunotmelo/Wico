package com.wico.network;


import com.wico.network.interfaces.ParseInitializer;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ParseConnectorOfflineModule {
    @Provides @Singleton
    public ParseInitializer provideInitializer() {
        return new OfflineParseInitializer();
    }

    @Provides @Singleton
    public ParseObjectRetriever provideParseRetriever(){
        return new OfflineParseObjectRetriever();
    }

    @Provides @Singleton
    public ParseObjectStorer provideParseStorer(){
        return new OfflineParseObjectStorer();
    }
}
