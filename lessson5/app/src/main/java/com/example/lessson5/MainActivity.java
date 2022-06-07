package com.example.lessson5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public CalendarView calendarView;
    Calendar calendar = Calendar.getInstance();
    public static Date date;
    public  Button nextButton;
    public static EditText nameEdittext;
    public static EditText emailEdittext;
    private String[] status = {"full time", "part time", "Student","other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        nextButton = findViewById(R.id.next_button);
        nameEdittext = findViewById(R.id.nameTextEntry);
        emailEdittext = findViewById(R.id.emailTextEntry);


        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year, month, dayOfMonth);
            date = calendar.getTime();
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nameEdittext.getText().toString().trim().isEmpty()
                        && !emailEdittext.getText().toString().trim().isEmpty()){
                    startActivity(new Intent(MainActivity.this, ConfirmActivity.class));
                }else {
                    Toast.makeText(MainActivity.this,
                            "You cant continue with an empty value", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

        Spinner sp = (Spinner) findViewById(R.id.spinner);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_list_item,status));


    }
}