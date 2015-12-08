package com.wico.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wico.R;
import com.wico.network.ParseConnector;
import com.wico.ui.fragments.ActivityFabOverriderFragment;
import com.wico.ui.adapters.SectionsPagerAdapter;

public class PageActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FloatingActionButton fab;
    public static final String MASTER_PAGE_ID = "iEmjO2Xvs2";
    private String pageId;

    private View.OnClickListener defaultFabCallBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            overrideFabForCurrentTab(0);
            fab.performClick();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        connectToParse();
        getPagePathFromIntent();
        setToolbar();
        startUiVariables();
        setTabLayout();
    }

    private void connectToParse() {
        ParseConnector connector = new ParseConnector();
        connector.initialize(this);
    }

    private void getPagePathFromIntent() {
        Intent intent = getIntent();
        pageId = intent.getStringExtra("pageId");
        if (pageId == null){
            pageId = MASTER_PAGE_ID;
        }
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), pageId);
    }

    private void startUiVariables(){
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        fab = (FloatingActionButton) findViewById(R.id.page_fab);
        fab.setOnClickListener(defaultFabCallBack);
    }

    private void setTabLayout(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(getTabListener());
    }

    private TabLayout.ViewPagerOnTabSelectedListener getTabListener(){
        return new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int numTab = tab.getPosition();
                overrideFabForCurrentTab(numTab);
            }
        };
    }

    private void overrideFabForCurrentTab(int numTab){
        ActivityFabOverriderFragment fragment = mSectionsPagerAdapter.getRegisteredFragment(numTab);
        fragment.overrideFab(fab);
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
