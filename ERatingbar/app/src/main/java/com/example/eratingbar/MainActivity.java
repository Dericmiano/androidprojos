package com.example.eratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private RatingBar ratingBar;

    float myRating = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int rating1 = (int) rating;
                String message = null;

                myRating = ratingBar.getRating();

                switch (rating1){
                    case  1:
                        message = "poor";
                        break;
                    case 2:
                        message = "fair";
                        break;
                    case 3:
                        message = "Good";
                        break;
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();


            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mes = "";

                textView.setText(String.valueOf(myRating));
            }
        });
    }
}