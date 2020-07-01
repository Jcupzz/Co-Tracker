package com.jcupzz.mycoviddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class Location_History extends AppCompatActivity {
    TextView date_tv,day_tv;
    Location_Adapter myAdapter;
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Lati_Longi_Models> downModelArrayList = new ArrayList<>();
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location__history);

        db = FirebaseFirestore.getInstance();

        shared = getSharedPreferences("email_save", MODE_PRIVATE);
        String email_id = (shared.getString("email", ""));

        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (downModelArrayList.size() > 0)
            downModelArrayList.clear();

        Toast.makeText(Location_History.this,email_id,Toast.LENGTH_SHORT).show();

        db.collection(email_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot querySnapshot : Objects.requireNonNull(task.getResult())) {
                            Lati_Longi_Models lati_longi_models = new Lati_Longi_Models(querySnapshot.getTimestamp("timestamp"), querySnapshot.getString("day"), querySnapshot.getString("date"), querySnapshot.getString("lati_S"), querySnapshot.getString("longi_S"));
                            downModelArrayList.add(lati_longi_models);
                        }
                        myAdapter = new Location_Adapter(Location_History.this, downModelArrayList);
                        mRecyclerView.setAdapter(myAdapter);
                    }
                });

    }

}
