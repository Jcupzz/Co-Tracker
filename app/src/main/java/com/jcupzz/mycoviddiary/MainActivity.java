package com.jcupzz.mycoviddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Main_Vp_Adapter adapter;
    List<Main_Vp_Names> main_vp_list_names;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.signOut_id)
        {
            FirebaseAuth.getInstance().signOut();
            Intent myIntent = new Intent(MainActivity.this,  Login_Info.class);
            MainActivity.this.startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        main_vp_list_names =new ArrayList<Main_Vp_Names>();
        main_vp_list_names.add(new Main_Vp_Names("Location","Place's you have visited",R.raw.location_lottie));
        main_vp_list_names.add(new Main_Vp_Names("Number Plate Photography","Take photo's of number plate of vehicle's you have travelled",R.raw.car_lottie));
        main_vp_list_names.add(new Main_Vp_Names("QRCode Generator","Generate QRCode for shops,vehicle's,buildings....",R.raw.qrcodegenerator_lottie));
        main_vp_list_names.add(new Main_Vp_Names("QRCode Scanner","Scan QRCodes",R.raw.qrcodescanner_lottie));
        main_vp_list_names.add(new Main_Vp_Names("Visited People","Using bluetooth technology automatically identify people who came to contact with you",R.raw.spider_lottie));
        main_vp_list_names.add(new Main_Vp_Names("Your Footprints","History of places you have travelled",R.raw.footprint_one_lottie));

        adapter = new Main_Vp_Adapter(this,main_vp_list_names);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(120, 0, 120, 0);
        viewPager.setCurrentItem(0);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}