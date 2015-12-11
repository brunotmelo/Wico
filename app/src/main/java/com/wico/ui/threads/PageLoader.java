package com.wico.ui.threads;

import com.wico.datatypes.WicoPage;
import com.wico.network.ParseConnector;
import com.wico.ui.threads.listeners.PageLoadedListener;

public class PageLoader extends Thread {

    private String pageId;
    private PageLoadedListener listener;

    public PageLoader(String pageId, PageLoadedListener listener){
        this.pageId = pageId;
        this.listener = listener;
    }

    @Override
    public void run(){
        ParseConnector connector = new ParseConnector();
        WicoPage page = connector.loadPage(pageId);
        listener.onPageLoaded(page);
    }
}
