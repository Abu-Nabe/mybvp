package com.example.mybvp.Student;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mybvp.Adapter.StudentAdapter;
import com.example.mybvp.Model.StudentModel;
import com.example.mybvp.MyBvpData;
import com.example.mybvp.R;
import com.example.mybvp.Teacher.attendance_table_teacher;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashSet;



public class calendar_view_student extends AppCompatActivity {

    TextView Present, Absent,subjectname;
    MaterialTextView subject_name;
    PieChart pieChart;
    CalendarView calendarView;
     int color,present,absent;
     String present_db;

    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList<StudentModel> mStudentModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view_student);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setClickable(false);
        calendarView.setEnabled(false);
        calendarView.setFocusable(false);

        Present = findViewById(R.id.tvPresent);
        Absent = findViewById(R.id.tvAbsent);
        pieChart = findViewById(R.id.piechart_student);

        subjectname=findViewById(R.id.tvSubjectName);
        subject_name = findViewById(R.id.subject_name);

        Intent i =getIntent();
        String subject=i.getStringExtra("key");
        subjectname.setText(subject);
        subject_name.setText(i.getStringExtra("subject"));

        String classname = subject.replace("-", "");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Attendance").child(classname.toLowerCase());

        configDate();

    }

    private void configDate()
    {
        mStudentModel = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mStudentModel.clear();
                int present_per = 0;
                int absent_per = 0;

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    StudentModel model = snapshot.getValue(StudentModel.class);

                    mStudentModel.add(model);
                }

                for(int i = 0; i < mStudentModel.size(); i++){
                    if(mStudentModel.get(i).getType().equals("Present")){
                        present_per = present_per + 1;
                    }else{
                        absent_per = absent_per + 1;
                    }
                }
                setdata(present_per, absent_per);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setdata(int present_per, int absent_per) {

//        int present_per = 46;
//        int absent_per = 100 - present_per;
        Present.setText("P: " + present_per);
        Absent.setText("A: " + absent_per);

        pieChart.addPieSlice(
                new PieModel(
                        "P",
                        present_per,
                        Color.parseColor("#40ff00")));
        pieChart.addPieSlice(
                new PieModel(
                        "A",
                        absent_per,
                        Color.parseColor("#ff0000")));

        pieChart.startAnimation();

    }


}