package com.jcupzz.mycoviddiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import es.dmoral.toasty.Toasty;


public class Generator extends AppCompatActivity {
    String name,address;
    String num;
    ImageView qr;
    String licence_no;
String qr_var;
ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

constraintLayout = findViewById(R.id.cl_layout);




                qr = findViewById(R.id.qr_id);

        Intent intent = getIntent();
        name = intent.getStringExtra("qr_name");
        address = intent.getStringExtra("qr_address");
        num = intent.getStringExtra("qr_num");
licence_no = intent.getStringExtra("qr_licence_no");



        if(QRCode_Generator.qr_decider==1)
        {
            qr_var = licence_no;
        }
        if (QRCode_Generator.qr_decider==2)
        {
            qr_var = name+"\n"+address+"\n"+num;
        }

        Toasty.info(getApplicationContext(),"Take a screenshot of this QRCode by pressing volume down button and power button together",Toasty.LENGTH_LONG,true).show();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(qr_var, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qr.setImageBitmap(bitmap);



        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

}