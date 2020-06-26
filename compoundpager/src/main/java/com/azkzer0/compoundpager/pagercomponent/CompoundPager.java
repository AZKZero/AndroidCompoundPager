package com.azkzer0.compoundpager.pagercomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import com.azkzer0.compoundpager.R;
import com.azkzer0.compoundpager.databinding.ComponentImagepagerBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CompoundPager extends ConstraintLayout {
    ViewPager viewPager;
    TabLayout tabLayout;

    private int tabSelectorLayout;
    private ComponentImagepagerBinding componentImagepagerBinding;
    private Runnable runnable;
    private Handler handler;
    private CompoundPagerAdapter compoundPagerAdapter;
    private int counter = 0;
    private int milliSeconds = 0;
    public static final String TAG = "CompoundPager";

    public CompoundPager(Context context) {
        super(context);
        initView();
    }

    public CompoundPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        View inflated = inflate(getContext(), R.layout.component_imagepager, this);
        processAttributes(getContext().obtainStyledAttributes(attrs, R.styleable.CompoundPager), inflated);
    }

    private void processAttributes(TypedArray obtainedStyledAttributes, View inflated) {
        /*componentImagepagerBinding = DataBindingUtil.bind(inflated);

        assert componentImagepagerBinding != null;*/

        viewPager = inflated.findViewById(R.id.image_pager);
        tabLayout = inflated.findViewById(R.id.tab_layout_image_pager);

        boolean viewPagerHeightWrapContent = obtainedStyledAttributes.getBoolean(R.styleable.CompoundPager_viewPagerHeightWrapContent, false);
        int viewPagerHeightI = obtainedStyledAttributes.getDimensionPixelSize(R.styleable.CompoundPager_viewPagerHeight, 300);
        /*componentImagepagerBinding.imagePager*/
        viewPager.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewPagerHeightWrapContent ? ViewGroup.LayoutParams.WRAP_CONTENT : viewPagerHeightI));

        tabSelectorLayout = obtainedStyledAttributes.getResourceId(R.styleable.CompoundPager_tabIndicatorLayout, R.layout.default_tab_select);

    }

    public CompoundPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.component_imagepager, this);
    }

    public void setAdapter(CompoundPagerAdapter compoundPagerAdapter) {
        this.compoundPagerAdapter = compoundPagerAdapter;
        this.compoundPagerAdapter.addDataChangeReference(this);
//        if (componentImagepagerBinding != null) {

        /*componentImagepagerBinding.imagePager*/
        viewPager.setAdapter(compoundPagerAdapter);

        /*componentImagepagerBinding.tabLayoutImagePager*/
        tabLayout.setupWithViewPager(/*componentImagepagerBinding.imagePager*/viewPager, true);

        /*componentImagepagerBinding.imagePager*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                counter = i;
                handler.removeCallbacksAndMessages(null);
                if (milliSeconds > 0) handler.postDelayed(runnable, milliSeconds);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        if (compoundPagerAdapter.getCount() > 0) /*componentImagepagerBinding.imagePager*/ viewPager.setCurrentItem(0);

        for (int i = 0; i < compoundPagerAdapter.getCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(tabSelectorLayout);
        }

//        }
    }

    public int getCount() {
        return compoundPagerAdapter.getCount();
    }

    public CompoundPagerAdapter getCompoundPagerAdapter() {
        return compoundPagerAdapter;
    }

    public boolean setCustomTabViews(ArrayList<View> tabViews) {
        if (tabViews.size() < compoundPagerAdapter.getCount()) {
            Log.w(TAG, "setCustomTabViews: View List is less than total items");
            return false;
        }
        for (int i = 0; i < compoundPagerAdapter.getCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(tabSelectorLayout);
        }
        return true;
    }

    public void refresh() {
        for (int i = 0; i < compoundPagerAdapter.getCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(tabSelectorLayout);
        }
    }

    public void setTimeInterval(int milliSeconds) {
        this.milliSeconds = milliSeconds;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        if (runnable == null) {
            runnable = () -> {
                increaseCounter();
                /*componentImagepagerBinding.imagePager*/
                viewPager.setCurrentItem(counter, true);
            };
        } else handler.removeCallbacks(runnable);

        handler.postDelayed(runnable, milliSeconds);
    }

    private void increaseCounter() {
        if (counter < compoundPagerAdapter.getCount() - 1) counter++;
        else counter = 0;
    }

}
