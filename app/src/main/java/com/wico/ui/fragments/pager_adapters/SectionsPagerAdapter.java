package com.wico.ui.fragments.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wico.ui.PageActivity;
import com.wico.ui.fragments.PageContentViewFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private String pageId;

    public SectionsPagerAdapter(FragmentManager fm, String pageId) {
        super(fm);
        this.pageId = pageId;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0: return PageContentViewFragment.newInstance(pageId);
            //case 1: return QuestionListFragment.newInstance(pageId);
            default: return PageActivity.PlaceholderFragment.newInstance(position + 1);
        }
    }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Page";
            case 1:
                return "Children";
            case 2:
                return "Questions";
        }
        return null;
    }
}
