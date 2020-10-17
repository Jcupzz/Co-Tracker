package com.jcupzz.mycoviddiary;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class AddPlacesManually extends AppCompatActivity {
    String place_details;
    String dayName;
    String set_time;
    String setdate;
    Button save_btn;
    SharedPreferences shared;
    Button set_time_btn, set_date_btn;
    TimePicker timePicker;
    EditText place_details_et;
    FirebaseFirestore db;
    TextView place_tv, date_tv, time_tv,preview_tv;
    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places_manually);

        place_details_et = findViewById(R.id.place_et_id);
        save_btn = findViewById(R.id.save_btn_id);
        set_time_btn = findViewById(R.id.set_time_btn_id);
        set_date_btn = findViewById(R.id.set_date_btn_id);
        place_tv = findViewById(R.id.place_tv_id);
        date_tv = findViewById(R.id.date_tv_id);
        time_tv = findViewById(R.id.time_tv_id);
        preview_tv = findViewById(R.id.preview_tv_id);

        Calendar calendar = Calendar.getInstance();

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day_calender = calendar.get(Calendar.DAY_OF_MONTH);

        place_details_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                preview_tv.setVisibility(View.GONE);
                place_tv.setVisibility(View.VISIBLE);
                place_tv.setTextSize(30);
                place_tv.setText("I went to " + place_details_et.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //set time btn
        set_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddPlacesManually.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        Time time = new Time(hourOfDay, minute, 0);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
                        set_time = simpleDateFormat.format(time);

                        preview_tv.setVisibility(View.GONE);
                        time_tv.setVisibility(View.VISIBLE);
                        time_tv.setTextSize(30);
                        time_tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        time_tv.setText("at " + set_time);
//                        time_tv.setPadding(0, 0, 0, 0);
//                        time_tv.setBackgroundColor(getResources().getColor(R.color.background));
                        time_tv.setTypeface(null, Typeface.BOLD);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        //set date btn
        set_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPlacesManually.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");

                        try {

                            Date myDate = inFormat.parse(dayOfMonth + "-" + month + "-" + year);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                             dayName = simpleDateFormat.format(myDate);
                             setdate = "On"+  "\n" + dayName + "," + "\n" + dayOfMonth + "th" + " of " + MONTHS[month];

                            preview_tv.setVisibility(View.GONE);
                            date_tv.setVisibility(View.VISIBLE);
                            date_tv.setTextSize(30);
                            date_tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            date_tv.setText(setdate);
//                            date_tv.setBackgroundColor(getResources().getColor(R.color.background));
//                            date_tv.setPadding(0, 0, 0, 0);
                            date_tv.setTypeface(null, Typeface.BOLD);

                        } catch (ParseException e) {

                            e.printStackTrace();

                        }


                    }
                }, year, month, day_calender);
                datePickerDialog.show();
            }
        });


        //save btn
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place_details = place_details_et.getText().toString();
                if (!(place_details.isEmpty()) && dayName!=null && set_time!=null) {
                    String uploadcontent = setdate+"\n"+"at " + set_time+"\n"+"I went to "+place_details;
                    uploadToFirebase(uploadcontent);
                    Intent intent = new Intent(AddPlacesManually.this,AddPlacesMain.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toasty.error(getApplicationContext(), "Please complete the above details", Toast.LENGTH_SHORT, true).show();
                }


            }
        });

    }

    private void uploadToFirebase(String uploadcontent) {

        shared = getSharedPreferences("email_save", MODE_PRIVATE);
        String email_id = (shared.getString("email", ""));
        AddPlacesManually_Models data = new AddPlacesManually_Models(uploadcontent);
        db = FirebaseFirestore.getInstance();
        db.collection(email_id).document("addplaces").collection("userplaces").document().set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toasty.success(getApplicationContext(),"Successfully Uploaded",Toasty.LENGTH_SHORT,true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(getApplicationContext(),"Failed to upload!",Toasty.LENGTH_SHORT,true);
            }
        });

    }

}