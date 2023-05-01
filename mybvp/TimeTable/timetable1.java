package com.example.mybvp.TimeTable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mybvp.R;
import com.example.mybvp.Timetable_list;

public class timetable1 extends AppCompatActivity {
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable1);

        back=findViewById(R.id.ivBack);

        back.setOnClickListener((v)->{
            Intent i = new Intent(this, Timetable_list.class);
            startActivity(i);
        });

    }


}