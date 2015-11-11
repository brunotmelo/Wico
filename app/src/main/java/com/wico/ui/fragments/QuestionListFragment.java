package com.wico.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.wico.R;
import com.wico.datatypes.Question;
import com.wico.network.ParseConnector;
import com.wico.ui.CreateQuestionActivity;
import com.wico.ui.QuestionAndAnswersActivity;

import java.util.ArrayList;

public class QuestionListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter questionAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestionListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void loadQuestions() {
        ParseConnector parseConnector = new ParseConnector();
        ArrayList<Question> questionList = parseConnector.getQuestions();
        questionAdapter = new QuestionListAdapter(getActivity(), android.R.id.text1, questionList);
        mListView.setAdapter(questionAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionlist, container, false);
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(questionAdapter);
        mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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
        startActivity(intent);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
