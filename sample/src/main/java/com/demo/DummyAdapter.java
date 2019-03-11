package com.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
import java.util.Locale;

public class DummyAdapter extends FragmentPagerAdapter {

    List<PlaceholderFragment> list;
        public DummyAdapter(FragmentManager fm, List<PlaceholderFragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return list.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "PAGE 3";
                case 1:
                    return "PAGE 1";
                case 2:
                    return "PAGE 2";
                case 3:
                    return "PAGE 3";
                case 4:
                    return "PAGE 1";
            }
            return null;
        }

    }
