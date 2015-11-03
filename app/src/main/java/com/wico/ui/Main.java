package com.wico.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wico.R;
import com.wico.ui.threads.NetworkChecker;
import com.wico.network.ParseConnector;
import com.wico.ui.fragments.QuestionListFragment;

public class Main extends AppCompatActivity {

    private TextView connectText;
    private QuestionListFragment listFragment;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectToParse();
        setContentView(R.layout.activity_main);
        setToolbar();
        startUiVariables();
        waitInternetAndLoadContent();
    }

    private void connectToParse() {
        ParseConnector connector = new ParseConnector();
        connector.initialize(this);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void startUiVariables() {
        listFragment = (QuestionListFragment) getFragmentManager().findFragmentById(R.id.Main_questionListFragment);
        connectText = (TextView) findViewById(R.id.connectmessage);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fabNewQuestion);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getFabColor()));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateQuestionActivity.class);
                startActivity(intent);
            }
        });
    }

    private int getFabColor() {
        return ContextCompat.getColor(this, R.color.colorAccent);
    }

    public void waitInternetAndLoadContent() {
        NetworkChecker checker = new NetworkChecker(this);
        checker.setNetworkCheckerListener(new NetworkChecker.NetworkCheckerListener() {
            @Override
            public void onConnected() {
                connectedToInternet();
            }
        });
        checker.start();
    }

    private void connectedToInternet() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                connectText.setVisibility(View.GONE);
                listFragment.loadQuestions();
            }
        });
    }

    @Override
    public void onResume() {
        waitInternetAndLoadContent();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
