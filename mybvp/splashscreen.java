package com.example.mybvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.mybvp.Parent.Homepage_parent;
import com.example.mybvp.Student.Homepage_student;
import com.example.mybvp.Teacher.Attendance_list_teacher;
import com.example.mybvp.Teacher.attendance_history_teacher;
import com.example.mybvp.Utils.PreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splashscreen);

        // on below line we are calling handler to run a task
        // for specific time interval

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String type = PreferenceUtils.getType(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentUser == null)
                {
                    proceedToMainActivity();
                }
                else{
                    if(type == null){
                        proceedToMainActivity();
                    }else{
                        determineType(type);
                    }
                }
            }
        }, 3000);
    }

    private void determineType(String type)
    {
        if(type.equals("student")){
            Intent i =new Intent(this, Homepage_student.class);
            startActivity(i);
        }else if(type.equals("parent")){
            Intent i =new Intent(this, Homepage_parent.class);
            startActivity(i);
        }else{
            Intent i =new Intent(this, Attendance_list_teacher.class);
            startActivity(i);
        }
    }


    private void proceedToMainActivity(){
        Intent i = new Intent(splashscreen.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}