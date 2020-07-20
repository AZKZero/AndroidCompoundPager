package com.azkzer0.androidimagepager;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.azkzer0.compoundpager.pagercomponent.CompoundPagerAdapter;
import com.azkzer0.compoundpager.pagercomponent.CompoundPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] urls = new String[]{
            "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
            "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",
            "https://homepages.cae.wisc.edu/~ece533/images/fruits.png",
            "https://homepages.cae.wisc.edu/~ece533/images/goldhill.png",
            "https://homepages.cae.wisc.edu/~ece533/images/peppers.png",
    };
    String urlX = "https://homepages.cae.wisc.edu/~ece533/images/serrano.png";
    private CompoundPagerAdapter adapter;
    private ArrayList<Fragment> fragmentsTop;
    private ArrayList<Fragment> fragmentsBottom;
    private ArrayList<Fragment> fragmentsLeft;
    private ArrayList<Fragment> fragmentsRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CompoundPager compoundPagerTop = findViewById(R.id.magnus9);
        CompoundPager compoundPagerLeft = findViewById(R.id.magnus5);
        CompoundPager compoundPagerRight = findViewById(R.id.magnus3);
        CompoundPager compoundPagerBottom = findViewById(R.id.magnus7);

        Button button = findViewById(R.id.add_another);
        button.setOnClickListener(view -> {
           /* testF testF = new testF();
            Bundle bundle = new Bundle();
            bundle.putString("text", urlX + " counter Extra");
            bundle.putString("imageURL", urlX);
            testF.setArguments(bundle);
            fragments.add(testF);

            adapter.notifyDataSetChanged();*/
        });

        fragmentsTop = new ArrayList<>();
        fragmentsRight = new ArrayList<>();
        fragmentsLeft = new ArrayList<>();
        fragmentsBottom = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            testF testFB = new testF();
            Bundle bundle = new Bundle();
            bundle.putString("text", urls[i] + " counter " + i);
            bundle.putString("imageURL", urls[i]);
            testFB.setArguments(bundle);

            testF testFL = new testF();
            testFL.setArguments(bundle);

            testF testFR = new testF();
            testFR.setArguments(bundle);

            testF testFT = new testF();
            testFT.setArguments(bundle);

            fragmentsTop.add(testFT);
            fragmentsBottom.add(testFB);
            fragmentsLeft.add(testFL);
            fragmentsRight.add(testFR);
        }

        CompoundPagerAdapter compoundPagerAdapterTop = new CompoundPagerAdapter(getSupportFragmentManager(), fragmentsTop);
        CompoundPagerAdapter compoundPagerAdapterBottom = new CompoundPagerAdapter(getSupportFragmentManager(), fragmentsBottom);
        CompoundPagerAdapter compoundPagerAdapterLeft = new CompoundPagerAdapter(getSupportFragmentManager(), fragmentsLeft);
        CompoundPagerAdapter compoundPagerAdapterRight = new CompoundPagerAdapter(getSupportFragmentManager(), fragmentsRight);
        compoundPagerTop.setAdapter(compoundPagerAdapterTop);
        compoundPagerTop.setTimeInterval(5000);
        compoundPagerLeft.setAdapter(compoundPagerAdapterLeft);
        compoundPagerLeft.setTimeInterval(5000);
        compoundPagerRight.setAdapter(compoundPagerAdapterRight);
        compoundPagerRight.setTimeInterval(5000);
        compoundPagerBottom.setAdapter(compoundPagerAdapterBottom);
        compoundPagerBottom.setTimeInterval(5000);
    }
}
