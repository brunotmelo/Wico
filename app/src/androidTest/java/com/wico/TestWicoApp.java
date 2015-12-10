package com.wico;

import com.wico.network.DaggerParseOfflineComponent;
import com.wico.network.ParseComponent;
import com.wico.network.ParseConnectorOfflineModule;
import com.wico.network.ParseOfflineComponent;

public class TestWicoApp extends WicoApp{

    private ParseOfflineComponent component;

    @Override public void onCreate() {
        super.onCreate();
        System.out.println("hey");
        component = DaggerParseOfflineComponent.builder()
                .parseConnectorOfflineModule(new ParseConnectorOfflineModule())
                .build();
    }

    @Override
    public ParseComponent getComponent() {
        return component;
    }

}
