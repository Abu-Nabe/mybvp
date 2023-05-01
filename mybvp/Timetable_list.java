package com.example.mybvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mybvp.Parent.Homepage_parent;
import com.example.mybvp.TimeTable.timetable1;
import com.example.mybvp.TimeTable.timetable2;
import com.example.mybvp.TimeTable.timetable3;
import com.example.mybvp.TimeTable.timetable4;
import com.example.mybvp.TimeTable.timetable5;
import com.example.mybvp.TimeTable.timetable6;

public class Timetable_list extends AppCompatActivity {

    Button Tyco1,Tyco2,Syco1,Syco2,Fyco1,Fyco2;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_list);

        Tyco1=findViewById(R.id.btntyco1);
        Tyco2=findViewById(R.id.btntyco2);
        Syco1=findViewById(R.id.btnsyco1);
        Syco2=findViewById(R.id.btnsyco2);
        Fyco1=findViewById(R.id.btnfyco1);
        Fyco2=findViewById(R.id.btnfyco2);
        back=findViewById(R.id.ivBack);

        back.setOnClickListener((v)->{
            Intent i =new Intent(this, Homepage_parent.class);
            startActivity(i);
        });

        Tyco1.setOnClickListener((v)->{
            Intent i =new Intent(this, timetable1.class);
            startActivity(i);
        });
        Tyco2.setOnClickListener((v)->{
            Intent i =new Intent(this,  timetable2.class);
            startActivity(i);
        });
        Syco1.setOnClickListener((v)->{
            Intent i =new Intent(this,  timetable3.class);
            startActivity(i);
        });
        Syco2.setOnClickListener((v)->{
            Intent i =new Intent(this, timetable4.class);
            startActivity(i);
        });
        Fyco1.setOnClickListener((v)->{
            Intent i =new Intent(this, timetable5.class);
            startActivity(i);
        });
        Fyco2.setOnClickListener((v)->{
            Intent i =new Intent(this, timetable6.class);
            startActivity(i);
        });

    }
}