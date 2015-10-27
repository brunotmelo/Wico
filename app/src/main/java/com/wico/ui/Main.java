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

import com.parse.Parse;
import com.parse.ParseObject;
import com.wico.R;
import com.wico.datatypes.Question;

public class Main extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState){
        connectToParse();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabNewQuestion);
        int buttonColor = ContextCompat.getColor(this, R.color.colorAccent);
        fab.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CreateQuestionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void connectToParse(){
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Question.class);
        Parse.initialize(this, "rvro91QbTePbPJKwAfB5TcMjoXzVH8ewSawqk7uk", "8W1XCtK31EAh9EXY5Fp7kbePKkT7eDO92DdxmHEr");
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
         return super.onOptionsItemSelected(item);
    }

}
