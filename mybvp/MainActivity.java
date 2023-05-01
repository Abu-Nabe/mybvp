package com.example.mybvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mybvp.Parent.Homepage_parent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ImageView student,parent,teacher;
    ProgressBar bar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student=findViewById(R.id.ivloginstudent);
        parent=findViewById(R.id.ivloginparent);
        teacher=findViewById(R.id.ivloginteacher);
        bar=findViewById(R.id.progressbarSplashScren);
        mAuth=FirebaseAuth.getInstance();

        student.setOnClickListener((v)->{
            bar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,LoginPage.class);
                i.putExtra("key","student");
                startActivity(i);

                finish();
            }
        }, 3000);


        });

        teacher.setOnClickListener((v)->{

            bar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> {
                Intent i = new Intent(MainActivity.this,LoginPage.class);
                i.putExtra("key","teacher");
               startActivity(i);

                finish();
            }, 3000);


        });

        parent.setOnClickListener((v)->{

            bar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> {
                Intent i = new Intent(MainActivity.this,LoginPage.class);
              i.putExtra("key","parent");
             startActivity(i);

                finish();
            }, 3000);

        });

    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            Toast.makeText(this, "Please log in to Access!!", Toast.LENGTH_SHORT).show();

        }else{
            Intent i = new Intent(this, LoginPage.class);
            startActivity(i);
        }
    }*/
}