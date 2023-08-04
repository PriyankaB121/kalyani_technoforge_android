package com.operator.app.kalyanitechnoforge;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.operator.app.kalyanitechnoforge.Model.ApiResponse;
import com.operator.app.kalyanitechnoforge.Model.KaizenList;
import com.operator.app.kalyanitechnoforge.Model.KaizenMasterResponse;
import com.operator.app.kalyanitechnoforge.Model.KaizenResponse;
import com.operator.app.kalyanitechnoforge.Model.ResponseBody;
import com.operator.app.kalyanitechnoforge.Model.User;
import com.operator.app.kalyanitechnoforge.UiViews.adapter.dashboard.MyListAdapter;
import com.operator.app.kalyanitechnoforge.config.BitmapUtils;
import com.operator.app.kalyanitechnoforge.config.ConnectionListetener;
import com.operator.app.kalyanitechnoforge.network.APIClient;
import com.operator.app.kalyanitechnoforge.service.APIService;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton arrow;
    private static final int REQUEST_CAMERA_IMAGE = 1;
    private static final int FIRST = 111;
    private static final int REQUEST_GALLERY_IMAGE = 2;

    private static final int REQUEST_IMAGE_CAPTURE_1 = 1;
    private static final int REQUEST_IMAGE_GALLERY_1 = 2;
    private static final int REQUEST_IMAGE_CAPTURE_2 = 3;
    private static final int REQUEST_IMAGE_GALLERY_2 = 4;
    private static final int SECOND = 222;
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    EditText etSerialNo, etRevNo, etRevDate, etTpmCircleNo, etTpmCircleName, etStartDate, etEndDate, etTheme,
            etRevDetails, etShop, etDateOfImp, etMachine, etStatus, etCounterMeasures, etResult, etBenefits,
            etActivityPillers, etStandardLoss,etStandardLoss2,etStandardLoss3,etStandardLoss4,etStandardLoss5,
            etStandardLoss6,etStandardLoss7,etStandardLoss8,etStandardLoss9, etAfterImage,etBeforePhoto,etResultArea, etCost1, etCost2,etCostFreq, etCost3, etCost4, etCost5;
    Button btnSubmit;
    String docNo, revNo, revDate, tpmCircleNo, tpmCircleName;
    List<String> activityPillers;
    List<Integer> standardsNo1;
    List<Integer> standardNo2;
    List<Integer> standardNo3;
    List<Integer> standardNo4;
    List<Integer> standardNo5;
    List<Integer> standardNo6;
    List<Integer> standardNo7;
    List<Integer> standardNo8;
    List<Integer> standardNo9;
    List<String> resultArea;
    private boolean isApiCallInProgress = false;
    SharedPreferenceManager sharedPreferenceManager;
     int userId=0;
    String startDate, endDate, theme, revDetails, shop, dateOfImp, machine, ideaBy, presentStatus, countermeasure,
            result, benefits, cost1,cost2,cost3, cost4,cost5,beforeImageData=null,
            afterImageData=null,standardLoss1,
            standardLoss2,
            standardLoss3,
            standardLoss4,
            standardLoss5,
            standardLoss6,
            standardLoss7,
            standardLoss8,
            standardLoss9,costFreq,
    costVal1,costVal2,costVal3,costVal4,costVal5,costSaved;
    ProgressBar progressBar;
    FrameLayout snackbar_container;
    EditText etCostVal1,etCostVal2,etCostVal3,etCostVal4,etCostVal5,etCostSaved;
     ImageView imageView1,imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        btnSubmit = findViewById(R.id.btnSubmit);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        arrow = findViewById(R.id.arrow);
        progressBar = findViewById(R.id.progressBar);
        snackbar_container = findViewById(R.id.snackbar_container);
        etSerialNo = findViewById(R.id.etSerialNo);
        etRevNo = findViewById(R.id.etRevNo);
        etRevDate = findViewById(R.id.etRevDate);
        etTpmCircleNo = findViewById(R.id.etTpmCircleNo);
        etTpmCircleName = findViewById(R.id.etTpmCircleName);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        etTheme = findViewById(R.id.etTheme);
        etRevDetails = findViewById(R.id.etRevDetails);
        etShop = findViewById(R.id.etShop);
        etDateOfImp = findViewById(R.id.etDateOfImp);
        etMachine = findViewById(R.id.etMachine);
        etStatus = findViewById(R.id.etStatus);
        etCounterMeasures = findViewById(R.id.etCounterMeasures);
        etBenefits = findViewById(R.id.etBenifits);
        etResult = findViewById(R.id.etResult);
        etActivityPillers = findViewById(R.id.etActivityPillers);
        etStandardLoss = findViewById(R.id.etStandardLoss);
        etStandardLoss2 = findViewById(R.id.etStandardLoss2);
        etStandardLoss3 = findViewById(R.id.etStandardLoss3);
        etStandardLoss4 = findViewById(R.id.etStandardLoss4);
        etStandardLoss5 = findViewById(R.id.etStandardLoss5);
        etStandardLoss6 = findViewById(R.id.etStandardLoss6);
        etStandardLoss7 = findViewById(R.id.etStandardLoss7);
        etStandardLoss8 = findViewById(R.id.etStandardLoss8);
        etStandardLoss9 = findViewById(R.id.etStandardLoss9);
        etResultArea = findViewById(R.id.etResultArea);
        etAfterImage = findViewById(R.id.etAfterImage);
        etBeforePhoto = findViewById(R.id.etBeforePhoto);
        etCost1 = findViewById(R.id.etCost1);
        etCost2 = findViewById(R.id.etCost2);
        etCost3 = findViewById(R.id.etCost3);
        etCost4 = findViewById(R.id.etCost4);
        etCost5 = findViewById(R.id.etCost5);
        etCostVal1 = findViewById(R.id.etCostVal1);
        etCostVal2 = findViewById(R.id.etCostVal2);
        etCostVal3 = findViewById(R.id.etCostVal3);
        etCostVal4 = findViewById(R.id.etCostVal4);
        etCostVal5 = findViewById(R.id.etCostVal5);
        etCostFreq = findViewById(R.id.etCostFreq);
        etCostSaved = findViewById(R.id.etCostSaved);
        arrow.setOnClickListener(this);

//        etActivityPillers.setOnClickListener(this);
//        etStandardLoss.setOnClickListener(this);
//        etStandardLoss9.setOnClickListener(this);
//        etResultArea.setOnClickListener(this);
//        etCost1.setOnClickListener(this);
//        etCost2.setOnClickListener(this);
        etStartDate.setOnClickListener(this);
        etEndDate.setOnClickListener(this);
        etRevDate.setOnClickListener(this);
        etDateOfImp.setOnClickListener(this);
//        etCost3.setOnClickListener(this);
//        etCost4.setOnClickListener(this);
//        etCost5.setOnClickListener(this);
        standardsNo1 = new ArrayList<>();
        standardNo2 = new ArrayList<>();
        standardNo3 = new ArrayList<>();
        standardNo4 = new ArrayList<>();
        standardNo5 = new ArrayList<>();
        standardNo6 = new ArrayList<>();
        standardNo7 = new ArrayList<>();
        standardNo8 = new ArrayList<>();
        standardNo9 = new ArrayList<>();
        resultArea = new ArrayList<>();
        activityPillers = new ArrayList<>();
        sharedPreferenceManager=new SharedPreferenceManager(this);
        sharedPreferenceManager.connectDB();
        userId=sharedPreferenceManager.getInt("userId");
        sharedPreferenceManager.closeDB();
        apiCall1();
        etBeforePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openImagePickerDialog();
                alert();

            }
        });
        etAfterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 alert1();
                // openImagePickerDialog();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revNo = etRevNo.getText().toString();
                docNo = etSerialNo.getText().toString();
                revDate = etRevDate.getText().toString();
                revDetails = etRevDetails.getText().toString();
                dateOfImp = etDateOfImp.getText().toString();
                tpmCircleName = etTpmCircleName.getText().toString();
                tpmCircleNo = etTpmCircleNo.getText().toString();
                shop = etShop.getText().toString();
                machine = etMachine.getText().toString();
                presentStatus = etStatus.getText().toString();
                countermeasure = etCounterMeasures.getText().toString();
                result = etResult.getText().toString();
                theme = etTheme.getText().toString();
                benefits = etBenefits.getText().toString();
                startDate = etStartDate.getText().toString();
                endDate = etEndDate.getText().toString();
                standardLoss1 = etStandardLoss.getText().toString();
                standardLoss2 = etStandardLoss2.getText().toString();
                standardLoss3 = etStandardLoss3.getText().toString();
                standardLoss4 = etStandardLoss4.getText().toString();
                standardLoss5 = etStandardLoss5.getText().toString();
                standardLoss6 = etStandardLoss6.getText().toString();
                standardLoss7 = etStandardLoss7.getText().toString();
                standardLoss8 = etStandardLoss8.getText().toString();
                standardLoss9 = etStandardLoss9.getText().toString();
                String costtxt1 = etCost1.getText().toString();
                String costtxt2 = etCost2.getText().toString();
                String costtxt3 = etCost3.getText().toString();
                String costtxt4 = etCost4.getText().toString();
                String costtxt5 = etCost5.getText().toString();
                String costtxtFreq = etCostFreq.getText().toString();
                costVal1 = etCostVal1.getText().toString();
                costVal2 = etCostVal2.getText().toString();
                costVal3 = etCostVal3.getText().toString();
                costVal4 = etCostVal4.getText().toString();
                costVal5 = etCostVal5.getText().toString();
                costSaved = etCostSaved.getText().toString();
                if (docNo.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Enter DocNo", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etSerialNo.requestFocus();
                } else if (revNo.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Enter RevNo", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etRevNo.requestFocus();
                } else if (revDate.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select RevDate", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etRevDate.requestFocus();
                } else if (tpmCircleNo.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Enter Tpm Circle No", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etTpmCircleNo.requestFocus();
                } else if (tpmCircleName.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Enter Tpm Circle Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etTpmCircleName.requestFocus();
                } else if (activityPillers.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select activity pillers", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etActivityPillers.requestFocus();
               } else if (standardLoss1.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 1", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss.requestFocus();
                } else if (standardLoss2.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 2", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss2.requestFocus();
                } else if (standardLoss3.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 3", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss3.requestFocus();
                } else if (standardLoss4.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 4", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss4.requestFocus();
                } else if (standardLoss5.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 5", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss5.requestFocus();
                } else if (standardLoss6.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 6", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss6.requestFocus();
                } else if (standardLoss7.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 7", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss7.requestFocus();
                } else if (standardLoss8.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 8", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss8.requestFocus();
                } else if (standardLoss9.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select standard loss 9", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStandardLoss9.requestFocus();
                } else if (resultArea.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select result area", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etResultArea.requestFocus();
                } else if (startDate.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select start date", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etStartDate.requestFocus();
                } else if (endDate.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select end date", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etEndDate.requestFocus();
                } else if (theme.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please add theme", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etTheme.requestFocus();
                } else if (revDetails.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter Rev Details", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etRevDetails.requestFocus();
                } else if (shop.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter shop name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etShop.requestFocus();
                } else if (dateOfImp.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select implementation date", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etDateOfImp.requestFocus();
                } else if (machine.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter machine name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etMachine.requestFocus();
                } else if (presentStatus.isEmpty()) {
                        Snackbar snackbar = Snackbar.make(snackbar_container, "Please select status", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        etStatus.requestFocus();
                } else if (countermeasure.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter counter measure", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCounterMeasures.requestFocus();
                } else if (result.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter result", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etResult.requestFocus();
                } else if (benefits.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter benefit", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etBenefits.requestFocus();
                } else if (costtxt1.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select cost incurred 1", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCost1.requestFocus();
                } else if (costVal1.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter cost incurred Val 1", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCostVal1.requestFocus();
                } else if (costtxt2.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select cost incurred 2", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCost2.requestFocus();
                } else if (costVal2.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter cost incurred Val 2", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCostVal2.requestFocus();
                } else if (costtxt3.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select cost incurred 3", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCost3.requestFocus();
                } else if (costVal3.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter cost incurred val 3", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCostVal3.requestFocus();
                   } else if (costtxt4.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select cost incurred 4", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCost4.requestFocus();
                } else if (costVal4.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter cost incurred val 4", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCostVal4.requestFocus();
                } else if (costtxt5.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select cost incurred 5", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCost5.requestFocus();
                } else if (costVal5.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter cost incurred val 5", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCostVal5.requestFocus();
                } else if (costtxtFreq.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please select cost incurred frequency", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCostFreq.requestFocus();
                } else if (costSaved.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(snackbar_container, "Please enter Cost Saved", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    etCostSaved.requestFocus();
                } else {
                    apiCall(String.valueOf(userId),docNo, revNo, revDate, tpmCircleNo, tpmCircleName, activityPillers, standardsNo1, standardNo2,
                            resultArea, startDate, endDate, theme, revDetails, shop, dateOfImp, machine, ideaBy, presentStatus, countermeasure, result, benefits, beforeImageData, afterImageData);
                }
            }
        });
    }

    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);
        builder.setMessage("Choose Image from");
        builder.setPositiveButton("Gallery", (DialogInterface.OnClickListener) (dialog, which) -> {
            selectImageFromGallery(REQUEST_IMAGE_GALLERY_1);
        });
        builder.setNegativeButton("Camera", (DialogInterface.OnClickListener) (dialog, which) -> {
            selectImageFromCamera(REQUEST_IMAGE_CAPTURE_1);

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void alert1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);
        builder.setMessage("Choose Image from");
        builder.setPositiveButton("Gallery", (DialogInterface.OnClickListener) (dialog, which) -> {
            selectImageFromGallery(REQUEST_IMAGE_GALLERY_2);

        });
        builder.setNegativeButton("Camera", (DialogInterface.OnClickListener) (dialog, which) -> {
            selectImageFromCamera(REQUEST_IMAGE_CAPTURE_2);

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etStartDate:
              showDatePickerDialog(etStartDate);
                break;
            case R.id.etEndDate:
                showDatePickerDialog(etEndDate);
                break;
            case R.id.etRevDate:
                revDatePicker();
                break;
            case R.id.etDateOfImp:
                impDatePicker();
                break;
            case R.id.arrow:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void apiCall(String operator_id,String docNo, String revNo, String revDate, String tpmCircleNo, String tpmCircleName,
                         List<String> activityPillers, List<Integer> standardsNo1, List<Integer> standardNo2,List<String> resultArea, String startDate,
                         String endDate, String theme, String revDetails, String shop, String dateOfImp, String machine, String ideaBy, String presentStatus, String countermeasure, String result, String benefits,
                         String beforeImageData, String afterImageData) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionListetener.checkNetworkConnection(this)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Check Your Internet Conneion!", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.GONE);
            APIService apiService = APIClient.getRetrofit().create(APIService.class);
            Call<ApiResponse> call = apiService.createKaizen( activityPillers, standardsNo1, standardNo2,
                    standardNo3, standardNo4,standardNo5,standardNo6,standardNo7,standardNo8,standardNo9,resultArea,
                    operator_id,docNo, revNo, revDate, tpmCircleNo, tpmCircleName, startDate,
                    endDate, theme, revDetails, shop, dateOfImp, machine, ideaBy, presentStatus, countermeasure, result, benefits,
                    cost1,costVal1,cost2,costVal2,cost3,costVal3,cost4,costVal4,cost5,costVal5,"",costFreq,costSaved,costSaved,
                    beforeImageData, afterImageData,"In Progress");
            Log.d("TAG", "onResponse create: "+new Gson().toJson(call.request().body()));

            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response){
                    if (response.isSuccessful()) {
                        Toast.makeText(CreateActivity.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(CreateActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        Toast.makeText(CreateActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                    Log.d("TAG", "onResponse create: "+new Gson().toJson(response.body()));
                    Log.d("TAG", "onResponse create: "+new Gson().toJson(call.request()));
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "onResponse create: Throwable "+t.getMessage());

                }
            });
        }
    }
    private void showPopupMenu(EditText editText, Map<String, String> mapData) {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CreateActivity.this, editText);
                boolean isFirstSelection = true;
                String firstSelectedValue = "";
                String firstSelectedKey = "";
                for (Map.Entry<String, String> entry : mapData.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();

                    MenuItem menuItem = popupMenu.getMenu().add(value);
                    menuItem.setTitleCondensed(key);
                    if (isFirstSelection) {
                        firstSelectedValue = value;
                        firstSelectedKey = key;
                        isFirstSelection = false;
                    }
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String selectedValue = menuItem.getTitle().toString();
                        String selectedKey = menuItem.getTitleCondensed().toString();
                        int selectedKey1=0;
                        if (editText==etActivityPillers)
                            activityPillers= Collections.singletonList(selectedKey);
                        else if (editText==etResultArea)
                            resultArea= Collections.singletonList(selectedKey);
                        else if (editText==etStatus)
                            presentStatus= selectedValue;
                        else if (editText==etResult)
                            result= selectedValue;
                        else if (editText==etCost1) {
                            cost1 = selectedKey;
                        }else if (editText==etCost2) {
                            cost2 = selectedKey;
                        }else if (editText==etCost3) {
                            cost3 = selectedKey;
                        }else if (editText==etCost4) {
                            cost4 =selectedKey;
                        }else if (editText==etCost5) {
                            cost5 = selectedKey;
                        }else if (editText==etStandardLoss) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardsNo1 = Collections.singletonList(selectedKey1);
                        }else if (editText==etStandardLoss2) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo2 = Collections.singletonList(selectedKey1);

                        }else if (editText==etStandardLoss3) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo3= Collections.singletonList(selectedKey1);

                        }else if (editText==etStandardLoss4) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo4 = Collections.singletonList(selectedKey1);

                        }else if (editText==etStandardLoss5) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo5 = Collections.singletonList(selectedKey1);

                        }else if (editText==etStandardLoss6) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo6= Collections.singletonList(selectedKey1);

                        }else if (editText==etStandardLoss7) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo7 = Collections.singletonList(selectedKey1);

                        }else if (editText==etStandardLoss8) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo8 = Collections.singletonList(selectedKey1);

                        } else if (editText==etStandardLoss9) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo9 = Collections.singletonList(selectedKey1);
                        } else if (editText==etCostFreq)
                         costFreq= selectedKey;

                        editText.setText(selectedValue);
                        Log.d("TAG", "Selected key: " + selectedKey);

                        return true;
                    }
                });
                editText.setText(firstSelectedValue);

                popupMenu.show();
            }
        });



    }
    /* private void openImagePickerDialog() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Select Image");
         builder.setItems(new CharSequence[]{"Take Photo", "Choose from Gallery"}, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 switch (which) {
                     case 0:
                         // Open the camera to capture an image
                         openCamera();
                         break;
                     case 1:
                         // Open the gallery to choose an image
                         openGallery();
                         break;
                 }
             }
         });
         builder.show();
     }

     private void openCamera() {
         Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         if (cameraIntent.resolveActivity(getPackageManager()) != null) {
             startActivityForResult(cameraIntent, REQUEST_CAMERA_IMAGE);
         }
     }

     private void openGallery() {
         Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         startActivityForResult(galleryIntent, REQUEST_GALLERY_IMAGE);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (resultCode == RESULT_OK) {
             if (requestCode == REQUEST_CAMERA_IMAGE) {
                 Bundle extras = data.getExtras();
                 if (extras != null) {
                     Bitmap imageBitmap = (Bitmap) extras.get("data");
                     if (requestCode==SECOND){
                         bitmap1 = imageBitmap;
                         etAfterImage.setText("Photo Selected");
                         afterImageData=BitmapUtils.convertBitmapToString(bitmap1);
                     }else {
                         bitmap1 = imageBitmap;
                         etBeforePhoto.setText("Photo Selected");
                         beforeImageData=BitmapUtils.convertBitmapToString(bitmap1);

                     }

                 }
             } else if (requestCode == REQUEST_GALLERY_IMAGE) {
                 if (data != null && data.getData() != null) {
                     Uri imageUri = data.getData();
                     try {
                         Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                         bitmap2 = imageBitmap;
                         if (requestCode==SECOND){
                             bitmap2 = imageBitmap;
                             etAfterImage.setText("Photo Selected");
                             afterImageData=BitmapUtils.convertBitmapToString(bitmap2);
                         }else {
                             bitmap2 = imageBitmap;
                             etBeforePhoto.setText("Photo Selected");
                             beforeImageData=BitmapUtils.convertBitmapToString(bitmap2);

                         }
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }
     }*/

    private void selectImageFromCamera(int requestCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, requestCode);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, requestCode);
        }
    }

    private void selectImageFromGallery(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE_1 || requestCode == REQUEST_IMAGE_CAPTURE_2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, requestCode);
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE_1 && data != null) {
                // Image captured from camera for ImageView1
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView1.setImageBitmap(imageBitmap);
                beforeImageData=BitmapUtils.convertBitmapToString(imageBitmap);
                etBeforePhoto.setText("Photo Selected");

            } else if (requestCode == REQUEST_IMAGE_GALLERY_1 && data != null) {
                // Image selected from gallery for ImageView1
                Uri imageUri = data.getData();
                imageView1.setImageURI(imageUri);
                beforeImageData=BitmapUtils.convertBitmapToString(uriToBitmap(imageUri));
                etBeforePhoto.setText("Photo Selected");

            } else if (requestCode == REQUEST_IMAGE_CAPTURE_2 && data != null) {
                // Image captured from camera for ImageView2
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView2.setImageBitmap(imageBitmap);
                afterImageData=BitmapUtils.convertBitmapToString(imageBitmap);
                etAfterImage.setText("Photo Selected");

            } else if (requestCode == REQUEST_IMAGE_GALLERY_2 && data != null) {
                // Image selected from gallery for ImageView2
                Uri imageUri = data.getData();
                imageView2.setImageURI(imageUri);
                afterImageData=BitmapUtils.convertBitmapToString(uriToBitmap(imageUri));
                etAfterImage.setText("Photo Selected");

            }
        }
    }

     private Bitmap uriToBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    private void showDatePickerDialog(final EditText editText) {
        // Get the current date
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(CreateActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                selectedyear = selectedyear;
                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.US);

                    editText.setText(sdf.format(mcurrentDate.getTime()));

//                SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy",Locale.US);
//                stringFromDate = sf.format(mcurrentDate.getTime());
            }
        }, mYear, mMonth, mDay);
        mcurrentDate.set(mYear, mMonth, mDay);
//              long value=mc urrentDate.getTimeInMillis();
//              mDatePicker.getDatePicker().setMaxDate(value);
        mDatePicker.getDatePicker();
        mDatePicker.show();
    }
    private void revDatePicker() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Convert selected date to desired format
                        String formattedDate = String.format(Locale.US, "%04d-%02d-%02d", selectedYear , selectedMonth + 1, selectedDay);

                        // Set the formatted date to the EditText
                        etRevDate.setText(formattedDate);
                    }
                },
                year, month, day
        );

        // Set the minimum date to the current date
       // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        // Show the date picker dialog
        datePickerDialog.show();
    }

    private void impDatePicker() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Convert selected date to desired format
                        String formattedDate = String.format(Locale.US, "%04d-%02d-%02d",selectedYear , selectedMonth + 1, selectedDay);

                        // Set the formatted date to the EditText
                        etDateOfImp.setText(formattedDate);
                    }
                },
                year, month, day
        );

        // Set the minimum date to the current date
       // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        // Show the date picker dialog
        datePickerDialog.show();
    }
    private void apiCall1() {
        if (isApiCallInProgress) {
            return; // Return early if API call is already in progress
        }

        if (!ConnectionListetener.checkNetworkConnection(this)) {
            Toast.makeText(this, "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
        } else {
            isApiCallInProgress = true; // Set the flag to indicate an API call is in progress

            APIService apiService = APIClient.getRetrofit().create(APIService.class);

            Call<KaizenMasterResponse> call = apiService.get_kaizen_masters();

            call.enqueue(new Callback<KaizenMasterResponse>() {
                @Override
                public void onResponse(Call<KaizenMasterResponse> call, Response<KaizenMasterResponse> response) {
                    isApiCallInProgress = false; // Reset the flag when the API call is complete

                    if (response.isSuccessful()) {
                        Log.d("TAG", "onResponse:LIST  "+new Gson().toJson(response.body()));
                        KaizenMasterResponse kaizensResponse = response.body();
                        Map<String, String> mapData1 = kaizensResponse.getOperatorKaizens().getActivity();
                        Map<String, String> mapData2 = kaizensResponse.getOperatorKaizens().getLossNumber();
                        Map<String, String> mapData3 = kaizensResponse.getOperatorKaizens().getAreaDepartmentZone();
                        Map<String, String> mapData4 = kaizensResponse.getOperatorKaizens().getCostIncurred();
                        Map<String, String> mapData5 = kaizensResponse.getOperatorKaizens().getCostIncurredFrequency();
                        Map<String, String> mapData6 = kaizensResponse.getOperatorKaizens().getStatus();
                        Map<String, String> mapData7 = kaizensResponse.getOperatorKaizens().getResult();

                        if (!mapData1.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData1.entrySet().iterator().next();
                             etActivityPillers.setText(""+firstEntry.getValue());
                            activityPillers= Collections.singletonList(firstEntry.getKey());
                        }
                        if (!mapData3.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData3.entrySet().iterator().next();
                            etResultArea.setText(""+firstEntry.getValue());
                            resultArea= Collections.singletonList(firstEntry.getKey());
                        }
                        if (!mapData4.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData4.entrySet().iterator().next();
                            etCost1.setText(""+firstEntry.getValue());
                            cost1= firstEntry.getKey();
                        }
                        if (!mapData4.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData4.entrySet().iterator().next();
                            etCost2.setText(""+firstEntry.getValue());
                            cost2= firstEntry.getKey();
                        }
                        if (!mapData4.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData4.entrySet().iterator().next();
                            etCost3.setText(""+firstEntry.getValue());
                            cost3= firstEntry.getKey();
                        }
                        if (!mapData4.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData4.entrySet().iterator().next();
                            etCost4.setText(""+firstEntry.getValue());
                            cost4= firstEntry.getKey();
                        }
                        if (!mapData4.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData4.entrySet().iterator().next();
                            etCost5.setText(""+firstEntry.getValue());
                            cost5= firstEntry.getKey();
                        }
                        if (!mapData5.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData5.entrySet().iterator().next();
                            etCostFreq.setText(""+firstEntry.getValue());
                            costFreq= firstEntry.getKey();
                        }
                        if (!mapData6.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData6.entrySet().iterator().next();
                            etStatus.setText(""+firstEntry.getValue());
                            presentStatus= firstEntry.getValue();
                        }
                        if (!mapData7.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData7.entrySet().iterator().next();
                            etResult.setText(""+firstEntry.getValue());
                            result= firstEntry.getValue();
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardsNo1= Collections.singletonList(selectedKey1);
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss2.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardNo2= Collections.singletonList(selectedKey1);
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss3.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardNo3= Collections.singletonList(selectedKey1);
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss4.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardNo4= Collections.singletonList(selectedKey1);
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss5.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardNo5= Collections.singletonList(selectedKey1);
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss6.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardNo6= Collections.singletonList(selectedKey1);
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss7.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardNo7= Collections.singletonList(selectedKey1);
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss8.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardNo8= Collections.singletonList(selectedKey1);
                        }
                        if (!mapData2.isEmpty()) {
                            Map.Entry<String, String> firstEntry = mapData2.entrySet().iterator().next();
                            etStandardLoss9.setText(""+firstEntry.getValue());
                            int selectedKey1 = Integer.parseInt(String.valueOf(firstEntry.getKey()));
                            standardNo9= Collections.singletonList(selectedKey1);
                        }
                        showPopupMenu(etActivityPillers, mapData1);
                        showPopupMenu(etStandardLoss, mapData2);
                        showPopupMenu(etStandardLoss2, mapData2);
                        showPopupMenu(etStandardLoss3, mapData2);
                        showPopupMenu(etStandardLoss4, mapData2);
                        showPopupMenu(etStandardLoss5, mapData2);
                        showPopupMenu(etStandardLoss6, mapData2);
                        showPopupMenu(etStandardLoss7, mapData2);
                        showPopupMenu(etStandardLoss8, mapData2);
                        showPopupMenu(etStandardLoss9, mapData2);
                        showPopupMenu(etResultArea, mapData3);
                        showPopupMenu(etCost1, mapData4);
                        showPopupMenu(etCost2, mapData4);
                        showPopupMenu(etCost3, mapData4);
                        showPopupMenu(etCost4, mapData4);
                        showPopupMenu(etCost5, mapData4);
                        showPopupMenu(etCostFreq, mapData5);
                        showPopupMenu(etStatus, mapData6);
                        showPopupMenu(etResult, mapData7);
                    } else {
                        Log.d("TAG", "onFailure: 11");
                    }
                }

                @Override
                public void onFailure(Call<KaizenMasterResponse> call, Throwable t) {
                    isApiCallInProgress = false; // Reset the flag when the API call fails
                    Log.d("TAG", "onFailure: " + t.getMessage());
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}