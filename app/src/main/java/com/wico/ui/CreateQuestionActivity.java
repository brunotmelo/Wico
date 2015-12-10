package com.wico.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseUser;
import com.wico.R;
import com.wico.datatypes.Question;
import com.wico.ui.threads.QuestionSaver;

public class CreateQuestionActivity extends AppCompatActivity {

    private FloatingActionButton sendButton;
    private ProgressBar spinner;
    private EditText title;
    private EditText content;

    private String parentPageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        setToolbar();
        startUiElements();
        parentPageId = getIntent().getStringExtra("parentPageId");
    }

    private void setToolbar() {
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

    private void startUiElements() {
        spinner = (ProgressBar) findViewById(R.id.savingQuestionProgressBar);
        title = (EditText) findViewById(R.id.titleEditText);
        content = (EditText) findViewById(R.id.contentEditText);
        sendButton = (FloatingActionButton) findViewById(R.id.cq_fab);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuestionAndSave();
            }
        });
    }

    private void getQuestionAndSave() {
        String title = getUiTitle();
        String content = getUiContent();
        String authorName = ParseUser.getCurrentUser().getUsername();
        Question question = new Question.Builder()
                .title(title)
                .content(content)
                .parentId(parentPageId)
                .author(authorName)
                .build();
        saveQuestion(question);
        lockUi();
    }

    private void saveQuestion(Question question) {
        QuestionSaver questionSaverThread = new QuestionSaver(this, question);
        setThreadExceptionHandler();
        questionSaverThread.start();
    }

    private void setThreadExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                unableToSaveExceptionHandler();
            }
        });
    }

    private void unableToSaveExceptionHandler() {
        unlockUi();
        Toast.makeText(this, "Unable to save question", Toast.LENGTH_LONG).show();
    }

    private String getUiTitle() {
        return title.getText().toString();
    }

    private String getUiContent() {
        return content.getText().toString();
    }

    private void lockUi() {
        sendButton.setEnabled(false);
        title.setEnabled(false);
        content.setEnabled(false);
        spinner.setVisibility(View.VISIBLE);
    }

    private void unlockUi() {
        sendButton.setEnabled(true);
        title.setEnabled(true);
        content.setEnabled(true);
    }

    public void onQuestionSaved() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);
                openMainScreen();
            }
        });
    }

    private void openMainScreen() {
        onBackPressed();
    }

}
