package com.wico.ui.fragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wico.R;
import com.wico.ui.CreatePageActivity;

public class ChildrenPagesFragment extends ActivityFabOverriderFragment{



    private static final String PARENT_PAGE_ID = "param1";
    private String parentPageId;

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
        View rootView = inflater.inflate(R.layout.fragment_children_pages, container, false);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void overrideFab(FloatingActionButton fab){
        fab.setOnClickListener(fabCallBack);
        Drawable editIcon = getResources().getDrawable(R.drawable.ic_note_add_white_24dp);
        fab.setImageDrawable(editIcon);
    }

}
