package com.jcupzz.mycoviddiary;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class AddPlacesManually extends AppCompatActivity {
    String place_details;
    String day;
    String time;
    Button save_btn;
    Button set_time_btn, set_date_btn;
    TimePicker timePicker;
    EditText place_details_et;
    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places_manually);

        place_details_et = findViewById(R.id.place_et_id);
        save_btn = findViewById(R.id.save_btn_id);
        set_time_btn = findViewById(R.id.set_time_btn_id);
        set_date_btn = findViewById(R.id.set_date_btn_id);

        Calendar calendar = Calendar.getInstance();

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day_calender = calendar.get(Calendar.DAY_OF_MONTH);


        //set time btn
        set_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddPlacesManually.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        Time time = new Time(hourOfDay, minute, 0);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
                        String set_time = simpleDateFormat.format(time);

                        set_time_btn.setTextSize(55);
                        set_time_btn.setTextColor(getResources().getColor( R.color.colorPrimaryDark));
                        set_time_btn.setText("at "+set_time);
                        set_time_btn.setPadding(0,0,0,0);
                        set_time_btn.setBackgroundColor(getResources().getColor(R.color.background));
                        set_time_btn.setTypeface(null, Typeface.BOLD);

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
                            
                            Date myDate = inFormat.parse(dayOfMonth+"-"+month+"-"+year);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                            String dayName=simpleDateFormat.format(myDate);
                            String setdate = dayName+","+"\n"+dayOfMonth+"th"+" of "+MONTHS[month];


                            set_date_btn.setTextSize(60);
                            set_date_btn.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            set_date_btn.setText(setdate);
                            set_date_btn.setBackgroundColor(getResources().getColor(R.color.background));
                            set_date_btn.setPadding(0,0,0,0);
                            set_date_btn.setTypeface(null, Typeface.BOLD);

                        }
                        catch (ParseException e) {

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
                if (!(place_details.isEmpty()) && day != null) {
                } else {
                    Toasty.error(getApplicationContext(), "Please complete the above details", Toast.LENGTH_SHORT, true).show();
                }


            }
        });

    }

}