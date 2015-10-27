package com.wico.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.wico.R;
import com.wico.datatypes.Question;
import com.wico.ui.threads.QuestionSaver;

public class CreateQuestionActivity extends AppCompatActivity {

    private FloatingActionButton sendButton;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
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
        spinner = (ProgressBar)findViewById(R.id.savingQuestionProgressBar);
        sendButton = (FloatingActionButton) findViewById(R.id.fab);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveQuestion();
            }
        });
    }

    private void saveQuestion(){
        String title = getUiTitle();
        String content = getUiContent();
        Question question = new Question.Builder().title(title).content(content).build();
        QuestionSaver qs = new QuestionSaver(this,question);
        qs.run();
        savingQuestion();
    }

    private String getUiTitle(){
        EditText title = (EditText) findViewById(R.id.titleEditText);
        return title.getText().toString();
    }

    private String getUiContent(){
        EditText content = (EditText) findViewById(R.id.contentEditText);
        return content.getText().toString();
    }

    private void savingQuestion(){
        sendButton.setEnabled(false);
        spinner.setVisibility(View.VISIBLE);
    }

    public void onQuestionSaved(){
        spinner.setVisibility(View.GONE);
        openMainScreen();
    }

    private void openMainScreen(){
        onBackPressed();
    }

}
