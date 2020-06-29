package com.jcupzz.mycoviddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class QRCode_Vehicle_Details extends AppCompatActivity {
EditText g_qr_tv_licence_no;
String licence_no;
Button g_qr_vehicle_btn;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code__vehicle__details);

        g_qr_tv_licence_no = findViewById(R.id.g_qr_licence_no_id);
        g_qr_vehicle_btn = findViewById(R.id.g_vehicle_qrcode_btn_id);


        g_qr_vehicle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(g_qr_tv_licence_no.length()!=0) {
                    licence_no = g_qr_tv_licence_no.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), Generator.class);
                    intent.putExtra("qr_licence_no", licence_no);
                    startActivity(intent);
                }
                else
                {
                    Toasty.error(getApplicationContext(), "Please enter the vehicle number", Toast.LENGTH_SHORT, true).show();
                }
            }
        });

    }
}