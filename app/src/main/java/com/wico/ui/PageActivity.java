package com.wico.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wico.R;
import com.wico.ui.fragments.pager_adapters.SectionsPagerAdapter;

public class PageActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FloatingActionButton fab;

    private View.OnClickListener openCreatePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),CreatePageActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener openCreateQuestionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),CreateQuestionActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),"pageid");

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        fab = (FloatingActionButton) findViewById(R.id.page_fab);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int numTab = tab.getPosition();

                overrideFloatingActionButton(numTab);
            }
        });
    }

    private void overrideFloatingActionButton(int numTab){
        switch(numTab){
            case 0: fabEditsPage();
                    break;
            case 1: fabAddsPage();
                    break;
            case 2: fabAddsQuestion();
                    break;
        }
    }

    private void fabEditsPage(){
        //fab.setOnClickListener(openEditPageListener);
        Drawable editIcon = getResources().getDrawable(R.drawable.ic_mode_edit_white_24dp);
        fab.setImageDrawable(editIcon);
    }

    private void fabAddsPage(){
        fab.setOnClickListener(openCreatePageListener);
        Drawable addIcon = getResources().getDrawable(R.drawable.ic_add);
        fab.setImageDrawable(addIcon);
    }

    private void fabAddsQuestion(){
        fab.setOnClickListener(openCreateQuestionListener);
        Drawable addIcon = getResources().getDrawable(R.drawable.ic_add);
        fab.setImageDrawable(addIcon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
