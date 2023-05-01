package com.example.mybvp.Student;

import static com.example.mybvp.R.id;
import static com.example.mybvp.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mybvp.MainActivity;
import com.example.mybvp.Parent.Attendance_list_parent;
import com.example.mybvp.Timetable_list;
import com.example.mybvp.Utils.PreferenceUtils;
import com.example.mybvp.aboutus;
import com.example.mybvp.activities;
import com.example.mybvp.contactUs;
import com.example.mybvp.facilities;
import com.example.mybvp.feedback;
import com.example.mybvp.rules;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class Homepage_student extends AppCompatActivity {

    TextView txt_Timetable,txt_Attendance,txt_Acadamic_AC,txt_RulnReg,txt_Collgeg_AC,txt_Contact_Us,txt_About_us,txt_Feedback;
    ImageView iv_Timetable,iv_Attendance,iv_Acadamic_AC,iv_RulnReg,iv_Collgeg_AC,iv_Contact_Us,iv_About_us,iv_Feedback;
    FirebaseAuth mAuth;
    Button btn_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_homepage_student);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        mAuth=FirebaseAuth.getInstance();

        btn_Logout=findViewById(id.btn_Logout);

        txt_Timetable=findViewById(id.tvTimetable);
        txt_Acadamic_AC=findViewById(id.tvAcademic_Ac);
        txt_Feedback=findViewById(id.tvFeedback_title);
        txt_Collgeg_AC=findViewById(id.tvCollege_facilities);
        txt_Contact_Us=findViewById(id.tvContact_Us);
        txt_Attendance=findViewById(id.tvAttendance);
        txt_RulnReg=findViewById(id.tvRulesnReg);
        txt_About_us=findViewById(id.tvAbout_us);

        iv_Timetable=findViewById(id.ivTimetable_icon);
        iv_Acadamic_AC=findViewById(id.ivAcademic_Ac_icon);
        iv_Feedback=findViewById(id.ivfeedback_icon);
        iv_Collgeg_AC=findViewById(id.ivCollege_facilities_icon);
        iv_Contact_Us=findViewById(id.ivcontact_us_icon);
        iv_Attendance=findViewById(id.ivAttendance_icon);
        iv_RulnReg=findViewById(id.ivRulesnReg_icon);
        iv_About_us=findViewById(id.ivAbout_icon);


        //Redirects
        iv_Timetable.setOnClickListener((v)->{
            Intent i = new Intent(this, Timetable_list.class);
            startActivity(i);
        });
        iv_Attendance.setOnClickListener((v)->{
            Intent i = new Intent(this, Attendance_list_student.class);
            startActivity(i);
        });
        iv_Acadamic_AC.setOnClickListener((v)->{
            Intent i = new Intent(this, activities.class);
            startActivity(i);
        });
        iv_RulnReg.setOnClickListener((v)->{
            Intent i = new Intent(this, rules.class);
            startActivity(i);
        });
        iv_Collgeg_AC.setOnClickListener((v)->{
            Intent i = new Intent(this, facilities.class);
            startActivity(i);
        });
        iv_Contact_Us.setOnClickListener((v)->{
            Intent i = new Intent(this, contactUs.class);
            startActivity(i);
        });
        iv_About_us.setOnClickListener((v)->{
            Intent i = new Intent(this, aboutus.class);
            startActivity(i);
        });
        iv_Feedback.setOnClickListener((v)->{
            Intent i = new Intent(this, feedback.class);
            startActivity(i);
        });

        btn_Logout.setOnClickListener((v) -> {
            mAuth.signOut();
            Intent i = new Intent(this, MainActivity.class);
            Snackbar.make(v,"Logged out",Snackbar.LENGTH_SHORT).show();
            startActivity(i);

            PreferenceUtils.saveType(null, this);
            FirebaseAuth.getInstance().signOut();
        });


    }


}