package com.example.lessson5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {
    private TextView enteredName;
    private TextView enteredEmail;
    private TextView enteredDate;
    private Button nextButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        enteredName = findViewById(R.id.entereredname);
        enteredEmail = findViewById(R.id.enteredEmail);
        enteredDate = findViewById(R.id.enteredDate);
        nextButton = findViewById(R.id.nextbutton2);
        backButton = findViewById(R.id.backbutton2);

        String name = MainActivity.nameEdittext.getText().toString();
        String email= MainActivity.emailEdittext.getText().toString();
//        Integer date = Math.toIntExact(MainActivity.date.getTime());
        Log.d("TAG", "onCreate: ");

        enteredName.setText(name);
        enteredEmail.setText(email);
//        enteredDate.setText(date);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfirmActivity.this, MainActivity.class));
            }
        });
    }
}