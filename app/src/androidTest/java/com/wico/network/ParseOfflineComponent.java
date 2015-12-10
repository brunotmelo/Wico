package com.wico.network;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ParseConnectorOfflineModule.class)
public interface ParseOfflineComponent extends ParseComponent{

    void inject(ParseConnector connector);

}
