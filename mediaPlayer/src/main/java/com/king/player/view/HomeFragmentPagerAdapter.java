package com.king.player.view;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.Collections;
import java.util.List;

class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments;

    public HomeFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
//        super(fm);
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
    
}