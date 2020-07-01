package com.jcupzz.mycoviddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

public class Scanner_History extends AppCompatActivity {
FirebaseFirestore db;
SharedPreferences shared;
Scanner_Adapter scanner_adapter;
ArrayList<Models> arraylistmodels = new ArrayList<>();
RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner__history);


        db = FirebaseFirestore.getInstance();

        shared = getSharedPreferences("email_save", MODE_PRIVATE);
        String email_id = (shared.getString("email", ""));

        mRecyclerView = findViewById(R.id.s_recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (arraylistmodels.size() > 0)
            arraylistmodels.clear();


        db.collection(email_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot querySnapshot : Objects.requireNonNull(task.getResult())) {
                            Models modelss = new Models(querySnapshot.getString("qr_result"),querySnapshot.getTimestamp("qr_timestamp"),querySnapshot.getString("qr_day"),querySnapshot.getString("qr_date"));
                            arraylistmodels.add(modelss);
                        }
                        scanner_adapter = new Scanner_Adapter(Scanner_History.this, arraylistmodels);
                        mRecyclerView.setAdapter(scanner_adapter);
                    }
                });

    }

}