package com.wico.ui;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.wico.R;
import com.wico.datatypes.Question;
import com.wico.network.ParseConnector;
import com.wico.ui.CreateQuestionActivity;

public class Main extends AppCompatActivity {

    private TextView connectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        connectToParse();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set action button background
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabNewQuestion);
        int btncolor = getResources().getColor(R.color.colorAccent);
        fab.setBackgroundTintList(ColorStateList.valueOf(btncolor));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateQuestionActivity.class);
                startActivity(intent);

            }
        });
        connectToParse();
    }

    private void connectToParse(){
        ParseConnector connector = new ParseConnector();
        connector.initialize(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        //Fragment frag = getFragmentManager().findFragmentById(R.id.Main_questionListFragment);
        //frag.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
