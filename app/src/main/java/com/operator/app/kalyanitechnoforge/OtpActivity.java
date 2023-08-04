package com.operator.app.kalyanitechnoforge;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {

    Button OTP;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    TextView Txt_Head;
    ProgressBar progressBar;
    private String verificationId;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Txt_Head=  findViewById(R.id.Txt_Head);
        edit1= (EditText) findViewById(R.id.edit1);
        edit2= (EditText) findViewById(R.id.edit2);
        edit3= (EditText) findViewById(R.id.edit3);
        edit4= (EditText) findViewById(R.id.edit4);
        edit5= (EditText) findViewById(R.id.edit5);
        edit6= (EditText) findViewById(R.id.edit6);
        OTP = findViewById(R.id.Btn_submit);
        progressBar = findViewById(R.id.progressbar);

        edit1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = edit1.getText().length();

                if (textlength1 >= 1) {
                    edit2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });
        edit2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = edit2.getText().length();

                if (textlength1 >= 1) {
                    edit3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });
        edit3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = edit3.getText().length();

                if (textlength1 >= 1) {
                    edit4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });
        edit4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = edit4.getText().length();

                if (textlength1 >= 1) {
                    edit5.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });
        edit5.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = edit5.getText().length();

                if (textlength1 >= 1) {
                    edit6.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });
        edit6.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = edit6.getText().length();


            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });
            progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        // set this to remove reCaptcha web
        mAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);
        String phonenumber = getIntent().getStringExtra("mobile");
        Txt_Head.setText("Enter the OTP sent to +91"+phonenumber);
        sendVerificationCode("+91"+phonenumber.trim());


        OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = edit1.getText().toString().trim()+edit2.getText().toString().trim()
                        +edit3.getText().toString().trim()+edit4.getText().toString().trim()
                        +edit5.getText().toString().trim()+edit6.getText().toString().trim();
                  if (edit1.getText().toString().equals(""))
                  {
                      edit1.setError("Enter code...");
                      edit1.requestFocus();
                  }else if(edit2.getText().toString().equals("")){
                      edit2.setError("Enter code...");
                      edit2.requestFocus();
                  }else if(edit2.getText().toString().equals("")){
                      edit2.setError("Enter code...");
                      edit2.requestFocus();
                  }else if(edit3.getText().toString().equals("")){
                      edit3.setError("Enter code...");
                      edit3.requestFocus();
                  }else if(edit4.getText().toString().equals("")){
                      edit4.setError("Enter code...");
                      edit4.requestFocus();
                  }else if(edit5.getText().toString().equals("")){
                      edit5.setError("Enter code...");
                      edit5.requestFocus();
                  }else if(edit6.getText().toString().equals("")){
                      edit6.setError("Enter code...");
                      edit6.requestFocus();
                  }else if (code.isEmpty() || code.length() < 6) {
                      Toast.makeText(OtpActivity.this, "Please enter valid OTP", Toast.LENGTH_SHORT).show();
                }else {
                      verifyCode(code);
                  }
            }
        });

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);

                        } else {
                            Toast.makeText(OtpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
       /* PhoneAuthOptions options  = new PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(number) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this) // Activity (for callback binding)
                .setCallbacks(mCallBack) // OnVerificationStateChangedCallbacks
                .build()
        PhoneAuthProvider.verifyPhoneNumber(options)*/
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

   /* private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );

    }
*/
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            //Toast.makeText(OtpActivity.this, "s  "+verificationId, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                Toast.makeText(OtpActivity.this, "code  "+code, Toast.LENGTH_SHORT).show();

                //editText.setText(code);
                //verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
           // Toast.makeText(OtpActivity.this, "FirebaseException "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

}