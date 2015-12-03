package com.wico.ui.fragments.pager_adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.wico.ui.fragments.ActivityFabOverriderFragment;
import com.wico.ui.fragments.ChildrenPagesFragment;
import com.wico.ui.fragments.PageContentViewFragment;
import com.wico.ui.fragments.QuestionListFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private String pageId;
    SparseArray<ActivityFabOverriderFragment> registeredFragments = new SparseArray<>();

    public SectionsPagerAdapter(FragmentManager fm, String pageId) {
        super(fm);
        this.pageId = pageId;
    }

    @Override
    public ActivityFabOverriderFragment getItem(int position) {
        switch (position){
            case 0: return PageContentViewFragment.newInstance(pageId);
            case 1: return ChildrenPagesFragment.newInstance(pageId);
            case 2: return QuestionListFragment.newInstance(pageId);
            default: return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ActivityFabOverriderFragment fragment = (ActivityFabOverriderFragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
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

    public ActivityFabOverriderFragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
