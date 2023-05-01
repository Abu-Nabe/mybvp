package com.example.mybvp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.mybvp.Model.TeacherModel;
import com.example.mybvp.MyBvpData;
import com.example.mybvp.R;
import com.example.mybvp.Register_final;
import com.example.mybvp.Student.calendar_view_student;
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

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder>
{
    private List<TeacherModel> users;
    private Context mContext;
    public TeacherAdapter(Context mContext ,List<TeacherModel> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @NonNull
    @Override
    public TeacherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_teachers_list, parent, false);
        return new TeacherAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
       holder.tName.setText(users.get(position).getName());
       holder.sName.setText(users.get(position).getSubject());
       holder.cName.setText(users.get(position).getClassname());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i =new Intent(mContext, calendar_view_student.class);
               i.putExtra("key",users.get(position).getClassname());
               i.putExtra("subject",users.get(position).getSubject());
               mContext.startActivity(i);
           }
       });
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
        TextView tName, cName, sName;
        public MyViewHolder(View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.username);
            cName = itemView.findViewById(R.id.room);
            sName = itemView.findViewById(R.id.subject);
        }
    }
}


