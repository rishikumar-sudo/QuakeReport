package com.example.android.quakereport;
/*
Code Is Written By  Rishi Kumar  Date :20/12/2019
 */
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button btnDatePicker, btnEndDatePicker,btnData;
    EditText txtDate, txtEndDate;
    String string="";
    String string1="";
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int diff = 0;
        setContentView(R.layout.activity_main);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnEndDatePicker = (Button) findViewById(R.id.btn_time);
        txtDate = (EditText) findViewById(R.id.in_date);
        txtEndDate = (EditText) findViewById(R.id.in_time);

            btnDatePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    date();
                    txtDate.setText(string);
                }
            });

            btnEndDatePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    date();
                    txtEndDate.setText(string);
                }
            });
            string = String.valueOf(txtDate.getText());
            string1 = String.valueOf(txtEndDate.getText());




    }
    public  long getDateDiff( String oldDate, String newDate) {
        long diff;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date d = null;
        Date d2=null;
        try {
            d = formatter.parse(oldDate);//catch exception
            d2 = formatter.parse(newDate);//catch exception
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        diff =( d2.getTime()-d.getTime()) / (24 * 60 * 60 * 1000);
return diff;
    }
    public void callSecondActivity(View view){

            Intent i = new Intent(getApplicationContext(), EarthquakeActivity.class);
            i.putExtra("Value1", string);
            i.putExtra("Value2", string1);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Data Lodaing",
                    Toast.LENGTH_SHORT);

            toast.show();
            // Set the request code to any code you like, you can identify the
            // callback via this code
            startActivity(i);

    }



    public void date()
    {
        Calendar c=Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                string=year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;


            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    }
