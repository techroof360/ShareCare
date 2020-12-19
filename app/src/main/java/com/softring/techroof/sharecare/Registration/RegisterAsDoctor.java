package com.softring.techroof.sharecare.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.iid.FirebaseInstanceId;
import com.softring.techroof.sharecare.LoginActivity;
import com.softring.techroof.sharecare.R;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterAsDoctor extends AppCompatActivity {

    private static final String TAG = "RegisterDoctorActivity";
    private Context myContext = RegisterAsDoctor.this;

    private EditText
            registerDoctorFullName,
            registerDoctorEmail,
            registerDoctorSpeciality,
            registerDoctorCityTown,
            registerDoctorPassword,
            confirmRegisterDoctorPassword;

    private Button registerDoctorButton;
    private ProgressDialog progressDialog;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference storeDefaultDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as_doctor);

        Log.d(TAG, "on Create : started");

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        registerDoctorFullName = (EditText)findViewById(R.id.registerDoctorFullName);
        registerDoctorEmail = (EditText)findViewById(R.id.registerDoctorEmail);
        registerDoctorSpeciality = (EditText)findViewById(R.id.registerDoctorSpeciality);
        registerDoctorCityTown = (EditText)findViewById(R.id.registerDoctorAddress);
        registerDoctorPassword = (EditText)findViewById(R.id.registerDoctorPassword);
        confirmRegisterDoctorPassword = (EditText)findViewById(R.id.confirm_registerDoctorPassword);

        //Working with Create A/C Button Or, Register a/c
        registerDoctorButton = (Button) findViewById(R.id.resisterDoctorButton);
        registerDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = registerDoctorFullName.getText().toString();
                final String email = registerDoctorEmail.getText().toString();
                final String speciality = registerDoctorSpeciality.getText().toString();
                final String address = registerDoctorCityTown.getText().toString();
                String password = registerDoctorPassword.getText().toString();
                String confirmPassword = confirmRegisterDoctorPassword.getText().toString();

                // pass input parameter through this Method
                registerAccount(name, email, speciality, address, password, confirmPassword);
            }
        });
        progressDialog = new ProgressDialog(myContext);
    }// ending onCreate



    private void registerAccount(final String name, final String email, final String speciality, final String address, String password, String confirmPassword) {

        //Validation for empty fields
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(myContext, "Your name is required.", Toast.LENGTH_SHORT).show();
        } else if (name.length() < 3 || name.length() > 40){
            Toast.makeText(myContext, "Your name should be 3 to 40 numbers of characters.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)){
            Toast.makeText(myContext, "Your email is required.", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(myContext, "Your email is not valid.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(speciality)){
            Toast.makeText(myContext, "Your speciality is required.", Toast.LENGTH_SHORT).show();
        } else if (speciality.length() < 5){
            Toast.makeText(myContext, "Speciality should be min 5 characters.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(address)){
            Toast.makeText(myContext, "Your Address is required.", Toast.LENGTH_SHORT).show();
        } else if (address.length() < 3){
            Toast.makeText(myContext, "Address should be min 3 characters.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(myContext, "Please fill this password field", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6){
            Toast.makeText(myContext, "Create a password at least 6 characters long.", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(myContext, "Please retype in password field", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)){
            Toast.makeText(myContext, "Your password don't match with your confirm password", Toast.LENGTH_SHORT).show();
        } else
        {
            //NOw ready to create a user a/c
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                String deviceToken = FirebaseInstanceId.getInstance().getToken();

                                // get and link storage
                                String current_userID =  mAuth.getCurrentUser().getUid();
                                storeDefaultDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(current_userID);

                                storeDefaultDatabaseReference.child("user_name").setValue(name);
                                storeDefaultDatabaseReference.child("user_verified").setValue("false");
                                storeDefaultDatabaseReference.child("search_name").setValue(name.toLowerCase());
                                storeDefaultDatabaseReference.child("user_speciality").setValue(speciality.toLowerCase());
                                storeDefaultDatabaseReference.child("user_mobile").setValue("");
                                storeDefaultDatabaseReference.child("user_address").setValue(address);
                                storeDefaultDatabaseReference.child("user_email").setValue(email);
                                storeDefaultDatabaseReference.child("user_nickname").setValue("");
                                storeDefaultDatabaseReference.child("user_gender").setValue("");
                                storeDefaultDatabaseReference.child("user_profession").setValue("");
                                storeDefaultDatabaseReference.child("created_at").setValue(ServerValue.TIMESTAMP);
                                storeDefaultDatabaseReference.child("user_status").setValue("Hi, I'm Care Finder© new user!");
                                storeDefaultDatabaseReference.child("user_image").setValue("default_image"); // Original image
                                storeDefaultDatabaseReference.child("device_token").setValue(deviceToken);
                                storeDefaultDatabaseReference.child("user_thumb_image").setValue("default_image")
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    // SENDING VERIFICATION EMAIL TO THE REGISTERED USER'S EMAIL
                                                    user = mAuth.getCurrentUser();
                                                    if (user != null){
                                                        user.sendEmailVerification()
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()){

                                                                            registerSuccessPopUp();

                                                                            // LAUNCH activity after certain time period
                                                                            new Timer().schedule(new TimerTask(){
                                                                                public void run() {
                                                                                    RegisterAsDoctor.this.runOnUiThread(new Runnable() {
                                                                                        public void run() {
                                                                                            mAuth.signOut();

                                                                                            Intent mainIntent =  new Intent(myContext, LoginActivity.class);
                                                                                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                                            startActivity(mainIntent);
                                                                                            finish();

                                                                                            Toast.makeText(RegisterAsDoctor.this, "Please check your email & verify.", Toast.LENGTH_SHORT).show();

                                                                                        }
                                                                                    });
                                                                                }
                                                                            }, 8000);


                                                                        } else {
                                                                            mAuth.signOut();
                                                                        }
                                                                    }
                                                                });
                                                    }

                                                }
                                            }
                                        });

                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(myContext, "Error occurred : " + message, Toast.LENGTH_SHORT).show();
                            }

                            progressDialog.dismiss();

                        }
                    });


            //config progressbar
            progressDialog.setTitle("Creating new account");
            progressDialog.setMessage("Please wait a moment....");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
        }

    }

    private void registerSuccessPopUp() {
        // Custom Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterAsDoctor.this);
        View view = LayoutInflater.from(RegisterAsDoctor.this).inflate(R.layout.register_success_popup, null);

        //ImageButton imageButton = view.findViewById(R.id.successIcon);
        //imageButton.setImageResource(R.drawable.logout);
        builder.setCancelable(false);

        builder.setView(view);
        builder.show();
    }
}
