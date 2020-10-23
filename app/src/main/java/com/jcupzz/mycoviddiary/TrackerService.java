package com.jcupzz.mycoviddiary;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class TrackerService extends Service {

    private static final String TAG = TrackerService.class.getSimpleName();
    FirebaseFirestore db, live_db;
    SharedPreferences shared;
    SharedPreferences uid_sharedprefs;
    String address,uid;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();


        db = FirebaseFirestore.getInstance();
        live_db = FirebaseFirestore.getInstance();
        buildNotification();
        loginToFirebase();
    }

    private void buildNotification() {
        String stop = "stop";
        registerReceiver(stopReceiver, new IntentFilter(stop));
        PendingIntent broadcastIntent = PendingIntent.getBroadcast(
                this, 0, new Intent(stop), PendingIntent.FLAG_UPDATE_CURRENT);
        // Create the persistent notification

        String channel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            channel = createChannel();
        else {
            channel = "";
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.notification_text))
                .setOngoing(true)
                .setContentIntent(broadcastIntent)
                .setSmallIcon(R.drawable.ic_tracker);
        startForeground(1, builder.build());
    }

    protected BroadcastReceiver stopReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "received stop broadcast");
            // Stop the service when the notification is tapped
            unregisterReceiver(stopReceiver);
            stopSelf();
        }
    };

    private void loginToFirebase() {

        requestLocationUpdates();


    }

    private void requestLocationUpdates() {

        LocationRequest request = new LocationRequest();
        request.setInterval(600000);//900000
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);


        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            // Request location updates and when an update is
            // received, store the location in Firebase
            client.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {


                    uid_sharedprefs = getSharedPreferences("uid_save", MODE_PRIVATE);
                    uid = (uid_sharedprefs.getString("uid", "uid_shareprefs_crashed"));



                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(uid);
                    Location location = locationResult.getLastLocation();
                    if (location != null) {
                        Log.d(TAG, "location update " + location);
                        ref.setValue(location);

                        String lati = String.valueOf(location.getLatitude());
                        String longi = String.valueOf(location.getLongitude());

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());

                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            Address obj = addresses.get(0);
                            address = obj.getAddressLine(0)+"\n";
                            address = address + "\n" + "Locality: " + obj.getLocality()+"\n";
                            address = address + "\n" + "District: " +obj.getSubAdminArea()+"\n";
                            address = address + "\n" + "State: " +obj.getAdminArea()+"\n";
                            address = address + "\n" + "PostalCode: " +obj.getPostalCode()+"\n";
                            address = address + "\n" + "Country: " +obj.getCountryName();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (address == null || address.length() == 0) {
                            address = "Address not found";
                        }


                        Lati_Longi_Models lati_longi_models = new Lati_Longi_Models(Timestamp.now(), day, date, lati, longi, address);

                        shared = getSharedPreferences("email_save", MODE_PRIVATE);
                        String email_id = (shared.getString("email", ""));


                        //     String rand = randomString(6);

                        db.collection(email_id).document(String.valueOf(Timestamp.now()))
                                .set(lati_longi_models).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Toasty.success(getApplicationContext(),"Location Updated",Toasty.LENGTH_SHORT,true).show();
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toasty.error(getApplicationContext(), "Error!", Toasty.LENGTH_SHORT, true).show();
                                    }
                                });

                    }
                }
            }, null);
        }


    }

    @NonNull
    @TargetApi(26)
    private synchronized String createChannel() {
        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        String name = "snap map fake location ";
        int importance = NotificationManager.IMPORTANCE_LOW;

        NotificationChannel mChannel = new NotificationChannel("snap map channel", name, importance);

        mChannel.enableLights(true);
        mChannel.setLightColor(Color.BLUE);
        if (mNotificationManager != null) {
            mNotificationManager.createNotificationChannel(mChannel);
        } else {
            stopSelf();
        }
        return "snap map channel";
    }
 /*   public static final String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz|!£$%&/=@#";
    public static Random RANDOM = new Random();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
        }

        return sb.toString();
    }*/
}
