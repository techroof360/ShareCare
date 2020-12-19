package com.softring.techroof.sharecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CreateMeetingActivity extends AppCompatActivity {

    private ProgressDialog loadingBar;
    private Button UpdatePostButton;
    private MaterialEditText formPName, formPDisease, formPAge, formPDate, formPMobile, formPTime, formPCareCenter, formPDescription;
    private static final int Gallery_Pick = 1;
    private Uri ImageUri;
    private String pName, pDisease, pAge, pDate, pMobile, pTime, pCareAddress, pDescription;

    private StorageReference PostsImagesReference;
    private DatabaseReference UsersRef, meetingsRef;
    private FirebaseAuth mAuth;

    private String saveCurrentDate, saveCurrentTime, postRandomName, downloadUrl, current_user_id;

    private long countPost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();

        UsersRef = FirebaseDatabase.getInstance().getReference().child("users");
        meetingsRef = FirebaseDatabase.getInstance().getReference().child("meetings");

        formPName = findViewById(R.id.edit_patient_name);
        formPDisease = findViewById(R.id.edit_disease_name);
        formPAge = findViewById(R.id.edit_patient_age);
        formPDate = findViewById(R.id.edit_patient_date);
        formPMobile = findViewById(R.id.edit_patient_mobile);
        formPTime = findViewById(R.id.edit_patient_time);
        formPCareCenter = findViewById(R.id.edit_hospital_address);
        formPDescription = findViewById(R.id.edit_description);
        UpdatePostButton = findViewById(R.id.Post_button);
        loadingBar = new ProgressDialog(this);

        Toolbar toolbar = findViewById(R.id.post_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Consultant Meeting Form");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("My Channel", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        UpdatePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidatePostInfo();
                AcceptedPostNotification();
            }
        });

    }

    private void AcceptedPostNotification() {
        NotificationCompat.Builder builder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(CreateMeetingActivity.this, "My Channel");
        builder.setSmallIcon(R.drawable.start_logo);
        builder.setContentTitle("Care Finder Updates");
        builder.setContentText("Your meeting has been uploaded successfully...");
        builder.setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(CreateMeetingActivity.this);
        notificationManagerCompat.notify(2, builder.build());
    }

    private void ValidatePostInfo()
    {
        pName = formPName.getText().toString();
        pDisease = formPDisease.getText().toString();
        pAge = formPAge.getText().toString();
        pDate = formPDate.getText().toString();
        pMobile = formPMobile.getText().toString();
        pTime = formPTime.getText().toString();
        pCareAddress = formPCareCenter.getText().toString();
        pDescription = formPDescription.getText().toString();

        if(TextUtils.isEmpty(pName))
        {
            Toast.makeText(this, "Type patient name here...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pDisease))
        {
            Toast.makeText(this, "Type patient disease above...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pAge))
        {
            Toast.makeText(this, "Type patient age above...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pDate))
        {
            Toast.makeText(this, "Type meeting date above...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pMobile))
        {
            Toast.makeText(this, "Type patient mobile above...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pTime))
        {
            Toast.makeText(this, "Type meeting time above...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pCareAddress))
        {
            Toast.makeText(this, "Type care center address above...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pDescription))
        {
            Toast.makeText(this, "Type a short meeting description above...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Updating New Meeting");
            loadingBar.setMessage("Please wait, while we are updating your new meeting request...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            SavingPostInformationToDatabase();
        }
    }

    private void SavingPostInformationToDatabase()
    {
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());

        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calFordTime.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;

        meetingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    countPost = dataSnapshot.getChildrenCount();
                }
                else
                {
                    countPost = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        UsersRef.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String userFullName = dataSnapshot.child("user_name").getValue().toString();
                    String userProfileImage = dataSnapshot.child("user_image").getValue().toString();

                    HashMap postsMap = new HashMap();
                    postsMap.put("uid", current_user_id);
                    postsMap.put("date", saveCurrentDate);
                    postsMap.put("time", saveCurrentTime);
                    postsMap.put("p_name", pName);
                    postsMap.put("p_disease", pDisease);
                    postsMap.put("p_age", pAge);
                    postsMap.put("p_date", pDate);
                    postsMap.put("p_mobile", pMobile);
                    postsMap.put("p_time", pTime);
                    postsMap.put("p_careAddress", pCareAddress);
                    postsMap.put("description", pDescription);
                    postsMap.put("image", userProfileImage);
                    postsMap.put("name", userFullName);
                    postsMap.put("counter", countPost);
                    meetingsRef.child(postRandomName).updateChildren(postsMap)
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        SendUserToCurrentMeetingActivity();
                                        Toast.makeText(CreateMeetingActivity.this, "New Meeting is updated successfully...", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(CreateMeetingActivity.this, "Error Occurred while updating your meeting...", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void SendUserToCurrentMeetingActivity()
    {
        Intent helpIntent = new Intent(CreateMeetingActivity.this, CurrentMeetingsActivity.class);
        startActivity(helpIntent);
    }
}