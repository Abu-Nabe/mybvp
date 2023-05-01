package com.example.mybvp.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybvp.Adapter.AttendanceAdapter;
import com.example.mybvp.Model.AttendanceModel;
import com.example.mybvp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class mark_attendance extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private RecyclerView.LayoutManager layoutManager;
    ShapeableImageView backImage;

    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList<AttendanceModel> mAttendanceModel;
    String date;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Studentinfo");

        mAttendanceModel = new ArrayList<>();
        fab = findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        backImage = findViewById(R.id.ivBack);
        // Setup RecyclerView adapter and layoutManager

        date = getIntent().getStringExtra("date");
        key = getIntent().getStringExtra("key");

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
                mAttendanceModel.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    AttendanceModel model = snapshot.getValue(AttendanceModel.class);

                    mAttendanceModel.add(model);
                }

                AttendanceAdapter attendanceAdapter = new AttendanceAdapter(mark_attendance.this, mAttendanceModel, mark_attendance.this, date, key);
                recyclerView.setAdapter(attendanceAdapter);
                attendanceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
