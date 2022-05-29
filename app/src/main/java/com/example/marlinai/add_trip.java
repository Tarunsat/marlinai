package com.example.marlinai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class add_trip extends AppCompatActivity {
    CalendarView calendar;
    CalendarView calendar2;
    CheckBox a;
     static String f1="";
     static String f2="";
    Global sharedData = Global.getInstance();

    private EditText e;
    private Button sendDatabtn,backbtn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    // creating a variable for
    // our object class
    ImageUploadInfo employeeInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        calendar = findViewById(R.id.flyincalendar);
        a = findViewById(R.id.active);
        e= (EditText) findViewById(R.id.trip);
        employeeInfo = new ImageUploadInfo();



       firebaseDatabase = FirebaseDatabase.getInstance();
//
//        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("db");





        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                f1 = dayOfMonth + "-" + (month + 1) + "-" + year;

            }
        });



        calendar2 = findViewById(R.id.flyoutcalendar);
        if(a.isChecked())
        {
            f2 = "active";
            calendar2.setEnabled(false);


        }
        else {


            calendar2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    f2 = dayOfMonth + "-" + (month + 1) + "-" + year;

                }
            });
        }
        sendDatabtn = findViewById(R.id.submita);
        backbtn = findViewById(R.id.backa);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String name = e.getText().toString();
                String phone = f1;
                String address = f2;

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(add_trip.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference tasksRef = rootRef.child("db").push();
                    addDatatoFirebase(name, phone, address,tasksRef);
                }
                startActivity(new Intent(add_trip.this, MainActivity.class));
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(add_trip.this, MainActivity.class));
            }
        });

    }



    private void addDatatoFirebase(String fname, String f1, String f2, DatabaseReference d) {
        // below 3 lines of code is used to set
        // data in our object class.
        employeeInfo.setTripname(fname);
        employeeInfo.setDate1(f1);
        employeeInfo.setDate2(f2);


        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.

                d.setValue(employeeInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(add_trip.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(add_trip.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }






}

