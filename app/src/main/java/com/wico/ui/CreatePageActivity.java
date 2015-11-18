package com.wico.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wico.R;
import com.wico.datatypes.WicoPage;
import com.wico.ui.threads.WicoPageSaver;
import com.wico.ui.threads.listeners.PageSavedListener;

public class CreatePageActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private EditText titleInput;
    private EditText contentInput;

    private View.OnClickListener savePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getPageAndSave();
        }
    };
    private PageSavedListener savedListener = new PageSavedListener(){
        @Override
        public void onPageSaved(){
            pageSaved();
        }
    };
    private Thread.UncaughtExceptionHandler notSavedListener = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable e) {
            unableToSavePage();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_page);
        setToolbar();
        startUiElements();
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

    private void startUiElements(){
        titleInput = (EditText) findViewById(R.id.titleEditText);
        contentInput = (EditText) findViewById(R.id.contentEditText);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(savePageListener);
    }

    private void getPageAndSave(){
        lockInputUi();
        String title = getUiTitle();
        String content = getUiContent();
        WicoPage page = new WicoPage.Builder().title(title).content(content).path("/").build();
        savePage(page);
    }

    private void savePage(WicoPage page){
        WicoPageSaver pageSaverThread = new WicoPageSaver(page,savedListener);
        pageSaverThread.setUncaughtExceptionHandler(notSavedListener);
        pageSaverThread.start();
    }

    //callback
    private void pageSaved(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                unlockInputUi();
                sendSavedMessage();
            }
        });
    }

    //exeption callback
    private void unableToSavePage(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                unlockInputUi();
                sendNotSavedMessage();
            }
        });
    }

    private void sendSavedMessage(){
        Toast.makeText(this, "Page saved", Toast.LENGTH_SHORT).show();
    }

    private void sendNotSavedMessage(){
        Toast.makeText(this,"An error ocurred, the page was not saved", Toast.LENGTH_SHORT).show();
    }

    private void lockInputUi(){
        titleInput.setEnabled(false);
        contentInput.setEnabled(false);
        fab.setEnabled(false);
    }

    private void unlockInputUi(){
        titleInput.setEnabled(true);
        contentInput.setEnabled(true);
        fab.setEnabled(true);
    }

    private String getUiTitle() {
        return titleInput.getText().toString();
    }

    private String getUiContent() {
        return contentInput.getText().toString();
    }


}
