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
import android.widget.TextView;

import com.wico.R;
import com.wico.datatypes.Question;
import com.wico.network.ParseConnector;
import com.wico.ui.QuestionAndAnswersActivity;
import com.wico.ui.adapters.QuestionListAdapter;
import com.wico.ui.threads.NetworkChecker;

import java.util.ArrayList;

public class QuestionListFragment extends Fragment implements AbsListView.OnItemClickListener {

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;
    private ListAdapter questionAdapter;

    private TextView connectText;

    private static final String WICO_PAGE_ID = "param1";
    private String wicoPageId;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestionListFragment() {
    }

    public static QuestionListFragment newInstance(String wicoPageId) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        args.putString(WICO_PAGE_ID, wicoPageId);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_questionlist, container, false);
        startUiVariables(view);
        return view;
    }

    private void startUiVariables(View view){
        connectText = (TextView) view.findViewById(R.id.connectmessage);
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        waitInternetAndLoadContent();
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
                connectText.setVisibility(View.GONE);
                loadQuestions();
            }
        });
    }

    public void loadQuestions() {
        ParseConnector parseConnector = new ParseConnector();
        ArrayList<Question> questionList = parseConnector.getQuestions(wicoPageId);
        questionAdapter = new QuestionListAdapter(getActivity(), android.R.id.text1, questionList);
        mListView.setAdapter(questionAdapter);
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
        Intent intent = new Intent(getActivity(), QuestionAndAnswersActivity.class);
        Question question = (Question)questionAdapter.getItem(position);
        intent.putExtra("questionId", question.getObjectId());
        intent.putExtra("title",question.getTitle());
        intent.putExtra("content",question.getContent());
        startActivity(intent);
    }

}
