package com.example.mybvp.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.mybvp.Adapter.AttendanceAdapter;
import com.example.mybvp.Adapter.TeacherAdapter;
import com.example.mybvp.Model.AttendanceModel;
import com.example.mybvp.Model.TeacherModel;
import com.example.mybvp.R;
import com.example.mybvp.Teacher.Attendance_list_teacher;
import com.example.mybvp.Teacher.attendance_table_teacher;
import com.example.mybvp.Teacher.mark_attendance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Attendance_list_student extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    ShapeableImageView backImage;

    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList<TeacherModel> mTeacherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list_student);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Teacherinfo");

        mTeacherModel = new ArrayList<>();
        backImage = findViewById(R.id.ivBack);
        // Setup RecyclerView adapter and layoutManager

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        configData();
    }

    private void configData(){
        myRef.addValueEventListener(new ValueEventListener() {

            int counter = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTeacherModel.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    TeacherModel model = snapshot.getValue(TeacherModel.class);

                    mTeacherModel.add(model);
                }

                TeacherAdapter teacherAdapter = new TeacherAdapter(Attendance_list_student.this, mTeacherModel);
                recyclerView.setAdapter(teacherAdapter);
                teacherAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}