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

    public QuestionListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public QuestionListAdapter(Context context, int resource, List<Question> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.question_cell, null);
        }

        Question q = getItem(position);

        if (q != null) {
            TextView title = (TextView) v.findViewById(R.id.questionCellTitleText);
            TextView content = (TextView) v.findViewById(R.id.questionCellContentText);
            Button answersButton = (Button) v.findViewById(R.id.questionCellAnswersButton);

            if (title != null) {
                title.setText(q.getTitle());
            }

            if (content != null) {content.setText(q.getContent());
            }

            if (answersButton != null) {
                answersButton.setText(q.getNumOfAwnsers()+ " answers");
            }
        }

        return v;
    }

}
