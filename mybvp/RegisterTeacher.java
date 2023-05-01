package com.example.mybvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mybvp.R;

public class RegisterTeacher extends AppCompatActivity {
    ImageView back;
    Button next;

    EditText name,subjectname;
    CheckBox ce,me,civil,bigdata, fy1, fy2;

    String check_option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        back=findViewById(R.id.ivBack);
        next=findViewById(R.id.btnNext);
        ce=findViewById(R.id.checkBoxCE);
        me=findViewById(R.id.checkBoxME);
        civil=findViewById(R.id.checkBoxCivil);
        bigdata=findViewById(R.id.checkBigData);
        fy1=findViewById(R.id.checkFyco1);
        fy2=findViewById(R.id.checkFyco2);
        name=findViewById(R.id.etname);
        subjectname=findViewById(R.id.etEnrollment);

        ce.setOnClickListener(v -> {
            if (ce.isChecked()) {
                me.setChecked(false);
                civil.setChecked(false);
                bigdata.setChecked(false);
                fy1.setChecked(false);
                fy2.setChecked(false);
                check_option=ce.getText().toString();
                subjectname.setHint("Example: programming with python");
            }
        });

        me.setOnClickListener(v -> {
            if (me.isChecked()) {
                ce.setChecked(false);
                civil.setChecked(false);
                bigdata.setChecked(false);
                fy1.setChecked(false);
                fy2.setChecked(false);
                check_option=me.getText().toString();
                subjectname.setHint("Example: mobile application development");
            }
        });

        civil.setOnClickListener(v -> {
            if (civil.isChecked()) {
                ce.setChecked(false);
                me.setChecked(false);
                bigdata.setChecked(false);
                fy1.setChecked(false);
                fy2.setChecked(false);
                check_option=civil.getText().toString();
                subjectname.setHint("Example: java programming");
            }
        });

        bigdata.setOnClickListener(v -> {
            if (bigdata.isChecked()) {
                ce.setChecked(false);
                me.setChecked(false);
                civil.setChecked(false);
                fy1.setChecked(false);
                fy2.setChecked(false);
                check_option=bigdata.getText().toString();
                subjectname.setHint("Example: microprocessors");
            }
        });

        fy1.setOnClickListener(v -> {
            if (fy1.isChecked()) {
                ce.setChecked(false);
                me.setChecked(false);
                civil.setChecked(false);
                bigdata.setChecked(false);
                fy2.setChecked(false);
                check_option=fy1.getText().toString();
                subjectname.setHint("Example: basic electronics");
            }
        });

        fy2.setOnClickListener(v -> {
            if (fy2.isChecked()) {
                ce.setChecked(false);
                me.setChecked(false);
                civil.setChecked(false);
                bigdata.setChecked(false);
                fy1.setChecked(false);
                check_option=fy2.getText().toString();
                subjectname.setHint("Example: applied mathematics");
            }
        });

        back.setOnClickListener((v)-> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        String subjectName = subjectname.getText().toString().trim();
        String teacherName = name.getText().toString().trim();
        next.setOnClickListener((v)-> {
            if(subjectName.isEmpty() || check_option == null || teacherName.isEmpty()){
                Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show();
            }else{
                Intent i = new Intent(this, Register_final.class);
                i.putExtra("key", "teacher");
                i.putExtra("Tcheck", check_option);
                i.putExtra("Tsubject",subjectname.getText().toString());
                i.putExtra("Tname", name.getText().toString());

                startActivity(i);
            }
        });
    }
}