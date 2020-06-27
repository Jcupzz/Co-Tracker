package com.jcupzz.mycoviddiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Main_Vp_Adapter adapter;
    List<Main_Vp_Names> main_vp_list_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_vp_list_names =new ArrayList<Main_Vp_Names>();
        main_vp_list_names.add(new Main_Vp_Names("Places You Have Visited",R.raw.location_lottie));
        main_vp_list_names.add(new Main_Vp_Names("Take Pictures Of Vehicle's You Have Travelled",R.raw.car_lottie));
        main_vp_list_names.add(new Main_Vp_Names("Location",R.raw.spider_lottie));
        main_vp_list_names.add(new Main_Vp_Names("Location",R.raw.spider_lottie));
        main_vp_list_names.add(new Main_Vp_Names("Location",R.raw.spider_lottie));

        adapter = new Main_Vp_Adapter(this,main_vp_list_names);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(120, 0, 120, 0);
        viewPager.setCurrentItem(1);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}