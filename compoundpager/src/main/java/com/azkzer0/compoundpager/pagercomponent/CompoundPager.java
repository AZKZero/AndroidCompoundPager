package com.azkzer0.compoundpager.pagercomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.ViewPager;

import com.azkzer0.compoundpager.R;
import com.azkzer0.compoundpager.interfaces.DataChange;

import java.util.HashMap;

public class CompoundPager extends ConstraintLayout implements DataChange {
    ViewPager viewPager;
    RadioGroup tabLayout;

    private int tabSelectorDrawable;
    private Runnable runnable;
    private Handler handler;
    private CompoundPagerAdapter compoundPagerAdapter;
    private int counter = 0;
    private int milliSeconds = 0;
    public static final String TAG = "CompoundPager";

    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    HashMap<Integer, Integer> idMap = new HashMap<>();
    HashMap<Integer, Integer> indexMap = new HashMap<>();

    public CompoundPager(Context context) {
        super(context);
        initView();
    }

    public CompoundPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        processAttributes(getContext().obtainStyledAttributes(attrs, R.styleable.CompoundPager));
    }

    private void processAttributes(TypedArray obtainedStyledAttributes) {

        int pagerLayout = obtainedStyledAttributes.getResourceId(R.styleable.CompoundPager_pagerLayout, R.layout.component_compound_pager);

        View inflated = inflate(getContext(), pagerLayout, this);

        int position = obtainedStyledAttributes.getInt(R.styleable.CompoundPager_tabsPosition, 1);

        tabLayout = inflated.findViewById(R.id.tab_layout_image_pager_bottom);

        showTabPosition(position, inflated);

        viewPager = inflated.findViewById(R.id.image_pager);

        boolean viewPagerHeightWrapContent = obtainedStyledAttributes.getBoolean(R.styleable.CompoundPager_viewPagerHeightWrapContent, false);
        int viewPagerHeightI = obtainedStyledAttributes.getDimensionPixelSize(R.styleable.CompoundPager_viewPagerHeight, 300);
        viewPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewPagerHeightWrapContent ? ViewGroup.LayoutParams.WRAP_CONTENT : viewPagerHeightI));

        if (obtainedStyledAttributes.getBoolean(R.styleable.CompoundPager_otherPagersInView, false)) viewPager.setId(View.generateViewId());

        ConstraintSet set = new ConstraintSet();
        set.connect(viewPager.getId(), ConstraintSet.TOP, R.id.tab_layout_image_pager_top, ConstraintSet.BOTTOM, 10);
        set.connect(viewPager.getId(), ConstraintSet.BOTTOM, R.id.tab_layout_image_pager_bottom, ConstraintSet.TOP, 10);
        set.connect(viewPager.getId(), ConstraintSet.LEFT, R.id.tab_layout_image_pager_left, ConstraintSet.RIGHT, 10);
        set.connect(viewPager.getId(), ConstraintSet.RIGHT, R.id.tab_layout_image_pager_right, ConstraintSet.LEFT, 10);

        set.applyTo(findViewById(R.id.pager));

        tabSelectorDrawable = obtainedStyledAttributes.getResourceId(R.styleable.CompoundPager_tabIndicatorDrawable, R.drawable.splash_selector);

    }

    public CompoundPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void showTabPosition(int position, View inflated) {
        switch (position) {
            case TOP:
                tabLayout = inflated.findViewById(R.id.tab_layout_image_pager_top);
                inflated.findViewById(R.id.tab_layout_image_pager_bottom).setVisibility(View.GONE);
                inflated.findViewById(R.id.tab_layout_image_pager_right).setVisibility(View.GONE);
                inflated.findViewById(R.id.tab_layout_image_pager_left).setVisibility(View.GONE);
                break;
            default:
            case BOTTOM:
                tabLayout = inflated.findViewById(R.id.tab_layout_image_pager_bottom);
                inflated.findViewById(R.id.tab_layout_image_pager_top).setVisibility(View.GONE);
                inflated.findViewById(R.id.tab_layout_image_pager_right).setVisibility(View.GONE);
                inflated.findViewById(R.id.tab_layout_image_pager_left).setVisibility(View.GONE);
                break;
            case LEFT:
                tabLayout = inflated.findViewById(R.id.tab_layout_image_pager_left);
                inflated.findViewById(R.id.tab_layout_image_pager_top).setVisibility(View.GONE);
                inflated.findViewById(R.id.tab_layout_image_pager_right).setVisibility(View.GONE);
                inflated.findViewById(R.id.tab_layout_image_pager_bottom).setVisibility(View.GONE);
                break;
            case RIGHT:
                tabLayout = inflated.findViewById(R.id.tab_layout_image_pager_right);
                inflated.findViewById(R.id.tab_layout_image_pager_top).setVisibility(View.GONE);
                inflated.findViewById(R.id.tab_layout_image_pager_bottom).setVisibility(View.GONE);
                inflated.findViewById(R.id.tab_layout_image_pager_left).setVisibility(View.GONE);
                break;
        }
    }

    private void initView() {
        inflate(getContext(), R.layout.component_compound_pager, this);
    }

    public void setAdapter(CompoundPagerAdapter compoundPagerAdapter) {
        this.compoundPagerAdapter = compoundPagerAdapter;
        this.compoundPagerAdapter.addDataChangeReference(this);
        viewPager.setAdapter(compoundPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                counter = i;
                handler.removeCallbacksAndMessages(null);
                if (milliSeconds > 0) handler.postDelayed(runnable, milliSeconds);
                Integer id = idMap.get(i);
                Log.i(TAG, "onPageSelected: " + id);
                if (id != null) tabLayout.check(id);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        if (compoundPagerAdapter.getCount() > 0) /*componentImagepagerBinding.imagePager*/ viewPager.setCurrentItem(0);

        for (int i = 0; i < compoundPagerAdapter.getCount(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(View.generateViewId());
            radioButton.setPadding(10, 10, 10, 10);
            radioButton.setButtonDrawable(tabSelectorDrawable);
            idMap.put(i, radioButton.getId());
            indexMap.put(radioButton.getId(), i);
            tabLayout.addView(radioButton);
        }

        /*tabLayout.setOnCheckedChangeListener((group, checkedId) -> {
            Integer index = indexMap.get(checkedId);
            if (index != null && viewPager.getCurrentItem() != index)
                viewPager.setCurrentItem(index);
        });*/
    }

    public int getCount() {
        return compoundPagerAdapter.getCount();
    }

    public CompoundPagerAdapter getCompoundPagerAdapter() {
        return compoundPagerAdapter;
    }

    @Override
    public void refresh() {
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            ((RadioButton) tabLayout.getChildAt(i)).setButtonDrawable(tabSelectorDrawable);
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
