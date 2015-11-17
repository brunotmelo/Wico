package com.wico.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wico.R;

import org.w3c.dom.Text;


public class PageContentViewFragment extends Fragment {

    private static final String WICO_PAGE_ID = "param1";

    // TODO: Rename and change types of parameters
    private String wicoPageId;

    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page_view, container, false);
        TextView txt = (TextView)view.findViewById(R.id.wp_testText);
        txt.setText(wicoPageId);
        return view;
    }
}
