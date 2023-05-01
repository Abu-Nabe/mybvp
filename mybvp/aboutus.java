package com.example.mybvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class aboutus extends AppCompatActivity {

    TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        about=findViewById(R.id.tvActivities);

        about.setText(" The Vidyavardhini established the Vidyavardhini’s Bhausaheb Vartak Polytechnic\n in the year 1984, with that a long-felt need for technical education\n" +
                " in the Vasai-Virar region. It is run by Vidyavardhini Trust, Vasai\n" +
                "\n" +

                "The Polytechnic is affiliated to the Maharashtra State Board\n of Technical Education (MSBTE) and offers diploma courses in Engineering Technology\n" +
                " approved by the All India Council of Technical Education,\n" +
                " New Delhi (vide letter No.740-89-135 (E)/RC/94 dated April 5, 1995),\n" +
                " Director of Technical Education Maharashtra state and government of Maharashtra.\n" +
                " The Polytechnic is situated in the sprawling campus of Vidyavardhini\n" +
                " at a stone’s throw away from Vasai Road Railway Station and is equipped with the entire infrastructure\n" +
                " needed for the various diploma courses it offers.");
    }
}