package com.example.atharva.memeapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterProfil extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterProfil(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragemnetPro1 tab1 = new FragemnetPro1();
                return tab1;
            case 1:
                FragemnetPro2 tab2 = new FragemnetPro2();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}