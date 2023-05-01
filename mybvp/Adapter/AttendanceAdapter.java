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

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder>
{
    private List<AttendanceModel> users;
    String currentUser;
    String alreadyExecuted = "no";
    String timestamp;
    private Context mContext;
    private Activity mActivity;
    private FloatingActionButton button;
    String date;
    String key;
    public AttendanceAdapter(Context mContext ,List<AttendanceModel> users, Activity mActivity, String date, String key) {
        this.mContext = mContext;
        this.users = users;
        this.mActivity = mActivity;
        this.button = button;
        this.date = date;
        this.key = key;
    }

    @NonNull
    @Override
    public AttendanceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_attendance_present_list, parent, false);
        return new AttendanceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.name.setText(users.get(position).getName());
        holder.course.setText(users.get(position).getCourse());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAttendance(users.get(position).getUid(), users.get(position).getCourse(), users.get(position).getRoll_no(), users.get(position).getName());
            }
        });
    }

    private void addAttendance(String uid, String course, String roll_number, String username)
    {
        DatabaseReference Pinfo;

        String cUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Pinfo = database.getReference("Attendance").child(key);
        DatabaseReference p_info=Pinfo.child(uid);
        p_info.child("username").setValue(username);
        p_info.child("date").setValue(date);
        p_info.child("roll_number").setValue(roll_number);
        p_info.child("student_id").setValue(uid);
        p_info.child("teacher_id").setValue(cUID);
        p_info.child("subject_name").setValue(course);
        p_info.child("presents").setValue(1);
        p_info.child("absents").setValue(0);
        p_info.child("type").setValue("Present");

        mActivity.finish();
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
        TextView name, course;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username);
            course = itemView.findViewById(R.id.course);
        }
    }
}

