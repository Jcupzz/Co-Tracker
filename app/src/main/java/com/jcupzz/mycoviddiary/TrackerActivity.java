package com.jcupzz.mycoviddiary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import es.dmoral.toasty.Toasty;

public class TrackerActivity extends Activity {

//    private static final int PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//
//        int permission = ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION);
//        if (permission == PackageManager.PERMISSION_GRANTED) {
//            startTrackerService();
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_REQUEST);
//        }
//
//        //location service request start:-
//
//
//        //-:end
//
//    }
//
//    private void startTrackerService() {
//        startService(new Intent(this, TrackerService.class));
//        finish();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
//            grantResults) {
//        if (requestCode == PERMISSIONS_REQUEST && grantResults.length == 1
//                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            // Start the service when the permission is granted
//            startTrackerService();
//        } else {
//            finish();
//        }
    }



}
