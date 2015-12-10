package com.wico;

import android.app.Application;
import android.content.Context;

import com.wico.network.DaggerParseComponent;
import com.wico.network.ParseComponent;
import com.wico.network.ParseConnectorModule;

public class WicoApp extends Application{

    private static Context context;
    private static ParseComponent component;


    @Override
    public void onCreate(){
        super.onCreate();
        WicoApp.context = getApplicationContext();
        buildDependencies();
    }

    private void buildDependencies(){
        component = DaggerParseComponent.builder()
                .parseConnectorModule(new ParseConnectorModule())
                .build();
    }

    public ParseComponent getComponent(){
        return component;
    }

    public static Context getAppContext() {
        return WicoApp.context;
    }


}
