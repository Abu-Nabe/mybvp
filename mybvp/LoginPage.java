package com.example.mybvp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybvp.Parent.Attendance_list_parent;
import com.example.mybvp.Parent.Homepage_parent;
import com.example.mybvp.Student.Homepage_student;
import com.example.mybvp.Teacher.Attendance_list_teacher;
import com.example.mybvp.Utils.PreferenceUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {
    TextView register,not;
    EditText mail, pass;
    Button submit;
    String key1;


    FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        register=findViewById(R.id.tvregister);
        not=findViewById(R.id.tvNOt);

        mail=findViewById(R.id.etEmail);
        pass=findViewById(R.id.etPass);
        submit=findViewById(R.id.btn_submit);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        mAuth=FirebaseAuth.getInstance();

        Intent intent= getIntent();
        if(!(intent==null)) {
            key1 = intent.getStringExtra("key");
            mail.setText(intent.getStringExtra("email"));
            pass.setText(intent.getStringExtra("pass"));

        }
            if(!(key1==null)) {
                not.setText("Not a " + key1 + "???");
                not.setOnClickListener((v) -> {
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                });
            }else{
                not.setText("Select a User!");
            }



        register.setOnClickListener((v)->{
            Snackbar.make(v, "user : "+key1+"", Snackbar.LENGTH_SHORT).show();

            switch (key1){
                case "parent":
                    Intent i = new Intent(LoginPage.this, RegisterParent.class);
                    startActivity(i);

                    break;
                case "teacher":
                    Intent j = new Intent(LoginPage.this, RegisterTeacher.class);
                    startActivity(j);

                    break;
                case "student":
                    Intent k = new Intent(LoginPage.this, RegisterStudent.class);
                    startActivity(k);

                    break;
                default:Snackbar.make(v,"error key: "+key1,Snackbar.LENGTH_SHORT).show();
            }

        });

            submit.setOnClickListener((v)-> {
                //check mail is empty
                if(mail.getText().toString().isEmpty())
                {
                    mail.setError("Email is empty");
                    mail.requestFocus();
                    return;
                }
                //check mail is valid
               else if(!Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches()) {
                    mail.setError("Enter the valid email");
                    mail.requestFocus();
                    return;
                }

                else if(pass.getText().toString().isEmpty())
                {
                    pass.setError("Password is empty");
                    pass.requestFocus();
                    return;
                }

                else {

                    switch (key1){
                        case "parent":

                            mAuth.signInWithEmailAndPassword(mail.getText().toString(), pass.getText().toString())
                                    .addOnCompleteListener(this, task -> {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "Logged In!");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            updateUIParent(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "LoginInWithEmail:failure!!", task.getException());
                                            Snackbar.make(v, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();

                                        }
// ...
                                    });


                            break;
                        case "teacher":
                            mAuth.signInWithEmailAndPassword(mail.getText().toString(), pass.getText().toString())
                                    .addOnCompleteListener(this, task -> {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "Logged In!");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            updateUITeacher(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "LoginInWithEmail:failure!!", task.getException());
                                            Snackbar.make(v, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();

                                        }
// ...
                                    });

                            break;
                        case "student":
                            mAuth.signInWithEmailAndPassword(mail.getText().toString(), pass.getText().toString())
                                    .addOnCompleteListener(this, task -> {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "Logged In!");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            updateUIStudent(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "LoginInWithEmail:failure!!", task.getException());
                                            Snackbar.make(v, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();

                                        }
// ...
                                    });

                            break;
                        default:Snackbar.make(v,"error key: "+key1,Snackbar.LENGTH_SHORT).show();
                    }



                }
            });



    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(key1.contains("parent")){
            updateUIParent(currentUser);
        }
        else if(key1.contains("student")){
            updateUIStudent(currentUser);
        }
        else if(key1.contains("teacher")) {
            updateUITeacher(currentUser);
        }else{
            Toast.makeText(this, "Something's Wrong!Try again", Toast.LENGTH_SHORT).show();
        }
    }*/

    public void updateUIParent( FirebaseUser account){
        if(account != null){
            Toast.makeText(this,"You Signed In successfully!",Toast.LENGTH_SHORT).show();

            PreferenceUtils.saveType("parent", this);

            Intent i =new Intent(this, Homepage_parent.class);
            i.putExtra("mail",mail.getText().toString());
            startActivity(i);

        }else {
            Toast.makeText(this,"You Didn't signed in",Toast.LENGTH_LONG).show();
        }


    }

    public void updateUIStudent( FirebaseUser account){
        if(account != null){
            Toast.makeText(this,"You Signed In successfully!",Toast.LENGTH_SHORT).show();
            PreferenceUtils.saveType("student", this);

            Intent i =new Intent(this, Homepage_student.class);
            i.putExtra("mail",mail.getText().toString());
            startActivity(i);

        }else {
            Toast.makeText(this,"You Didn't signed in",Toast.LENGTH_LONG).show();
        }

    }

    public void updateUITeacher( FirebaseUser account){
        if(account != null){
            PreferenceUtils.saveType("teacher", this);

            Toast.makeText(this,"You Signed In successfully!",Toast.LENGTH_SHORT).show();
            Intent i =new Intent(this, Attendance_list_teacher.class);
            i.putExtra("mail",mail.getText().toString());
            startActivity(i);

        }else {
            Toast.makeText(this,"You Didn't signed in",Toast.LENGTH_LONG).show();
        }
    }




}
