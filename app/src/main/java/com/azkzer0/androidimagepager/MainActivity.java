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
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CompoundPager compoundPager = findViewById(R.id.comp);
        Button button = findViewById(R.id.add_another);
        button.setOnClickListener(view -> {
            testF testF = new testF();
            Bundle bundle = new Bundle();
            bundle.putString("text", urlX + " counter Extra");
            bundle.putString("imageURL", urlX);
            testF.setArguments(bundle);
            fragments.add(testF);

            adapter.notifyDataSetChanged();
        });

        fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            testF testF = new testF();
            Bundle bundle = new Bundle();
            bundle.putString("text", urls[i] + " counter " + i);
            bundle.putString("imageURL", urls[i]);
            testF.setArguments(bundle);
            fragments.add(testF);
        }
        compoundPager.setAdapter(adapter = new CompoundPagerAdapter(getSupportFragmentManager(), fragments));
        compoundPager.setTimeInterval(5000);
    }
}
