package com.example.mybvp.Parent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.mybvp.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.w3c.dom.Text;

public class calendar_view_parent extends AppCompatActivity {
    TextView Present,Absent, subjectname;
    PieChart pieChart;
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView subjectname;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view_parent);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setEnabled(false);

        Present=findViewById(R.id.tvPresent);
        Absent=findViewById(R.id.tvAbsent);
        pieChart=findViewById(R.id.piechart_parent);
        subjectname=findViewById(R.id.tvSubjectName);

        Intent i =getIntent();
        String subject=i.getStringExtra("key");
        subjectname.setText(subject);

        setdata();
    }

    public void setdata(){

        int present_days=0;
        int absent_days=(1-present_days)*100;
        int present_per=46;
        int absent_per=100-present_per;
        Present.setText("P: "+Integer.toString(present_per)+"%");
        Absent.setText("A: "+Integer.toString(absent_per)+"%");

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