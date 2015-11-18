package com.wico.ui.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wico.R;
import com.wico.datatypes.Question;

import java.util.List;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    private View view;

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
        setUi(question);
    }

    private void setUi(Question question){
        TextView title = (TextView) view.findViewById(R.id.questionCellTitleText);
        TextView content = (TextView) view.findViewById(R.id.questionCellContentText);
        title.setText(question.getTitle());
        content.setText(question.getContent());
        setAnswersText(question.getNumberOfAnswers());
    }

    private void setAnswersText(int numAnswers){
        TextView answersNumber = (TextView) view.findViewById(R.id.questionCellAnswersText);
        String defaultAnswerText = view.getContext().getString(R.string.num_answers_text);
        String answerText = numAnswers + " " + defaultAnswerText;
        answersNumber.setText(answerText);
    }
}