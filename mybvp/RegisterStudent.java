package com.example.mybvp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mybvp.R;

public class RegisterStudent extends AppCompatActivity {
    ImageView back;
    Button next;

    EditText name,enrol,roll;
    CheckBox ce,me,civil,bigdata;

    String check_option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        back=findViewById(R.id.ivBack);
        next=findViewById(R.id.btnNext);
        enrol=findViewById(R.id.etEnrollment);
        roll=findViewById(R.id.etRoll_no);
        ce=findViewById(R.id.checkBoxCE);
        me=findViewById(R.id.checkBoxME);
        civil=findViewById(R.id.checkBoxCivil);
        bigdata=findViewById(R.id.checkBigData);
        name=findViewById(R.id.etName);

        ce.setOnClickListener(v -> {
            if (ce.isChecked()) {
                me.setChecked(false);
                civil.setChecked(false);
                bigdata.setChecked(false);
                check_option=ce.getText().toString();
            }
        });

        me.setOnClickListener(v -> {
            if (me.isChecked()) {
                ce.setChecked(false);
                civil.setChecked(false);
                bigdata.setChecked(false);
                check_option=me.getText().toString();
            }
        });

        civil.setOnClickListener(v -> {
            if (civil.isChecked()) {
                ce.setChecked(false);
                me.setChecked(false);
                bigdata.setChecked(false);
                check_option=civil.getText().toString();
            }
        });

        bigdata.setOnClickListener(v -> {
            if (bigdata.isChecked()) {
                ce.setChecked(false);
                me.setChecked(false);
                civil.setChecked(false);
                check_option=bigdata.getText().toString();
            }
        });

        back.setOnClickListener((v)-> {
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                });

            next.setOnClickListener((v)-> {
                if(!check_option.equals("")){
                    Intent i = new Intent(this, Register_final.class);
                    i.putExtra("key","student");
                    i.putExtra("Scheck", check_option);
                    i.putExtra("Sroll",roll.getText().toString() );
                    i.putExtra("Senrol", enrol.getText().toString());
                    i.putExtra("Sname", name.getText().toString());
                    startActivity(i);
                }else{
                    Toast.makeText(this, "Course isn't checked!", Toast.LENGTH_SHORT).show();
                }
        });
    }
}