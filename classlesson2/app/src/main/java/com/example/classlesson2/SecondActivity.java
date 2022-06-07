package com.example.classlesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {
   private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = (ListView) findViewById(R.id.listview);
        String [] values = {"java","c++","python","Scala" };

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item, Arrays.asList(values));
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(getApplicationContext(), "you clicked" +position+" "
                                +((TextView)view).getText(),
                        Toast.LENGTH_SHORT).show();



            }
        });
    }
}