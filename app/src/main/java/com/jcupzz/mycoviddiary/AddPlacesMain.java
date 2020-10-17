package com.jcupzz.mycoviddiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddPlacesMain extends AppCompatActivity {
    FloatingActionButton add_btn;
    FirebaseFirestore db;
    SharedPreferences shared;
    AddPlaces_Adapter addPlaces_adapter;
    ArrayList<AddPlacesManually_Models> arraylistmodels = new ArrayList<>();
    RecyclerView mRecyclerView;
    TextView nocontent_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places_main);

        add_btn = findViewById(R.id.add_btn_id);
        nocontent_tv = findViewById(R.id.nocontent_tv_id);

        mRecyclerView = findViewById(R.id.addplaces_recycle_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (arraylistmodels.size() > 0)
            arraylistmodels.clear();

        shared = getSharedPreferences("email_save", MODE_PRIVATE);
        String email_id = (shared.getString("email", ""));


db = FirebaseFirestore.getInstance();

        db.collection(email_id).document("addplaces").collection("userplaces")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!(task.getResult().isEmpty())) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                AddPlacesManually_Models addplacemodels = new AddPlacesManually_Models(documentSnapshot.getString("uploadcontent"));
                                arraylistmodels.add(addplacemodels);
                            }
                        } else {
                            nocontent_tv.setVisibility(View.VISIBLE);
                        }
                        addPlaces_adapter = new AddPlaces_Adapter(AddPlacesMain.this, arraylistmodels);
                        mRecyclerView.setAdapter(addPlaces_adapter);

                    }
                });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlacesMain.this,AddPlacesManually.class);
                startActivity(intent);
                finish();
            }
        });

    }

}