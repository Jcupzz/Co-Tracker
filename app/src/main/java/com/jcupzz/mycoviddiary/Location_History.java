package com.jcupzz.mycoviddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Location_History extends AppCompatActivity {
    TextView l_no_content;
    Location_Adapter myAdapter;
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Lati_Longi_Models> downModelArrayList = new ArrayList<>();
    SharedPreferences shared;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location__history);

        db = FirebaseFirestore.getInstance();
lottieAnimationView = findViewById(R.id.l_animation_view);
l_no_content = findViewById(R.id.l_no_content_id);

        shared = getSharedPreferences("email_save", MODE_PRIVATE);
        String email_id = (shared.getString("email", ""));

        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (downModelArrayList.size() > 0)
            downModelArrayList.clear();

        Toasty.info(Location_History.this,"Click on cards to view the location on maps",Toasty.LENGTH_LONG,true).show();

        db.collection(email_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!(task.getResult().isEmpty())) {


                            for (DocumentSnapshot documentSnapshot : (task.getResult())) {
                                Lati_Longi_Models lati_longi_models = new Lati_Longi_Models(documentSnapshot.getTimestamp("timestamp"), documentSnapshot.getString("day"), documentSnapshot.getString("date"), documentSnapshot.getString("lati_S"), documentSnapshot.getString("longi_S"));
                                downModelArrayList.add(lati_longi_models);
                            }
                        }
                        else
                        {
                            lottieAnimationView.setVisibility(View.VISIBLE);
                            l_no_content.setVisibility(View.VISIBLE);
                        }
                        myAdapter = new Location_Adapter(Location_History.this, downModelArrayList);
                        mRecyclerView.setAdapter(myAdapter);
                    }
                });

    }

}
