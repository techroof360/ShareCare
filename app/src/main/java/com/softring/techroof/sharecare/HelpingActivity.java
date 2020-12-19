package com.softring.techroof.sharecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelpingActivity extends AppCompatActivity {

    private CardView one, two, three, four;
    String[] urls = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helping);

        Toolbar mToolbar = findViewById(R.id.help_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Help & Support");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        one = findViewById(R.id.help_one);
        two = findViewById(R.id.help_two);
        three = findViewById(R.id.help_three);
        four = findViewById(R.id.help_four);

        urls[0] = "https://hospitals.aku.edu/pakistan/ForPatientAndVisitors/Pages/FAQs.aspx/";
        urls[1] = "https://www.care-statement.org/";
        urls[2] = "https://memorialcaredigestivecarecenter.com/terms-and-conditions/";

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCorona = new Intent(getApplicationContext(), WebViewActivity.class);
                intentCorona.putExtra("links", urls[0]);
                startActivity(intentCorona);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCorona = new Intent(getApplicationContext(), WebViewActivity.class);
                intentCorona.putExtra("links", urls[1]);
                startActivity(intentCorona);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCorona = new Intent(getApplicationContext(), WebViewActivity.class);
                intentCorona.putExtra("links", urls[2]);
                startActivity(intentCorona);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appInfoIntent = new Intent(HelpingActivity.this, MeetSpecialistActivity.class);
                startActivity(appInfoIntent);
            }
        });
    }
}
