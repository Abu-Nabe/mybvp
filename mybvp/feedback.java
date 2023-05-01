package com.example.mybvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {

    ShapeableImageView shapeableImageView;
    EditText materialTextView;
    MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        shapeableImageView = findViewById(R.id.ivBack);
        materialTextView = findViewById(R.id.tvFeedback);
        button = findViewById(R.id.btnSubmit);
        shapeableImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDatabase();
            }
        });


    }

    private void addToDatabase() {
        String timestampMillis = String.valueOf(System.currentTimeMillis());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Feedback");

        ref.child(timestampMillis).child("feedback").setValue(materialTextView.getText().toString());


        materialTextView.setText("");
    }
}