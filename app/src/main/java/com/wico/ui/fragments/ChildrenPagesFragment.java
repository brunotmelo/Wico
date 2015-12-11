package com.wico.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.wico.R;
import com.wico.datatypes.WicoPage;
import com.wico.network.ParseConnector;
import com.wico.ui.CreatePageActivity;
import com.wico.ui.PageActivity;
import com.wico.ui.adapters.ChildrenListAdapter;
import com.wico.ui.threads.NetworkChecker;

import java.util.ArrayList;

public class ChildrenPagesFragment extends ActivityFabOverriderFragment implements AbsListView.OnItemClickListener{

    private AbsListView mListView;
    private ListAdapter childrenAdapter;
    private static final String PARENT_PAGE_ID = "param2";
    private String parentPageId;
    private boolean loading = false;


    private View.OnClickListener fabCallBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CreatePageActivity.class);
                intent.putExtra("parentId",parentPageId);
                startActivity(intent);
        }
    };


    public static ChildrenPagesFragment newInstance(String parentPageId) {
        ChildrenPagesFragment fragment = new ChildrenPagesFragment();
        Bundle args = new Bundle();
        args.putString(PARENT_PAGE_ID, parentPageId);
        fragment.setArguments(args);
        return fragment;
    }

    public ChildrenPagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.parentPageId = getArguments().getString(PARENT_PAGE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_children_pages, container, false);
        startUiVariables(view);
        return view;
    }


    private void startUiVariables(View view){
        mListView = (AbsListView) view.findViewById(R.id.fcp_listView);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(!loading){
            waitInternetAndLoadContent();
            loading = true;
        }
    }
    
    public void waitInternetAndLoadContent() {
        NetworkChecker checker = new NetworkChecker(getActivity());
        checker.setNetworkCheckerListener(new NetworkChecker.NetworkCheckerListener() {
            @Override
            public void onConnected() {
                connectedToInternet();
            }
        });
        checker.start();
    }

    //callback
    private void connectedToInternet() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadWicoPages();
            }
        });
        loading = false;
    }

    public void loadWicoPages() {
        ParseConnector parseConnector = new ParseConnector();
        ArrayList<WicoPage> childrenList = parseConnector.getChildrenPages(parentPageId);
        childrenAdapter = new ChildrenListAdapter(getActivity(), android.R.id.text1, childrenList);
        mListView.setAdapter(childrenAdapter);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PageActivity.class);
        WicoPage wicoPage = (WicoPage) childrenAdapter.getItem(position);
        intent.putExtra("wicoPageId", wicoPage.getObjectId());
        startActivity(intent);
    }

    @Override
    public void overrideFab(FloatingActionButton fab){
        fab.setOnClickListener(fabCallBack);
        Drawable editIcon = getResources().getDrawable(R.drawable.ic_note_add_white_24dp);
        fab.setImageDrawable(editIcon);
    }


}
