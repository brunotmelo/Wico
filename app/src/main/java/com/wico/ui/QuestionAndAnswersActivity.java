package com.wico.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wico.R;
import com.wico.datatypes.Answer;
import com.wico.network.ParseConnector;
import com.wico.ui.adapters.AnswerListAdapter;
import com.wico.ui.threads.AnswerSaver;
import com.wico.ui.threads.listeners.AnswerSavedListener;

import java.util.ArrayList;

public class QuestionAndAnswersActivity extends AppCompatActivity {

    private AbsListView answersListView;
    private ListAdapter answersAdapter;
    private FloatingActionButton sendAnswerButton;
    private EditText answerInputText;

    private String questionId;

    private AnswerSavedListener savedListener = new AnswerSavedListener(){
        @Override
        public void onAnswerSaved(){
            answerSaved();
        }
    };
    private Thread.UncaughtExceptionHandler notSavedListener = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable e) {
            unableToSaveAnswer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_answers);
        setToolbar();
        startUiVariables();
        questionId = getIntent().getStringExtra("questionId");
        setQuestionContents();
        setButtonClickListener();
        getAnswersAndAttach();
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void startUiVariables(){
        answerInputText = (EditText)findViewById(R.id.qa_answerbox);
        sendAnswerButton = (FloatingActionButton) findViewById(R.id.qa_sendAnswerButton);
        answersListView = (AbsListView) findViewById(R.id.qa_answerList);
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
        lockInputUi();
        String answerText = answerInputText.getText().toString();
        Answer answer = new Answer.Builder().content(answerText).parentQuestionId(questionId).build();
        AnswerSaver saverThread = new AnswerSaver(answer, savedListener);
        saverThread.setUncaughtExceptionHandler(notSavedListener);
        saverThread.start();
    }

    //Callback
    private void answerSaved(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                unlockInputUi();
                sendSavedMessage();
                refreshContents();
            }
        });
    }

    //Exception Callback
    private void unableToSaveAnswer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                unlockInputUi();
                sendNotSavedMessage();
            }
        });
    }

    private void lockInputUi(){
        answerInputText.setEnabled(false);
        sendAnswerButton.setEnabled(false);
    }

    private void unlockInputUi(){
        answerInputText.setEnabled(true);
        answerInputText.setText("");
        sendAnswerButton.setEnabled(true);
    }

    private void sendSavedMessage(){
        Toast.makeText(this, "Answer saved", Toast.LENGTH_SHORT).show();
    }

    private void sendNotSavedMessage(){
        Toast.makeText(this,"An error ocurred, your answer was not saved", Toast.LENGTH_SHORT).show();
    }

    private void refreshContents(){
        getAnswersAndAttach();
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
        answersAdapter = new AnswerListAdapter(this, android.R.id.text1, answerList);
        answersListView.setAdapter(answersAdapter);
    }
}
