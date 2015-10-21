package com.wico.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wico.R;
import com.wico.datatypes.Question;

public class CreateQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
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


    }

    private String getUiTitle(){
        TextView title = (TextView) findViewById(R.id.titleEditText);
        return (String)title.getText();
    }

    private String getUiContent(){
        TextView content = (TextView) findViewById(R.id.contentEditText);
        return (String)content.getText();
    }

}
