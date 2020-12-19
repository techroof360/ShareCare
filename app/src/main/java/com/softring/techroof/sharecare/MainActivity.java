package com.softring.techroof.sharecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.softring.techroof.sharecare.Profile.ProfileSettings;
import com.squareup.picasso.Picasso;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int TIME_LIMIT = 1500;
    private static long backPressed;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private NavigationView navigationView;
    private CircleImageView NavProfileView;
    private TextView
            getUserName,
            btnCoronaText,
            btnVaccineText,
            btnMeetDoctorText,
            btnBabyCareText,
            btnLabsStoresText,
            btnPublicElaborationText,
            btnAwarenessProgramText;
    private LinearLayout
            biology,
            psychiatric,
            neoPlasma,
            immune,
            metabolic,
            nutrition,
            degenerative,
            infection;
    private TextView getUserStatus, dua, currentMeetings, dailyCareCourse;
    private FloatingActionButton userMsgBtn, meetingBtn;
    private Context myContext = MainActivity.this;
    private ProgressDialog progressDialog;

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseReference;
    public FirebaseUser currentUser;
    private FirebaseDatabase user_db;
    String currentUserId;
    String[] urls = new String[20];

    private ConnectivityReceiver connectivityReceiver;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            String user_uID = mAuth.getCurrentUser().getUid();

            userDatabaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("users").child(user_uID);
        }

        /**
         * Set Home Activity Toolbar Name
         */
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setTitle("uMe");

        /**
         * Navigation Drawer
         */
        navigationView = findViewById(R.id.nav_view);
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        getUserName = header.findViewById(R.id.UserNameView);
        getUserStatus = header.findViewById(R.id.UserStatusView);
        NavProfileView = header.findViewById(R.id.nav_profile);

        dua = findViewById(R.id.dua);
        dua.setSelected(true);

        userDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    if (dataSnapshot.hasChild("user_name"))
                    {
                        String userName = dataSnapshot.child("user_name").getValue().toString();
                        getUserName.setText(userName);
                    }
                    if (dataSnapshot.hasChild("user_status"))
                    {
                        String userStatus = dataSnapshot.child("user_status").getValue().toString();
                        getUserStatus.setText(userStatus);
                    }
                    if (dataSnapshot.hasChild("user_image"))
                    {
                        String userProfileImage = dataSnapshot.child("user_image").getValue().toString();
                        Picasso.get().load(userProfileImage).placeholder(R.drawable.profile_image).into(NavProfileView);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Profile is not Exists...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                UserMenuSelector(menuItem);
                return false;
            }
        });

        /**
         * Web View Links open urls
         */
        btnCoronaText = findViewById(R.id.current_covid19_report);
        btnVaccineText = findViewById(R.id.vaccination_report);
        btnMeetDoctorText = findViewById(R.id.meeting_doctors);
        btnBabyCareText = findViewById(R.id.baby_care);
        currentMeetings = findViewById(R.id.current_meetings);
        btnLabsStoresText = findViewById(R.id.labs_stores);
        btnPublicElaborationText = findViewById(R.id.public_elaborations);
        btnAwarenessProgramText = findViewById(R.id.awarenessPrograms);
        infection = findViewById(R.id.infection);
        degenerative = findViewById(R.id.degenerative);
        nutrition = findViewById(R.id.nutrition);
        metabolic = findViewById(R.id.metabolic);
        immune = findViewById(R.id.immune);
        neoPlasma = findViewById(R.id.neoPlasma);
        psychiatric = findViewById(R.id.psychiatric);
        biology = findViewById(R.id.biology);


        urls[0] = "https://www.worldometers.info/coronavirus/?utm_campaign=homeAdvegas1?";
        urls[1] = "https://www.ivi.int/";
        urls[2] = "https://www.marham.pk/doctors/physiotherapist";
        urls[3] = "https://www.sciencedirect.com/topics/chemistry/photooxidation";
        urls[4] = "https://www.lookup.pk/dynamic/search.aspx?searchtype=cat&class_id=3903";
        urls[5] = "https://www.who.int/campaigns";
        urls[6] = "https://www.who.int/campaigns";
        urls[7] = "https://www.marham.pk/doctors/infectious-diseases";
        urls[8] = "https://www.marham.pk/all-diseases/disc-degeneration";
        urls[9] = "https://www.marham.pk/doctors/nutritionist";
        urls[10] = "https://www.marham.pk/all-services/metabolic-abnormality";
        urls[11] = "https://www.marham.pk/all-services/immune-thrombocytopenia-itp";
        urls[12] = "https://www.marham.pk/doctors/oncologist";
        urls[13] = "https://www.marham.pk/doctors/psychiatrist";
        urls[14] = "https://www.marham.pk/hospitals/lahore/doctors-hospital/johar-town";
        urls[15] = "https://www.marham.pk/find-a-doctor";

        btnCoronaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCorona = new Intent(getApplicationContext(), WebViewActivity.class);
                intentCorona.putExtra("links", urls[0]);
                startActivity(intentCorona);
            }
        });
        btnVaccineText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaccine = new Intent(getApplicationContext(), WebViewActivity.class);
                intentVaccine.putExtra("links", urls[1]);
                startActivity(intentVaccine);
            }
        });
        btnMeetDoctorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMeetDoctors = new Intent(getApplicationContext(), WebViewActivity.class);
                intentMeetDoctors.putExtra("links", urls[2]);
                startActivity(intentMeetDoctors);
            }
        });
        btnBabyCareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBabyCare = new Intent(getApplicationContext(), WebViewActivity.class);
                intentBabyCare.putExtra("links", urls[3]);
                startActivity(intentBabyCare);
            }
        });
        btnLabsStoresText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLabs = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLabs.putExtra("links", urls[4]);
                startActivity(intentLabs);
            }
        });
        currentMeetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLabs = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLabs.putExtra("links", urls[15]);
                startActivity(intentLabs);
            }
        });
        btnPublicElaborationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPublic = new Intent(getApplicationContext(), WebViewActivity.class);
                intentPublic.putExtra("links", urls[5]);
                startActivity(intentPublic);
            }
        });
        btnAwarenessProgramText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[6]);
                startActivity(intentLivePrograms);
            }
        });
        infection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[7]);
                startActivity(intentLivePrograms);
            }
        });
        degenerative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[8]);
                startActivity(intentLivePrograms);
            }
        });
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[9]);
                startActivity(intentLivePrograms);
            }
        });
        metabolic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[10]);
                startActivity(intentLivePrograms);
            }
        });
        immune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[11]);
                startActivity(intentLivePrograms);
            }
        });
        neoPlasma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[12]);
                startActivity(intentLivePrograms);
            }
        });
        psychiatric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[13]);
                startActivity(intentLivePrograms);
            }
        });
        biology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLivePrograms = new Intent(getApplicationContext(), WebViewActivity.class);
                intentLivePrograms.putExtra("links", urls[14]);
                startActivity(intentLivePrograms);
            }
        });

        //Other textView click buttons
        dailyCareCourse = findViewById(R.id.dailyCareCourse);
        dailyCareCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dailyDietIntent = new Intent(MainActivity.this, DailyDietActivity.class);
                startActivity(dailyDietIntent);
            }
        });
        userMsgBtn = findViewById(R.id.user_message);
        userMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageIntent = new Intent(MainActivity.this, ConsultantChatsActivity.class);
                startActivity(messageIntent);
            }
        });
        meetingBtn = findViewById(R.id.create_meeting);
        meetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminAccessUserPendingPost();
            }
        });

    } // ending onCreate

    private void adminAccessUserPendingPost()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.meeting_dialog, null);

        final MaterialEditText inputText = (MaterialEditText)mView.findViewById(R.id.inputPass);
        Button btnCancel = (Button)mView.findViewById(R.id.btnCancel);
        Button btnSubmit = (Button)mView.findViewById(R.id.btnSubmit);

        builder.setView(mView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext, "Success:", Toast.LENGTH_SHORT).show();
                Intent cIntent = new Intent(MainActivity.this, CreateMeetingActivity.class);
                startActivity(cIntent);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void setupTabIcons() {
        //mTabLayout.getTabAt(0).setText("CHATS");
        //mTabLayout.getTabAt(1).setText("REQUESTS");
        //mTabLayout.getTabAt(2).setText("FRIENDS");
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        //checking logging, if not login redirect to Login ACTIVITY
        if (currentUser == null){
            logOutUser(); // Return to Login activity
        }
        if (currentUser != null){
            userDatabaseReference.child("active_now").setValue("true");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Register Connectivity Broadcast receiver
        connectivityReceiver = new ConnectivityReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectivityReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
        // Unregister Connectivity Broadcast receiver
        //unregisterReceiver(connectivityReceiver);

        // google kore aro jana lagbe, bug aache ekhane
//        if (currentUser != null){
//            userDatabaseReference.child("active_now").setValue(ServerValue.TIMESTAMP);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // from onStop
        if (currentUser != null){
            userDatabaseReference.child("active_now").setValue(ServerValue.TIMESTAMP);
        }
    }

    private void logOutUser() {
        Intent loginIntent =  new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }


    // tool bar action menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_search){
            Intent intent =  new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }

        /*if (item.getItemId() == R.id.profile_settings){
            *//*Intent profileIntent = new Intent(MainActivity.this, ProfileSettings.class);
            startActivity(profileIntent);*//*
        }

        if (item.getItemId() == R.id.all_friends){
            *//*Intent friendsIntent = new Intent(MainActivity.this, FriendsActivity.class);
            startActivity(friendsIntent);*//*
        }*/

        if (item.getItemId() == R.id.about_app){
            Intent infoIntent = new Intent(MainActivity.this, CommunityInfoActivity.class);
            startActivity(infoIntent);
        }

        if (item.getItemId() == R.id.main_logout){
            // Custom Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.logout_dailog, null);

            ImageButton imageButton = view.findViewById(R.id.logoutImg);
            imageButton.setImageResource(R.drawable.logout);
            builder.setCancelable(true);

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.setPositiveButton("YES, Log out", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (currentUser != null){
                        userDatabaseReference.child("active_now").setValue(ServerValue.TIMESTAMP);
                    }
                    mAuth.signOut();
                    logOutUser();
                }
            });
            builder.setView(view);
            builder.show();
        }
        return true;
    }

    private void UserMenuSelector(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;
            case R.id.user_import:
                Intent profileIntent = new Intent(MainActivity.this, ProfileSettings.class);
                startActivity(profileIntent);
                break;
            case R.id.user_govt_sector:
                Intent intentCorona = new Intent(getApplicationContext(), WebViewActivity.class);
                intentCorona.putExtra("links", urls[1]);
                startActivity(intentCorona);
                break;
            case R.id.user_public_posts:
                Intent meetingIntent = new Intent(MainActivity.this, CurrentMeetingsActivity.class);
                startActivity(meetingIntent);
                break;
            case R.id.user_pvt_sector:
                Intent portfolioIntent = new Intent(MainActivity.this, CareCentersActivity.class);
                startActivity(portfolioIntent);
                break;
            case R.id.settings:
                Intent contactUsIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(contactUsIntent);
                break;
            case R.id.feedback:
                Intent rateIntent = new Intent(MainActivity.this, RateUsActivity.class);
                startActivity(rateIntent);
                break;
            case R.id.security:
                // Custom Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.security_dialog, null);

                ImageButton imageButton = view.findViewById(R.id.logoutImg);
                imageButton.setImageResource(R.drawable.ic_security);
                builder.setCancelable(true);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent profileIntent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                        startActivity(profileIntent);
                    }
                });
                builder.setView(view);
                builder.show();
                break;
            case R.id.share_with:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://techroofsoftring.000webhostapp.com/");
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Share via : "));
                break;
            case R.id.help:
                Intent helpIntent = new Intent(MainActivity.this, HelpingActivity.class);
                startActivity(helpIntent);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    // Broadcast receiver for network checking
    public class ConnectivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()){

            } else {
                Snackbar snackbar = Snackbar
                        .make(mViewPager, "No internet connection! ", Snackbar.LENGTH_LONG)
                        .setAction("Go settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                startActivity(intent);
                            }
                        });
                // Changing action button text color
                snackbar.setActionTextColor(Color.BLACK);
                // Changing message text color
                View view = snackbar.getView();
                view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                TextView textView = view.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.show();
            }
        }
    }

    // This method is used to detect back button
    @Override
    public void onBackPressed() {
        if(TIME_LIMIT + backPressed > System.currentTimeMillis()){
            super.onBackPressed();
            //Toast.makeText(getApplicationContext(), "Exited", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    } //End Back button press for exit...
}