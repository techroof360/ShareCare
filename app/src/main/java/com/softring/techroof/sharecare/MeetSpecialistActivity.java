package com.softring.techroof.sharecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class MeetSpecialistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_specialist);

        Toolbar mToolbar = findViewById(R.id.myProfile_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Developed By");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}