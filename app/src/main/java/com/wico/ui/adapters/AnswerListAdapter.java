package com.wico.ui.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wico.R;
import com.wico.datatypes.Answer;

import java.util.List;

public class AnswerListAdapter extends ArrayAdapter<Answer> {

    private View view;

    public AnswerListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AnswerListAdapter(Context context, int resource, List<Answer> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        inflateView();
        addAnswerToCell(position);
        //view.setOnClickListener(cellListener);
        return view;
    }

    private View.OnClickListener cellListener= new View.OnClickListener(){
        public void onClick(View v) {
            //get question from position
            //Toast.makeText(getContext(), "Unable to save question", Toast.LENGTH_LONG).show();
        }
    };

    private void inflateView(){
        if (isViewEmpty()) {
            LayoutInflater cellView;
            cellView = LayoutInflater.from(getContext());
            view = cellView.inflate(R.layout.answer_cell, null);
        }
    }

    private boolean isViewEmpty(){
        return view == null;
    }

    private void addAnswerToCell(int position){
        Answer answer = getItem(position);
        if (answer != null) {
            TextView content = (TextView) view.findViewById(R.id.ac_content);
            TextView author = (TextView) view.findViewById(R.id.ac_author);
            if (content != null) {
                content.setText(answer.getContent());
            }
            if (author != null) {
                author.setText(answer.getAuthor());
            }
        }
    }
}