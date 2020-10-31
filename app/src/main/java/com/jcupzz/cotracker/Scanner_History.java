package com.jcupzz.cotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Scanner_History extends AppCompatActivity {
    FirebaseFirestore db;
    SharedPreferences shared;
    Scanner_Adapter scanner_adapter;
    ArrayList<Models> arraylistmodels = new ArrayList<>();
    RecyclerView mRecyclerView;
    LottieAnimationView lottieAnimationView;
    TextView s_no_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner__history);

s_no_content = findViewById(R.id.s_no_content_id);
lottieAnimationView = findViewById(R.id.s_animation_view);
        db = FirebaseFirestore.getInstance();

        shared = getSharedPreferences("email_save", MODE_PRIVATE);
        String email_id = (shared.getString("email", ""));

        mRecyclerView = findViewById(R.id.s_recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (arraylistmodels.size() > 0)
            arraylistmodels.clear();


        db.collection(email_id).document("scanner_result").collection("res")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!(task.getResult().isEmpty())) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Models modelss = new Models(documentSnapshot.getString("qr_result"), documentSnapshot.getTimestamp("qr_timestamp"), documentSnapshot.getString("qr_day"), documentSnapshot.getString("qr_date"));
                                arraylistmodels.add(modelss);
                            }
                        }
                        else
                        {
                            lottieAnimationView.setVisibility(View.VISIBLE);
                            s_no_content.setVisibility(View.VISIBLE);
                        }
                        scanner_adapter = new Scanner_Adapter(Scanner_History.this, arraylistmodels);
                        mRecyclerView.setAdapter(scanner_adapter);
                    }
                });

    }

}