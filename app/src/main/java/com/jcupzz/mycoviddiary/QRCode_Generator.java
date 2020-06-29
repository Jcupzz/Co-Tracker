package com.jcupzz.mycoviddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class QRCode_Generator extends AppCompatActivity {
ImageView qrcode_shop_img_btn,qrcode_vehicle_img_btn;
public static int qr_decider=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code__generator);

        qrcode_shop_img_btn = findViewById(R.id.qr_g_shop_img_id);
        qrcode_vehicle_img_btn = findViewById(R.id.qr_g_vehicle_img_id);

        qrcode_vehicle_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QRCode_Vehicle_Details.class);
                qr_decider=1;
                startActivity(intent);
            }
        });

        qrcode_shop_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QRcode_Shop_Details.class);
                qr_decider=2;
                startActivity(intent);
            }
        });

    }
}