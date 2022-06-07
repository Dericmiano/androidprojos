package com.example.lessonclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button button;
    private TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        textView2 = findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setVisibility(View.VISIBLE);

                String  name = editText.getText().toString().trim();
                if (name.isEmpty()){
                    textView.setText("Its empty");
                }else {
                    textView.setText(name);
                    Toast.makeText(MainActivity.this, "You clilcked the Button",
                            Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}