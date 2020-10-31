package com.jcupzz.cotracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class Location_History extends AppCompatActivity {
    TextView l_no_content;
    Location_Adapter myAdapter;
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Lati_Longi_Models> downModelArrayList = new ArrayList<>();
    SharedPreferences shared;
    LottieAnimationView lottieAnimationView;
    ConstraintLayout coordinatorLayout;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location__history);

        coordinatorLayout = findViewById(R.id.cl);
        db = FirebaseFirestore.getInstance();
        lottieAnimationView = findViewById(R.id.l_animation_view);
        l_no_content = findViewById(R.id.l_no_content_id);

        shared = getSharedPreferences("email_save", MODE_PRIVATE);
        String email_id = (shared.getString("email", ""));

        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (isInternetAvailable(Location_History.this)) {
            //do nothing
        } else {
            Toasty.warning(Location_History.this, "Please connect to internet!", Toasty.LENGTH_SHORT, true).show();
        }


        if (downModelArrayList.size() > 0)
            downModelArrayList.clear();


        db.collection(email_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!(task.getResult().isEmpty())) {


                            for (DocumentSnapshot documentSnapshot : (task.getResult())) {
                                Lati_Longi_Models lati_longi_models = new Lati_Longi_Models(documentSnapshot.getTimestamp("timestamp"), documentSnapshot.getString("day"), documentSnapshot.getString("date"), documentSnapshot.getString("lati_S"), documentSnapshot.getString("longi_S"), documentSnapshot.getString("address"));
                                downModelArrayList.add(lati_longi_models);
                            }
                        } else {
                            lottieAnimationView.setVisibility(View.VISIBLE);
                            l_no_content.setVisibility(View.VISIBLE);
                        }
                        myAdapter = new Location_Adapter(Location_History.this, downModelArrayList);
                        mRecyclerView.setAdapter(myAdapter);
                    }
                });

    }

    public static boolean isInternetAvailable(Context context) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            //Log.d(TAG,"no internet connection");
            return false;
        } else {
            if (info.isConnected()) {
                // Log.d(TAG," internet connection available...");
                return true;
            } else {
                // Log.d(TAG," internet connection");
                return true;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location_history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.location_history_menu_id) {
          final Snackbar snackbar =  Snackbar.make(coordinatorLayout, "To track your location we update your location to our database during every 10 minutes.", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Ok", new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 snackbar.dismiss();
             }
         });
          snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
          snackbar.getView().canScrollVertically(View.LAYOUT_DIRECTION_LTR);
          snackbar.setDuration(10000);
          snackbar.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
