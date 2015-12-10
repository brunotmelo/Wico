package com.wico.network;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ParseConnectorModule.class)
public interface ParseComponent {
    void inject(ParseConnector connector);
}
