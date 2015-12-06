package com.wico.util;

import android.support.test.espresso.core.deps.dagger.Component;

import com.wico.network.interfaces.ParseInitializer;
import com.wico.network.interfaces.ParseObjectRetriever;
import com.wico.network.interfaces.ParseObjectStorer;

import javax.inject.Singleton;

@Singleton
@Component(modules = ParseConnectorTestModule.class)
public interface ParseTestComponent {

    ParseInitializer provideInitializer();

    ParseObjectRetriever provideParseRetriever();

    ParseObjectStorer provideParseStorer();

}
