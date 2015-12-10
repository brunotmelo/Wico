package com.wico.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.wico.R;
import com.wico.datatypes.WicoPage;
import com.wico.network.ParseConnector;
import com.wico.ui.PageActivity;
import com.wico.ui.adapters.ChildrenListAdapter;
import com.wico.ui.threads.NetworkChecker;

import java.util.ArrayList;

public class ChildrenPagesFragment extends Fragment implements AbsListView.OnItemClickListener {

    private AbsListView mListView;
    private ListAdapter childrenAdapter;
    private static final String WICO_PAGE_ID = "param2";
    private String wicoPageId;
    private boolean loading = false;



    public static ChildrenPagesFragment newInstance(String wicoPageId) {
        ChildrenPagesFragment fragment = new ChildrenPagesFragment();
        Bundle args = new Bundle();
        args.putString(WICO_PAGE_ID, wicoPageId);
        fragment.setArguments(args);
        return fragment;
    }

    public ChildrenPagesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_children_pages, container, false);
        startUiVariables(view);
        return view;
    }


    private void startUiVariables(View view){
        mListView = (AbsListView) view.findViewById(R.id.fcp_listView);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
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
        ArrayList<WicoPage> childrenList = parseConnector.getWicoPages(wicoPageId);
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


}
