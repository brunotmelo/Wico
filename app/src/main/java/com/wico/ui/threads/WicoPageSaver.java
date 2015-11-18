package com.wico.ui.threads;

import com.wico.datatypes.WicoPage;
import com.wico.network.ParseConnector;
import com.wico.ui.threads.listeners.PageSavedListener;

public class WicoPageSaver extends Thread{
    private PageSavedListener listener;
    private WicoPage page;

    public WicoPageSaver(WicoPage page, PageSavedListener listener){
        this.page = page;
        this.listener = listener;
    }

    @Override
    public void run(){
        ParseConnector connector = new ParseConnector();
        connector.storePage(page);
        listener.onPageSaved();
    }
}
