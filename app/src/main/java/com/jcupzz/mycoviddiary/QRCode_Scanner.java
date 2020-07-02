package com.jcupzz.mycoviddiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class QRCode_Scanner extends AppCompatActivity {
    private static final int REQUEST_CODE_QR_SCAN = 101;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button scan_btn;
    SharedPreferences shared;
    String result;
    int i=1;
    String[] perms = {"android.permission.CAMERA","android.permission.READ_EXTERNAL_STORAGE"};

    int permsRequestCode = 200;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode != Activity.RESULT_OK)
        {
            //Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(QRCode_Scanner.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
//            Log.d(LOGTAG,"Have scan result in your app activity :"+ result);
            AlertDialog alertDialog = new AlertDialog.Builder(QRCode_Scanner.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            uploaddatatofb();
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }



    }

    private void uploaddatatofb() {

         shared = getSharedPreferences("email_save", MODE_PRIVATE);
        String email_id = (shared.getString("email", ""));

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());


        Models data = new Models(result,Timestamp.now(),day,date);


        db.collection(email_id).document("scanner_result").collection("res").document(String.valueOf(Timestamp.now()))
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toasty.success(getApplicationContext(), "Successfully Uploaded to Cloud", Toast.LENGTH_SHORT, true).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code__scanner);

        scan_btn = findViewById(R.id.scan_btn_id);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(
                        v.getContext(), CAMERA_SERVICE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    // You can use the API that requires the permission.
                    Intent i = new Intent(QRCode_Scanner.this, QrCodeActivity.class);
                    startActivityForResult( i,REQUEST_CODE_QR_SCAN);

                }
                else{
                    ActivityCompat
                            .requestPermissions(
                                    QRCode_Scanner.this,
                                    perms,permsRequestCode
                                    );

                }

     }
        });


    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] perms, @NonNull int[] grantResults) {
        Intent i = new Intent(QRCode_Scanner.this, QrCodeActivity.class);
        startActivityForResult( i,REQUEST_CODE_QR_SCAN);
        super.onRequestPermissionsResult(permsRequestCode, perms, grantResults);
    }


}