package com.azkzer0.compoundpager.pagercomponent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.azkzer0.compoundpager.fragments.CompoundPagerFragment;
import com.azkzer0.compoundpager.pagercomponent.CompoundPager;

import java.util.ArrayList;

public class CompoundPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragmentArrayList;
    private CompoundPager compoundPager;

    public CompoundPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    void addDataChangeReference(CompoundPager compoundPager) {
        this.compoundPager = compoundPager;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (compoundPager != null) compoundPager.refresh();
    }

    @Override
    public Fragment getItem(int i) {
        if (fragmentArrayList.size() > i)
            return fragmentArrayList.get(i);
        else return new CompoundPagerFragment();
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
