package com.example.khanh.melody.Saved;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }



    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomesFragment();
        } else if (position == 1) {
            return new OpenHomesFragment();
        } else {
            return new Saved_Searches_Fragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "HOMES";
            case 1:
                return "OPEN HOUSE";
            case 2:
                return " SEARCHES";
            default:
                return null;
        }
    }

}
