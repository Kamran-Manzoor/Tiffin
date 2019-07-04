package com.kamores.tiffin;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> lstFragment = new ArrayList<>();
    private final List<String> listTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super( fm );
    }

    @Override
    public Fragment getItem(int position) {
        return lstFragment.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
    public void AddFragment (Fragment fragment,String title){
        lstFragment.add(fragment);
        listTitle.add(title);
    }
}