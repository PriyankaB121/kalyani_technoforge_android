package com.operator.app.kalyanitechnoforge;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;
import com.operator.app.kalyanitechnoforge.Model.ResponseBody;
import com.operator.app.kalyanitechnoforge.Model.User;
import com.operator.app.kalyanitechnoforge.config.ConnectionListetener;
import com.operator.app.kalyanitechnoforge.network.APIClient;
import com.operator.app.kalyanitechnoforge.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView txt_alertMain;
    CountryCodePicker countryCodePicker;
    EditText edtMobileNo,edtPassword;
    Button btnGenerate;
    //  FirebaseAuth mAuth;
    ProgressBar progressBar;
    ImageView imageView,ivPasswordShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageView = findViewById(R.id.image);
        edtMobileNo = findViewById(R.id.edtMobileNo);
        edtPassword = findViewById(R.id.edtPassword);
        ivPasswordShow = findViewById(R.id.ivPasswordShow);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Adding the gif here using glide library
        btnGenerate = findViewById(R.id.Btn_submit);
        ivPasswordShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowHidePass(view);
            }
        });
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = edtMobileNo.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();

                if(mobile.isEmpty()|| mobile.length() < 10){
                    edtMobileNo.setError("Enter 10 digit mobile number");
                    edtMobileNo.requestFocus();
                    return;
               /* }else if(mobile.startsWith("0")|| mobile.startsWith(".")||mobile.startsWith(" ")){
                    edtMobileNo.setError("Enter a valid mobile");
                    edtMobileNo.requestFocus();
                    return;*/
                }else if(pass.isEmpty()) {
                    edtPassword.setError("Enter a valid password");
                    edtPassword.requestFocus();
                }else{
                    apiCall(mobile,pass);

                }
            }
        });
    }

    private void apiCall(String mobile, String pass) {
        if (!ConnectionListetener.checkNetworkConnection(this))
        {
            Toast.makeText(this, "Check Your Internet Conneion!", Toast.LENGTH_SHORT).show();
        }else {
            APIService apiService = APIClient.getRetrofit().create(APIService.class);

            Call<ResponseBody> call = apiService.login(mobile, pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody operatorLogResponse = response.body();
                    Log.d("MYTAG", "onResponse: "+new Gson().toJson(response.body()));

                    int status = operatorLogResponse.getStatus();
                    String message = operatorLogResponse.getMessage();
                    User operatorDetails = operatorLogResponse.getUser();
                    Log.d("MYTAG", "onResponse Login : "+new Gson().toJson(response.body()));

                    if (status==2) {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }else {
                        int userId = operatorDetails.getUser_id();
                        String mobile = operatorDetails.getMobile();
                        String password = operatorDetails.getPassword();
                        String name = operatorDetails.getName();
                        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(LoginActivity.this);
                        sharedPreferenceManager.connectDB();
                        sharedPreferenceManager.setInt("userId", userId);
                        sharedPreferenceManager.setString("mobile", mobile);
                        sharedPreferenceManager.setString("userName", name);
                        sharedPreferenceManager.closeDB();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("mobile", mobile);
                        intent.putExtra("password", password);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("MYTAG", "onResponse: "+response.body().getMessage());
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("MYTAG", "onFailure: "+t.getMessage());
                 if (t.getMessage().equals("Chain validation failed")){
                     Toast.makeText(LoginActivity.this,"Insecure Server:Can not login ", Toast.LENGTH_SHORT).show();

                 }else {
                     Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }
    }

    public void ShowHidePass(View view) {

        if (view.getId() == R.id.ivPasswordShow) {

            if (edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ivPasswordShow.setImageResource(R.drawable.ic_baseline_visibility_24);

                //Show Password
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ivPasswordShow.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Hide Password
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}