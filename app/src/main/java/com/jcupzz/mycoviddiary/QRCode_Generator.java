package com.jcupzz.mycoviddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import es.dmoral.toasty.Toasty;

public class QRCode_Generator extends AppCompatActivity {
ImageView qrcode_shop_img_btn,qrcode_vehicle_img_btn;
public static int qr_decider=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code__generator);

        Toasty.info(QRCode_Generator.this,"Tap on info icon on top to view help",Toasty.LENGTH_SHORT).show();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qrcode_docs_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.qrcode_docs_menu_id)
        {
            Intent myIntent = new Intent(QRCode_Generator.this,  QRCode_Docs.class);
            QRCode_Generator.this.startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}