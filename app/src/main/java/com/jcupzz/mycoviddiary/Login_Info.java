package com.jcupzz.mycoviddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class Login_Info extends AppCompatActivity {
    Button next_btn;
    public static String username=null;
    EditText Name;
    FirebaseAuth fAuth;
    TextView already_registered;
    SharedPreferences sharedPreferences;
    EditText mEmail, mPassword;
    public static String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__info);

        mPassword = findViewById(R.id.password);
        mEmail = findViewById(R.id.email);
        next_btn = findViewById(R.id.next_id);
        Name = findViewById(R.id.name_tv_id);
        already_registered = findViewById(R.id.createText);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        username = Name.getText().toString();

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();

                SharedPreferences shared = getSharedPreferences("email_save", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("email", email);
                editor.commit();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toasty.success(Login_Info.this, "User Created", Toast.LENGTH_SHORT, true).show();

                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            userID = currentUser.getUid().trim();

                            SharedPreferences save_uid_sharedprefs = getSharedPreferences("uid_save", MODE_PRIVATE);
                            SharedPreferences.Editor uid_editor = save_uid_sharedprefs.edit();
                            uid_editor.putString("uid", userID);
                            uid_editor.commit();

                            Intent intent = new Intent(Login_Info.this,MainActivity.class);
                            startActivity(intent);

                        }else {
                            Toasty.error(Login_Info.this,"Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT, true).show();
                        }
                    }
                });


            }



        });

        already_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Info.this,Already_Registered.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed() {
        finishAffinity();

    }

}