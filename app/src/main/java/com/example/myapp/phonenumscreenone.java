package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class phonenumscreenone extends AppCompatActivity {

    EditText enternumber;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenumscreenone);

        enternumber = findViewById(R.id.inputmobilenumber);
        button1 = findViewById(R.id.button1);

        ProgressBar progressBar = findViewById(R.id.sendprogress);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!enternumber.getText().toString().trim().isEmpty()) {
                    if ((enternumber.getText().toString().trim()).length() == 10) {

                        progressBar.setVisibility(View.VISIBLE);
                        button1.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + enternumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                phonenumscreenone.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        button1.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        button1.setVisibility(View.VISIBLE);
                                        Toast.makeText(phonenumscreenone.this,"Check your internet connection and try again",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        button1.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), otpscreentwo2.class);
                                          intent.putExtra("mobile", enternumber.getText().toString());
                                          intent.putExtra("backendotp",backendotp);
                                          startActivity(intent);
                                    }
                                }
                        );

                     ///   Intent intent = new Intent(getApplicationContext(), otpscreentwo.class);
                     ///   intent.putExtra("mobile", enternumber.getText().toString());
                     ///   startActivity(intent);
                    } else {
                        Toast.makeText(phonenumscreenone.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(phonenumscreenone.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


