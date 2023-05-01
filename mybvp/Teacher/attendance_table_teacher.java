package com.example.mybvp.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.example.mybvp.Adapter.AttendanceAdapter;
import com.example.mybvp.Adapter.StudentAdapter;
import com.example.mybvp.Model.AttendanceModel;
import com.example.mybvp.Model.StudentModel;
import com.example.mybvp.R;
import com.example.mybvp.Register_final;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class attendance_table_teacher extends AppCompatActivity {
    FirebaseDatabase database ;
    DatabaseReference Ainfo;
    String key1;

    TextView subject,today;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StudentModel> mStudentModel;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_table_teacher);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mStudentModel = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        subject = findViewById(R.id.tvSubject_teacher_attendance);
        today = findViewById(R.id.tvDate_teacher_attendance);

        Button add = findViewById(R.id.btnAdd);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        Format f = new SimpleDateFormat("EEEE");
        String day = f.format(new Date());

        key1 = getIntent().getStringExtra("key");
        myRef = database.getReference("Attendance").child(key1);

        today.setText(d + "/" + month + "/" + year + "," + day);
        subject.setText(key1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(attendance_table_teacher.this, mark_attendance.class);
                intent.putExtra("key", key1);
                intent.putExtra("date", today.getText().toString());
                startActivity(intent);
            }
        });

        configData();
    }
    private void configData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mStudentModel.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    StudentModel model = snapshot.getValue(StudentModel.class);

                    mStudentModel.add(model);
                }
                StudentAdapter studentAdapter = new StudentAdapter(attendance_table_teacher.this,  mStudentModel, key1, today.getText().toString());
                recyclerView.setAdapter(studentAdapter);
                studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}