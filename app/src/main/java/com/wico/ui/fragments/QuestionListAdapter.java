package com.wico.ui.fragments;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wico.R;
import com.wico.datatypes.Question;

import java.util.List;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    private View view;

    public QuestionListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public QuestionListAdapter(Context context, int resource, List<Question> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        inflateView();
        addQuestionToCell(position);
        return view;
    }

    private void inflateView(){
        if (isViewEmpty()) {
            LayoutInflater cellView;
            cellView = LayoutInflater.from(getContext());
            view = cellView.inflate(R.layout.question_cell, null);
        }
    }

    private boolean isViewEmpty(){
        return view == null;
    }

    private void addQuestionToCell(int position){
        Question question = getItem(position);
        if (question != null) {
            TextView title = (TextView) view.findViewById(R.id.questionCellTitleText);
            TextView content = (TextView) view.findViewById(R.id.questionCellContentText);
            Button answersButton = (Button) view.findViewById(R.id.questionCellAnswersButton);
            if (title != null) {
                title.setText(question.getTitle());
            }
            if (content != null) {
                content.setText(question.getContent());
            }
            if (answersButton != null) {
                answersButton.setText(R.string.button_answers_text);
            }
        }
    }

}