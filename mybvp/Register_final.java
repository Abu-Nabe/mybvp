package com.example.mybvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_final extends AppCompatActivity {

    EditText phone, email, pass;
    Button btn_signup;
    ImageView back;
    ProgressBar bar;
    String key1, name, subject, checkd_option,roll, enrol;

    FirebaseDatabase database ;
    DatabaseReference Sinfo, Tinfo, Pinfo;
    MyBvpData sdata,tdata,pdata;
    private FirebaseAuth mAuth;

    private static final String TAG = "FBMailAuthentication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_final);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        Sinfo = database.getReference("Studentinfo");
        Tinfo = database.getReference("Teacherinfo");
        Pinfo = database.getReference("Parentinfo");

        sdata = new MyBvpData();
        tdata= new MyBvpData();
        pdata= new MyBvpData();



        phone = findViewById(R.id.etphone);
        email = findViewById(R.id.etmail);
        pass = findViewById(R.id.etPass);

        btn_signup = findViewById(R.id.btn_signup);
        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        back = findViewById(R.id.ivBack);
        bar = findViewById(R.id.progressbarRegsiterFinal);

        Intent intent = getIntent();
        if (!(intent == null)) {
            key1 = intent.getStringExtra("key");
        }

        back.setOnClickListener((v) -> {
            switch (key1) {
                case "parent":
                    Intent i = new Intent(this, RegisterParent.class);
                    startActivity(i);
                    break;
                case "teacher":
                    Intent j = new Intent(this, RegisterTeacher.class);
                    startActivity(j);
                    break;
                case "student":
                    Intent k = new Intent(this, RegisterStudent.class);
                    startActivity(k);
                    break;
                default:
                    Toast.makeText(this, "There was some error!try again later!", Toast.LENGTH_SHORT).show();
                    Intent l = new Intent(this, LoginPage.class);
                    startActivity(l);
            }

        });

        btn_signup.setOnClickListener((v) -> {

            bar.setVisibility(View.VISIBLE);
            if (phone.getText().toString().equals(null)) {
                phone.setError("Email is empty");
                phone.requestFocus();
            } else if (pass.getText().toString().equals(null)) {
                pass.setError("Email is empty");
                pass.requestFocus();
            } else if (email.getText().toString().equals(null)) {
                email.setError("Email is empty");
                email.requestFocus();
            } else {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(task -> {
                            bar.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                Snackbar.make(v,
                                                "Registration successful!",
                                                Snackbar.LENGTH_SHORT)
                                        .show();
                                // hide the progress bar

                                // if the user created intent to login activity
                                Intent i
                                        = new Intent(this,
                                        LoginPage.class);
                                i.putExtra("mail", email.getText().toString());
                                i.putExtra("pass", pass.getText().toString());
                                i.putExtra("key", key1);
                                startActivity(i);

                                String uid = mAuth.getCurrentUser().getUid();
                                switch (key1) {
                                    case "parent":
                                        if (!(intent == null)) {

                                            name = intent.getStringExtra("Pname");
                                            checkd_option = intent.getStringExtra("Pcheck");
                                            roll = intent.getStringExtra("Proll");
                                            enrol = intent.getStringExtra("Penrol");

                                        }
                                        addParentdata(uid);
                                        break;

                                    case "teacher":
                                        if (!(intent == null)) {
                                            name = intent.getStringExtra("Tname");
                                            checkd_option = intent.getStringExtra("Tcheck");
                                            subject = intent.getStringExtra("Tsubject");
                                        }
                                        addTeacherdata(uid);
                                        break;
                                    case "student":
                                        if (!(intent == null)) {

                                            name = intent.getStringExtra("Sname");
                                            checkd_option = intent.getStringExtra("Scheck");
                                            roll = intent.getStringExtra("Sroll");
                                            enrol = intent.getStringExtra("Senrol");
                                        }
                                        addStudentdata(uid);
                                        break;

                                    default:
                                        Toast.makeText(this, "Data retrieval Problem: Try Again!", Toast.LENGTH_SHORT).show();
                                }

                            } else {

                                // Registration failed
                                Snackbar.make(
                                                v,
                                                "Registration failed!!"
                                                        + task.getException().getMessage(),
                                                Snackbar.LENGTH_SHORT)
                                        .show();


                                bar.setVisibility(View.GONE);
                            }

                        });

            }//else loop


        });//btn_signup


    }//on create

    void addParentdata(String uid){
        Pinfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.

                DatabaseReference p_info=Pinfo.child(uid);
                p_info.child("enrollment").setValue(enrol);
                p_info.child("roll").setValue(roll);
                p_info.child("name").setValue(name);
                p_info.child("phone").setValue(phone.getText().toString());
                p_info.child("mail").setValue(email.getText().toString());
                p_info.child("password").setValue(pass.getText().toString());
                p_info.child("Course").setValue(checkd_option);
                p_info.child("uid").setValue(uid);

                // after adding this data we are showing toast message.
                Toast.makeText(Register_final.this, "Parent's data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(Register_final.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }

    void addTeacherdata(String uid){

        Tinfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.

                DatabaseReference t_info=Tinfo.child(uid);
                t_info.child("mail").setValue(email.getText().toString());;
                t_info.child("name").setValue(name);
                t_info.child("phone").setValue(phone.getText().toString());
                t_info.child("password").setValue(pass.getText().toString());
                t_info.child("classname").setValue(checkd_option);
                t_info.child("subject").setValue(subject);
                t_info.child("id").setValue(uid);

                // after adding this data we are showing toast message.
                Toast.makeText(Register_final.this, "Teacher's data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(Register_final.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }
    void addStudentdata(String uid){

        Sinfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.

                DatabaseReference s_info= Sinfo.child(uid);
                s_info.child("enrollment").setValue(enrol);
                s_info.child("roll_no").setValue(roll);
                s_info.child("name").setValue(name);
                s_info.child("phone").setValue(phone.getText().toString());
                s_info.child("mail").setValue(email.getText().toString());
                s_info.child("password").setValue(pass.getText().toString());
                s_info.child("Course").setValue(checkd_option);


                // after adding this data we are showing toast message.
                Toast.makeText(Register_final.this, "Student's data added", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(Register_final.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }


}//main class