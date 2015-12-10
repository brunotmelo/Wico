
package com.wico.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wico.R;
import com.wico.datatypes.WicoPage;

import java.util.List;


public class ChildrenListAdapter extends ArrayAdapter<WicoPage> {

    public ChildrenListAdapter(Context context, int resource, List<WicoPage> wicoPagesList) {
        super(context, resource, wicoPagesList);
    }

    private View view;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        inflateView();
        addWicoPageToCell(position);
        return view;
    }

    private void inflateView(){
        if (isViewEmpty()) {
            LayoutInflater cellView;
            cellView = LayoutInflater.from(getContext());
            view = cellView.inflate(R.layout.children_cell, null);
        }
    }

    private boolean isViewEmpty(){
        return view == null;
    }

    private void addWicoPageToCell(int position){
        WicoPage wicoPage = getItem(position);
        setUi(wicoPage);
    }

    private void setUi(WicoPage wicoPage){
        TextView title = (TextView) view.findViewById(R.id.childrenPagesCellTitleText);
        title.setText(wicoPage.getTitle());
    }

    /*fix this, dunno if needed to display content using clickable shit, if so, changes definitely required

    private void setAnswersText(int numAnswers){
        TextView answersNumber = (TextView) view.findViewById(R.id.questionCellAnswersText);
        String defaultAnswerText = view.getContext().getString(R.string.num_answers_text);
        String answerText = numAnswers + " " + defaultAnswerText;
        answersNumber.setOnClickListener();
        answersNumber.setText(answerText);
    }*/
}


