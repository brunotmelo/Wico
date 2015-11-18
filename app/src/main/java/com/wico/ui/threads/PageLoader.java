package com.wico.ui.threads;

import com.wico.datatypes.WicoPage;
import com.wico.network.ParseConnector;
import com.wico.ui.threads.listeners.PageLoadedListener;

public class PageLoader extends Thread {

    private String pagePath;
    private PageLoadedListener listener;

    public PageLoader(String pagePath, PageLoadedListener listener){
        this.pagePath = pagePath;
        this.listener = listener;
    }

    @Override
    public void run(){
        ParseConnector connector = new ParseConnector();
        WicoPage page = connector.loadPage(pagePath);
        listener.onPageLoaded(page);
    }
}
