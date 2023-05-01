package com.example.mybvp.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.mybvp.MainActivity;
import com.example.mybvp.R;

public class Attendance_list_teacher extends AppCompatActivity {

    Button buttonadd,tyco1,tyco2,syco1,syco2,fyco1,fyco2;


    EditText add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list_teacher);
        buttonadd=findViewById(R.id.btn_Add);
        add=findViewById(R.id.itAdd);

        tyco1=findViewById(R.id.btntyco1) ; tyco2=findViewById(R.id.btntyco2);
        syco1=findViewById(R.id.btnsyco1) ; syco2=findViewById(R.id.btnsyco2);
        fyco1=findViewById(R.id.btnfyco1) ; fyco2=findViewById(R.id.btnfyco2);


        tyco1.setOnClickListener((v)->{
            Intent i =new Intent(this,attendance_table_teacher.class);
            i.putExtra("key","tyco1");
            startActivity(i);
        });
        tyco2.setOnClickListener((v)->{
            Intent i =new Intent(this,attendance_table_teacher.class);
            i.putExtra("key","tyco2");
            startActivity(i);
        });
        syco1.setOnClickListener((v)->{
            Intent i =new Intent(this,attendance_table_teacher.class);
            i.putExtra("key","syco1");
            startActivity(i);
        });
        syco2.setOnClickListener((v)->{
            Intent i =new Intent(this,attendance_table_teacher.class);
            i.putExtra("key","syco2");
            startActivity(i);
        });
        fyco1.setOnClickListener((v)->{
            Intent i =new Intent(this,attendance_table_teacher.class);
            i.putExtra("key","fyco1");
            startActivity(i);
        });        fyco2.setOnClickListener((v)->{
            Intent i =new Intent(this,attendance_table_teacher.class);
            i.putExtra("key","fyco2");
            startActivity(i);
        });

        Button btn_menu=findViewById(R.id.btn_navbar);
        btn_menu.setOnClickListener((view) -> {
            PopupMenu popupMenu = new PopupMenu(Attendance_list_teacher.this, btn_menu);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.attendance_history_teacher_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if ("Attendance table".equals(menuItem.getTitle())) {
                        Intent i =new Intent(getApplicationContext(),attendance_table_teacher.class);
                        startActivity(i);
                    }
                    if ("History".equals(menuItem.getTitle())) {
                        Intent i =new Intent(getApplicationContext(),attendance_history_teacher.class);
                        startActivity(i);
                    }
                    if ("edit".equals(menuItem.getTitle())) {
                        add.setVisibility(View.VISIBLE);
                        buttonadd.setVisibility(View.VISIBLE);
                    }
                    Toast.makeText(Attendance_list_teacher.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }

                });
            popupMenu.show();


            });//menu button onclicklistener
        buttonadd.setOnClickListener((v)->{
            if(add.getText().toString()!=null){
                Button btn = new Button(this);
                btn.setId(View.generateViewId());
                ConstraintLayout.LayoutParams NamBarBtnRulVar = new ConstraintLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                NamBarBtnRulVar.setMarginStart(42);
            }else{
                Toast.makeText(this,"enter the name to add",Toast.LENGTH_SHORT).show();
            }

        });
}
}