package com.example.mybvp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybvp.Model.AttendanceModel;
import com.example.mybvp.Model.StudentModel;
import com.example.mybvp.MyBvpData;
import com.example.mybvp.R;
import com.example.mybvp.Register_final;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>
{
    private List<StudentModel> users;
    String currentUser;
    String alreadyExecuted = "no";
    String timestamp;
    private Context mContext;
    String date;
    String key;
    public StudentAdapter(Context mContext ,List<StudentModel> users, String key, String date) {
        this.mContext = mContext;
        this.users = users;
        this.key = key;
        this.date = date;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_attendance_list, parent, false);
        return new StudentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.name.setText(users.get(position).getUsername());
        String textSerialNumber = String.valueOf(position + 1);
        holder.serial_number.setText(textSerialNumber);
        holder.roll_number.setText(users.get(position).getRoll_number());
        holder.type.setText(users.get(position).getType());
        if(users.get(position).getType().equals("Present")){
            holder.type.setTextColor(Color.parseColor("#008080"));
        }else{
            holder.type.setTextColor(Color.parseColor("#D70040"));
        }

        String uid = users.get(position).getStudent_id();
        updateDate(uid);

        holder.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int absents = users.get(position).getAbsents();
                int presents = users.get(position).getPresents();

                if(holder.type.getText().toString().equals("Present")){
                    holder.type.setTextColor(Color.parseColor("#D70040"));
                    holder.type.setText("Absent");
                    String type = "Absent";
//                    if(presents != 0){
//                        presents = presents - 1;
//                    }
                    absents = absents + 1;

                    changeValue(type, uid, presents, absents);
                }else{
                    holder.type.setTextColor(Color.parseColor("#008080"));
                    holder.type.setText("Present");
                    String type = "Present";
//                    if(absents != 0){
//                        absents = absents - 1;
//                    }
                    presents = presents + 1;

                    changeValue(type, uid, presents, absents);
                }
            }
        });
    }

    private void changeValue(String type, String uid, int presentMark, int absentMark)
    {
        DatabaseReference Pinfo;

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Pinfo = database.getReference("Attendance").child(key);
        DatabaseReference p_info=Pinfo.child(uid);
        p_info.child("presents").setValue(presentMark);
        p_info.child("absents").setValue(absentMark);
        p_info.child("type").setValue(type);
    }

    private void updateDate(String uid)
    {
        DatabaseReference Pinfo;

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Pinfo = database.getReference("Attendance").child(key);
        DatabaseReference p_info=Pinfo.child(uid);
        p_info.child("date").setValue(date);
    }

    @Override
    public int getItemCount() {
        if(users.isEmpty()){
            return 0;
        }else {
            return users.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, serial_number, roll_number, type;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username);
            serial_number = itemView.findViewById(R.id.serial_number);
            roll_number = itemView.findViewById(R.id.roll_number);
            type = itemView.findViewById(R.id.type);
        }
    }
}


