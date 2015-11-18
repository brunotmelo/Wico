package com.wico.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wico.R;
import com.wico.ui.CreatePageActivity;

public class ChildrenPagesFragment extends Fragment{

        public static ChildrenPagesFragment newInstance() {
        ChildrenPagesFragment fragment = new ChildrenPagesFragment();
        return fragment;
    }

    public ChildrenPagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_children_pages, container, false);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

}
