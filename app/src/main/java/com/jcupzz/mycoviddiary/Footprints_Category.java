package com.jcupzz.mycoviddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Footprints_Category extends AppCompatActivity {
ImageView fp_l_h_btn,fp_s_h_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footprints__category);

fp_l_h_btn = findViewById(R.id.fp_l_h_img_id);
fp_s_h_btn = findViewById(R.id.fp_s_h_img_id);

fp_l_h_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Footprints_Category.this, Location_History.class);
        startActivity(intent);
    }
});
fp_s_h_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Footprints_Category.this, Scanner_History.class);
        startActivity(intent);
    }
});
    }
}