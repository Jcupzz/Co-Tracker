package com.jcupzz.cotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class QRcode_Shop_Details extends AppCompatActivity {
    Button g_shop_qrcode;
    String name,address;
    String num;
    EditText g_qr_tv_name,g_qr_tv_address,g_qr_tv_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_rcode__shop__details);

        g_qr_tv_num = findViewById(R.id.g_qr_tv_num_id);
        g_qr_tv_address = findViewById(R.id.g_qr_tv_address_id);
        g_qr_tv_name = findViewById(R.id.g_qr_tv_name_id);
        g_shop_qrcode = findViewById(R.id.g_shop_qrcode_btn_id);

        g_shop_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = g_qr_tv_name.getText().toString();
                address = g_qr_tv_address.getText().toString();
                num = g_qr_tv_num.getText().toString();

                if(name.length()!=0&&address.length()!=0&&num.length()!=0) {

                    Intent intent = new Intent(getApplicationContext(), Generator.class);
                    intent.putExtra("qr_name", name);
                    intent.putExtra("qr_address", address);
                    intent.putExtra("qr_num", num);
                    startActivity(intent);
                }
                else{
                    Toasty.error(getApplicationContext(), "Please complete the above details", Toast.LENGTH_SHORT, true).show();
                }
            }
        });

    }
}