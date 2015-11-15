package com.wico.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.wico.R;
import com.wico.datatypes.Answer;
import com.wico.network.ParseConnector;
import com.wico.ui.adapters.AnswerListAdapter;

import java.util.ArrayList;

public class QuestionAndAnswersActivity extends AppCompatActivity {

    private AbsListView answersListView;
    private ListAdapter answersAdapter;

    private FloatingActionButton sendAnswerButton;

    private String questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_answers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        sendAnswerButton = (FloatingActionButton) findViewById(R.id.qa_sendAnswerButton);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        questionId = getIntent().getStringExtra("questionId");

        setQuestionContents();
        setButtonClickListener();
        getAnswersAndAttach();
        //attachAnswerList();
    }

    private void setButtonClickListener() {
        sendAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
            }
        });
    }

    private void saveAnswer(){
        EditText input = (EditText)findViewById(R.id.qa_answerbox);
        String answerText = input.getText().toString();
        ParseConnector connector = new ParseConnector();
        Answer answer = new Answer.Builder().content(answerText).parentQuestionId(questionId).build();
        connector.storeAnswer(answer);
    }

    private void refreshContents(){

    }

    private void getAnswersAndAttach(){
        ParseConnector connector = new ParseConnector();
        ArrayList<Answer> answerList = connector.getAnswersForQuestion(questionId);
        attachAnswerList(answerList);
    }

    private void setQuestionContents() {
        TextView content = (TextView)findViewById(R.id.qa_questionContent);
        TextView title = (TextView)findViewById(R.id.qa_questionTitle);
        title.setText(getIntent().getStringExtra("title"));
        content.setText(getIntent().getStringExtra("content"));
    }

    private void attachAnswerList(ArrayList<Answer> answerList) {
        answersListView = (AbsListView) findViewById(R.id.qa_answerList);
        answersAdapter = new AnswerListAdapter(this, android.R.id.text1, answerList);
        answersListView.setAdapter(answersAdapter);


        //answersListView.setOnItemClickListener(this);
    }


}
