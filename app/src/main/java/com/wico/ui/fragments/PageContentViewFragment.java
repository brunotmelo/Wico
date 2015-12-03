package com.wico.ui.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wico.R;
import com.wico.datatypes.WicoPage;
import com.wico.ui.threads.PageLoader;
import com.wico.ui.threads.listeners.PageLoadedListener;

import in.uncod.android.bypass.Bypass;


public class PageContentViewFragment extends ActivityFabOverriderFragment {

    private static final String WICO_PAGE_ID = "param1";

    private String wicoPageId;
    private WicoPage page;
    private boolean loading = false;

    private TextView pageContent;

    private View.OnClickListener fabCallBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            enableEdit();
        }
    };

    private PageLoadedListener loadedListener = new PageLoadedListener(){
        @Override
        public void onPageLoaded(WicoPage page){
            pageLoaded(page);
        }
    };
    private Thread.UncaughtExceptionHandler notLoadedListener = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable e) {
            unableToLoadPage();
        }
    };


    public static PageContentViewFragment newInstance(String wicoPageId) {
        PageContentViewFragment fragment = new PageContentViewFragment();
        Bundle args = new Bundle();
        args.putString(WICO_PAGE_ID, wicoPageId);
        fragment.setArguments(args);
        return fragment;
    }

    public PageContentViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.wicoPageId = getArguments().getString(WICO_PAGE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_view, container, false);
        pageContent = (TextView)view.findViewById(R.id.pv_markdownText);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(!loading){
            loading = true;
            getWicoPage();
        }
    }

    private void getWicoPage(){
        PageLoader pageLoaderThread = new PageLoader(wicoPageId, loadedListener);
        pageLoaderThread.setUncaughtExceptionHandler(notLoadedListener);
        pageLoaderThread.start();
    }

    //callBack
    private void pageLoaded(WicoPage page){
        this.page = page;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadMarkDownText();
            }
        });
        loading = false;
    }

    private void loadMarkDownText(){
        Bypass bypass = new Bypass();
        CharSequence string = bypass.markdownToSpannable(page.getContent());
        pageContent.setText(string);
        pageContent.setMovementMethod(LinkMovementMethod.getInstance());
        pageContent.setHighlightColor(Color.GREEN);
    }

    //error callback
    private void unableToLoadPage(){
        Toast.makeText(getActivity(),"An error ocurred while loading the page", Toast.LENGTH_SHORT).show();
    }

    private void enableEdit(){
        pageContent.setText("basudbsadkaslkasd");
        //view.setVisibility(View.GONE);
        //EditText edit = (EditText)findViewById(R.id.pv_editText);
        //edit.setVisibility(View.VISIBLE);
    }

    @Override
    public void overrideFab(FloatingActionButton fab){
        fab.setOnClickListener(fabCallBack);
        Drawable editIcon = getResources().getDrawable(R.drawable.ic_mode_edit_white_24dp);
        fab.setImageDrawable(editIcon);
    }


}
