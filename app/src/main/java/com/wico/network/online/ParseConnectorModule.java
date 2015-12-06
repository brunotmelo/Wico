package com.wico.network.online;

import com.wico.network.online.OnlineParseInitializer;
import com.wico.network.online.OnlineParseObjectRetriever;
import com.wico.network.online.OnlineParseObjectStorer;
import com.wico.network.interfaces.ParseInitializer;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;

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

