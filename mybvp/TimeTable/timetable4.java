package com.example.mybvp.TimeTable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mybvp.R;
import com.example.mybvp.Timetable_list;

public class timetable4 extends AppCompatActivity {
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable4);
        back=findViewById(R.id.ivBack);

        back.setOnClickListener((v)->{
            Intent i = new Intent(this, Timetable_list.class);
            startActivity(i);
        });

    }
}