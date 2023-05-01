package com.example.mybvp.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.mybvp.R;

public class attendance_history_teacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history_teacher);

        SearchView simpleSearchView = (SearchView) findViewById(R.id.simpleSearchView); // inititate a search view
        CharSequence query = simpleSearchView.getQuery();

        CharSequence queryHint = simpleSearchView.getQueryHint();
        boolean isIconfied=simpleSearchView.isIconfiedByDefault();
        ConstraintLayout clayout=findViewById(R.id.layout);


                if (isIconfied) {
                    simpleSearchView.setQueryHint("Search Date");
                    clayout.setAlpha(0.4F);
                    simpleSearchView.setAlpha(1);
                }



        simpleSearchView.setOnQueryTextFocusChangeListener((v,onfocus)->{
            //code to be run if focus changed from search view
        });

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
// do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
// do something when text changes
                return false;
            }
        });



    }
}