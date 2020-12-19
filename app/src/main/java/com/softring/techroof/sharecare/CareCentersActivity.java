package com.softring.techroof.sharecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CareCentersActivity extends AppCompatActivity {

    String[] urls = new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_centers);

        Toolbar toolbar = findViewById(R.id.careCenter_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Care Center Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /**
         * Web View Links open urls
         */
        TextView who = findViewById(R.id.who);
        TextView who_Pakistan = findViewById(R.id.who_pak);
        TextView shelterHomes = findViewById(R.id.shelter_home);
        LinearLayout fsd = findViewById(R.id.fsd);
        LinearLayout lhr = findViewById(R.id.lhr);
        LinearLayout gujranwala = findViewById(R.id.gujranwala);
        LinearLayout hyderabad = findViewById(R.id.hyderabad);
        LinearLayout kasur = findViewById(R.id.kasur);
        LinearLayout multan = findViewById(R.id.multan);
        LinearLayout muzaffargarh = findViewById(R.id.muzaffargarh);
        LinearLayout islamabad = findViewById(R.id.islamabad);
        LinearLayout karachi = findViewById(R.id.karachi);
        LinearLayout quetta = findViewById(R.id.quetta);
        LinearLayout rawalpindi = findViewById(R.id.rawalpindi);
        LinearLayout peshawar = findViewById(R.id.peshawar);
        LinearLayout abbottabad = findViewById(R.id.abbotabad);
        LinearLayout sialkot = findViewById(R.id.sialkot);
        LinearLayout dgKhan = findViewById(R.id.dgKhan);
        LinearLayout bhawalpur = findViewById(R.id.bhawalpur);
        LinearLayout places = findViewById(R.id.shelter_places);


        urls[0] = "https://www.who.int/";
        urls[1] = "https://www.who.int/hac/network/who/co_pakistan/en/";
        urls[2] = "https://www.pass.gov.pk/";
        urls[3] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&tbs=lf:1,lf_ui:2&tbm=lcl&sxsrf=ALeKk002IeOCArZzRRdAsf8PResbvmMptg:1608186843299&q=fsd+care+centers&rflfq=1&num=10&sa=X&ved=2ahUKEwjoqZvsstTtAhVYURUIHTw3CukQjGp6BAgEEEo&biw=1517&bih=730#rlfi=hd:;si:;mv:[[31.528336499999998,73.2916942],[31.359405599999995,72.957636]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[4] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&tbs=lf:1,lf_ui:2&tbm=lcl&sxsrf=ALeKk02zfD6o7mT4q7L6B9SLG6Mv3ZIhjg:1608186891514&q=lhr+care+centers&rflfq=1&num=10&sa=X&ved=2ahUKEwi1oJqDs9TtAhXnRhUIHTa2BocQjGp6BAgDEFE&biw=1517&bih=730#rlfi=hd:;si:;mv:[[31.6142041,74.4603849],[31.269745599999997,73.0459077]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[5] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&tbs=lf:1,lf_ui:2&tbm=lcl&sxsrf=ALeKk0236NkHs9-BWyU1Hki_FhnYhSZyIQ:1608186974276&q=gujranwala+care+centers&rflfq=1&num=10&sa=X&ved=2ahUKEwjazdWqs9TtAhVRTxUIHRNrCKAQjGp6BAgDEE8&biw=1517&bih=730#rlfi=hd:;si:;mv:[[32.239832899999996,74.2080841],[32.1415455,74.14098740000001]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[6] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1517&bih=730&tbm=lcl&sxsrf=ALeKk02aQ7vcfiBgMa6FGcZtJwuuukT-aQ%3A1608187390677&ei=_v3aX6zlKOjT1fAPuui7kAc&q=hyderabad+pak+care+centers&oq=hyderabad+pak+care+centers&gs_l=psy-ab.3...9356.10505.0.10873.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.OIIfWUe_KIQ#rlfi=hd:;si:;mv:[[25.431952100000004,68.3838523],[25.3491717,68.3310092]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[7] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&tbs=lf:1,lf_ui:2&tbm=lcl&sxsrf=ALeKk01m2unGmI9pHu3hmieQeRsTC6YWWA:1608189824505&q=kasur+care+centers&rflfq=1&num=10&sa=X&ved=2ahUKEwij4-H5vdTtAhVCtXEKHThlCRwQjGp6BAgEEDM&biw=1366&bih=657#rlfi=hd:;si:;mv:[[31.6316501,74.4952976],[30.9615334,73.8208035]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[8] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk01e_ShrSlw5v64qisycJ0y417cq4A%3A1608189831779&ei=hwfbX-yFL-SC1fAPyta--AQ&q=multan+care+centers&oq=multan+care+centers&gs_l=psy-ab.3...93976.96039.0.96830.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.xCxXb34opB8#rlfi=hd:;si:;mv:[[30.250397399999997,71.57195420000001],[30.134245,71.4153939]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[9] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk00xzsFFDmGNVeK5FQd79NJisSlefQ%3A1608189930104&ei=6gfbX4XfBeK21fAPtoe54AI&q=muzaffargarh+care+centers&oq=muzaffargarh+care+centers&gs_l=psy-ab.3...78357.82034.0.83554.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.FNRt-gHRgIg#rlfi=hd:;si:;mv:[[30.202142300000002,71.3570029],[30.004939999999994,70.9908955]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[10] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk02wke3JWXLH_BAL9OUQHcN7vTyckQ%3A1608190015702&ei=PwjbX-OsKvPzxgPxhq-gBA&q=islamabad+care+centers&oq=islamabad+care+centers&gs_l=psy-ab.3...86440.89381.0.90010.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.TkGTRGV-p6A#rlfi=hd:;si:;mv:[[33.7699565,73.2321394],[33.5533104,72.9052924]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[11] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk03R10IlGntVq76gHs1W__04GYzOGg%3A1608190699501&ei=6wrbX_uBHqPaxgPZw5bQCQ&q=karachi+care+centers&oq=karachi+care+centers&gs_l=psy-ab.3...20454.23130.0.23678.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.3QArPxhwfLg#rlfi=hd:;si:;mv:[[24.9577533,67.1732966],[24.7861475,66.9892613]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[12] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk03pLcWskEuVnsDF3ZV_-zX3zYw18Q%3A1608190107428&ei=mwjbX6GuGYHixgOOvqjADQ&q=quetta+care+centers&oq=quetta+care+centers&gs_l=psy-ab.3...74114.76018.0.77004.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.0tFXc_4y7aQ#rlfi=hd:;si:;mv:[[31.648728700000003,74.7952883],[29.975354699999997,66.53212669999999]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[13] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk01T6AsghwOLbo33Fdfo0oNN4B-tGQ%3A1608190725255&ei=BQvbX833Do-e1fAPkd6_iAo&q=rawalpindi+care+centers&oq=rawalpindi+care+centers&gs_l=psy-ab.3...108913.112694.0.112997.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.ViMcUcMeZw8#rlfi=hd:;si:;mv:[[33.6682287,73.1630716],[33.5419389,73.01189339999999]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[14] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk022Dp-2UIYD0fEykO11Z4ulpAsqow%3A1608190839758&ei=dwvbX8PtLaHsxgPd8qDIAQ&q=peshawar+care+centers&oq=peshawar+care+centers&gs_l=psy-ab.3...46707.49720.0.50043.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.7dsJmojEoAg#rlfi=hd:;si:;mv:[[34.0279595,71.6234703],[33.951109599999995,71.4039384]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[15] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk00o6jvQA12L45M5Hr5I2RcLXVxkHw%3A1608190891876&ei=qwvbX7z1NKWg1fAPkNyN2AE&q=abbottabad+care+centers&oq=abbottabad+care+centers&gs_l=psy-ab.3...40221.43934.0.44137.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.HEFDSE2Q_lA#rlfi=hd:;si:;mv:[[34.2334368,73.2546918],[34.1343126,73.2041928]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[16] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk02Pkm6gjVIRvPGh3AHKHczAM0VDFg%3A1608190937787&ei=2QvbX-LEL5am1fAP1dW4iAg&q=sialkot+care+centers&oq=sialkot+care+centers&gs_l=psy-ab.3...29740.34951.0.35528.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.usnTHr2kndc#rlfi=hd:;si:;mv:[[32.5292993,74.5798143],[32.4461315,74.4928798]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[17] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk03AG2q6KejaNpnxOATZBjWHDpGBKQ%3A1608190367129&ei=nwnbX9C3B5WU1fAPy-KDwAs&q=dg+khan+care+centers&oq=dg+khan+care+centers&gs_l=psy-ab.3...33378.35495.0.36056.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.MsYe5m37e1Y#rlfi=hd:;si:;mv:[[30.0789871,70.65513039999999],[29.858703700000003,70.62747620000002]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!3sIAE,lf:1,lf_ui:2";
        urls[18] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk02kXV4dBtkwGGfIuyTjBqAOMDvHyw%3A1608190484918&ei=FArbX5jON-GM1fAPrue0mA4&q=bhawalpur+care+centers&oq=bhawalpur+care+centers&gs_l=psy-ab.3...11359.16760.0.17620.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.ykYL5NlriZc";
        urls[19] = "https://www.google.com/search?rlz=1C1CHBD_enPK927PK927&biw=1366&bih=657&tbm=lcl&sxsrf=ALeKk01-JVza1UoPdLNM3kvYEoLIbl0Hfg%3A1608191070151&ei=XgzbX_jYCPml1fAPqJKpcA&q=shelter+homes&oq=shelter+homes&gs_l=psy-ab.3...6045.6045.0.6404.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.G12bEeEa_Ko#rlfi=hd:;si:;mv:[[33.039653900000005,74.4456778],[31.279551200000004,72.9348598]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u16!2m2!16m1!1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e2!2m1!1e16!2m1!1e3!2m4!1e17!4m2!17m1!1e2!3sIAE,lf:1,lf_ui:2";

        who.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCorona = new Intent(getApplicationContext(), WebViewActivity.class);
                intentCorona.putExtra("links", urls[0]);
                startActivity(intentCorona);
            }
        });
        who_Pakistan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaccine = new Intent(getApplicationContext(), WebViewActivity.class);
                intentVaccine.putExtra("links", urls[1]);
                startActivity(intentVaccine);
            }
        });
        shelterHomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMeetDoctors = new Intent(getApplicationContext(), WebViewActivity.class);
                intentMeetDoctors.putExtra("links", urls[2]);
                startActivity(intentMeetDoctors);
            }
        });
        fsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBabyCare = new Intent(getApplicationContext(), WebViewActivity.class);
                intentBabyCare.putExtra("links", urls[3]);
                startActivity(intentBabyCare);
            }
        });
        lhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLabs = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLabs.putExtra("links", urls[4]);
                startActivity(intentLabs);
            }
        });
        gujranwala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPublic = new Intent(getApplicationContext(), WebViewActivity.class);
                intentPublic.putExtra("links", urls[5]);
                startActivity(intentPublic);
            }
        });
        hyderabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[6]);
                startActivity(intentLivePrograms);
            }
        });
        kasur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[7]);
                startActivity(intentLivePrograms);
            }
        });
        multan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[8]);
                startActivity(intentLivePrograms);
            }
        });
        muzaffargarh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[9]);
                startActivity(intentLivePrograms);
            }
        });
        islamabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[10]);
                startActivity(intentLivePrograms);
            }
        });
        karachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[11]);
                startActivity(intentLivePrograms);
            }
        });
        quetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[12]);
                startActivity(intentLivePrograms);
            }
        });
        rawalpindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[13]);
                startActivity(intentLivePrograms);
            }
        });
        peshawar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[14]);
                startActivity(intentLivePrograms);
            }
        });
        abbottabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[15]);
                startActivity(intentLivePrograms);
            }
        });
        sialkot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[16]);
                startActivity(intentLivePrograms);
            }
        });
        dgKhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[17]);
                startActivity(intentLivePrograms);
            }
        });
        bhawalpur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[18]);
                startActivity(intentLivePrograms);
            }
        });
        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[19]);
                startActivity(intentLivePrograms);
            }
        });

    }
}