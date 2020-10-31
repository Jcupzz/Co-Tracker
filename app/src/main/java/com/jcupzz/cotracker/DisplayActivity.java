package com.jcupzz.cotracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;


public class DisplayActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = DisplayActivity.class.getSimpleName();
    private HashMap<String, Marker> mMarkers = new HashMap<>();
    private GoogleMap mMap;
    String uid;
    public static int j = 0;
    SharedPreferences uid_sharedprefs;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Authenticate with Firebase when the Google map is loaded
        mMap = googleMap;
        mMap.setMaxZoomPreference(16);
        loginToFirebase();
    }

    private void loginToFirebase() {
        subscribeToUpdates();
    }

    private void subscribeToUpdates() {

//        shared = getSharedPreferences("email_save", MODE_PRIVATE);
//        String email_id = (shared.getString("email", "crashed"));


        uid_sharedprefs = getSharedPreferences("uid_save", MODE_PRIVATE);
        uid = (uid_sharedprefs.getString("uid", "uid_shareprefs_crashed"));


        if(uid!=null) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(uid);
            ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    setMarker(snapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            Toasty.error(getApplicationContext(),"Error connecting to database! Please try again later!",Toasty.LENGTH_SHORT,true).show();
        }

    }

    private void setMarker(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
/*
    Intent intent = getIntent();
    String getlati = intent.getStringExtra("getlati");
    String getlongi = intent.getStringExtra("getlongi");
    double la = Double.parseDouble(getlati);
    double lo = Double.parseDouble(getlongi);

    LatLng locations = new LatLng(la, lo);

    if (!mMarkers.containsKey(key)) {
        mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key).position(locations)));
    } else {
        mMarkers.get(key).setPosition(locations);
    }
    LatLngBounds.Builder builders = new LatLngBounds.Builder();
    for (Marker marker : mMarkers.values()) {
        builders.include(marker.getPosition());
    }
    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builders.build(), 300));
*/

        Double mlatitude = dataSnapshot.child("latitude").getValue(Double.class);
        Double mlongitude = dataSnapshot.child("longitude").getValue(Double.class);


        Intent intent = getIntent();
        double la = intent.getDoubleExtra("la", 0);
        double lo = intent.getDoubleExtra("lo", 0);

        if (DisplayActivity.j == 1||DisplayActivity.j==0) {
            LatLng location = new LatLng(mlatitude, mlongitude);
            if (!mMarkers.containsKey(key)) {
                mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key).position(location)));
            } else {
                mMarkers.get(key).setPosition(location);
            }
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : mMarkers.values()) {
                builder.include(marker.getPosition());
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
        }
        if (DisplayActivity.j == 2) {
            LatLng locations = new LatLng(la, lo);
            if (!mMarkers.containsKey(key)) {
                mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key).position(locations)));
            } else {
                mMarkers.get(key).setPosition(locations);
            }
            LatLngBounds.Builder builders = new LatLngBounds.Builder();
            for (Marker marker : mMarkers.values()) {
                builders.include(marker.getPosition());
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builders.build(), 300));

        }

    }

}
