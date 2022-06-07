package com.example.calculatorassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText num1;
    private EditText num2;
    private ImageView productButton;
    private ImageView subtractButton;
    private ImageView divisionButton;
    private ImageView addButton;
    private TextView yourMaths;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        productButton = findViewById(R.id.productButton);
        subtractButton = findViewById(R.id.subButton);
        divisionButton = findViewById(R.id.divisionButton);
        addButton = findViewById(R.id.addButton);
        yourMaths = findViewById(R.id.mathstextview);
        answer = findViewById(R.id.answer);

        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1.getText().toString().trim().isEmpty() &&
                        !num2.getText().toString().trim().isEmpty()){
                    int number1 = Integer.parseInt(num1.getText().toString());
                    int number2 = Integer.parseInt(num2.getText().toString());
                    int result = number1 * number2;
                    answer.setText(String.valueOf(result));
                    Log.d("TAG", "onClick: "+result);

                    String maths = "multiplication of "+num2.getText().toString()+
                            " and "+num1.getText().toString() ;
                    yourMaths.setText(maths);
                    Toast.makeText(MainActivity.this, String.valueOf(result), Toast.LENGTH_LONG).show();
                }else {
                    errorMessage();
                }
                hideSoftKeyboard(v);

            }
        });
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!num1.getText().toString().trim().isEmpty() &&
                        !num2.getText().toString().trim().isEmpty()) {
                    int number1 = Integer.parseInt(num1.getText().toString());
                    int number2 = Integer.parseInt(num2.getText().toString());
                        int result;
                        if (number2 > number1) {
                            result = number2 - number1;
                        } else {
                            result = number1 - number2;
                        }
                        answer.setText(String.valueOf(result));
                        String yourMath = " subtraction of "
                                + num1.getText().toString() +
                                " from " + num2.getText().toString();
                        yourMaths.setText(yourMath);
                    Toast.makeText(MainActivity.this, String.valueOf(result), Toast.LENGTH_LONG).show();

                }else {
                   errorMessage();
                }

                hideSoftKeyboard(v);

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num1.getText().toString().trim().isEmpty() &&
                        !num2.getText().toString().trim().isEmpty()){
                    int number1 = Integer.parseInt(num2.getText().toString());
                    int number2 = Integer.parseInt(num1.getText().toString());
                    int result;
                    result = number2 + number1;
                    answer.setText(String.valueOf(result));
                    String maths = "added "+num1.getText().toString()+
                            " to "+num2.getText().toString();
                    yourMaths.setText(maths);
                    Toast.makeText(MainActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();

                }else {
                    errorMessage();
                }
                hideSoftKeyboard(v);
            }
        });
        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!num1.getText().toString().trim().isEmpty() &&
                        !num2.getText().toString().trim().isEmpty()){
                    int number1  = Integer.parseInt(num1.getText().toString());
                    int number2 = Integer.parseInt(num2.getText().toString());
                    int result;
                    result = number1 / number2;
                    answer.setText(String.valueOf(result));
                    String actions = "Divided "+num1.getText().toString()+
                            " by " +num2.getText().toString();
                    yourMaths.setText(actions);
                    Toast.makeText(MainActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();

                }else {
                    errorMessage();
                }
                hideSoftKeyboard(v);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityTwo.class));
            }
        });


    }
    private void errorDatatype(){
        String warning = "please enter integers";
        yourMaths.setText(warning);
    }


    private void errorMessage() {
        String warning = "please enter two values";
        yourMaths.setText(warning);
    }
    public static void  hideSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}