package com.operator.app.kalyanitechnoforge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.operator.app.kalyanitechnoforge.Model.ApiResponse;
import com.operator.app.kalyanitechnoforge.Model.KaizenList;
import com.operator.app.kalyanitechnoforge.Model.KaizenMasterResponse;
import com.operator.app.kalyanitechnoforge.Model.KaizenResponse;
import com.operator.app.kalyanitechnoforge.Model.RequestData;
import com.operator.app.kalyanitechnoforge.Model.ResponseBody;
import com.operator.app.kalyanitechnoforge.Model.UpdateDelete;
import com.operator.app.kalyanitechnoforge.Model.User;
import com.operator.app.kalyanitechnoforge.UiViews.adapter.dashboard.MyListAdapter;
import com.operator.app.kalyanitechnoforge.config.BitmapUtils;
import com.operator.app.kalyanitechnoforge.config.ConnectionListetener;
import com.operator.app.kalyanitechnoforge.network.APIClient;
import com.operator.app.kalyanitechnoforge.service.APIService;

import java.io.IOException;
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

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton arrow;
    private static final int REQUEST_CAMERA_IMAGE = 1;
    private static final int FIRST = 111;
    private static final int REQUEST_GALLERY_IMAGE = 2;
    private static final int SECOND = 222;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    String kaizenId = "";
    EditText etSerialNo, etRevNo, etRevDate, etTpmCircleNo, etTpmCircleName, etStartDate, etEndDate, etTheme,
            etRevDetails, etShop, etDateOfImp, etMachine, etStatus, etCounterMeasures, etResult, etBenefits,
            etActivityPillers, etStandardLoss,etStandardLoss2,etStandardLoss3,etStandardLoss4,etStandardLoss5,
            etStandardLoss6,etStandardLoss7,etStandardLoss8, etStandardLoss9, etAfterImage, etBeforePhoto, etResultArea, etCost1, etCost2, etCostFreq, etCost3, etCost4, etCost5;
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
    String activityKey = "", stand1Key = "", stand9Key = "", areaKey = "",  stand2Key = "",
            stand3Key = "",
            stand4Key = "",
            stand5Key = "",
            stand6Key = "",
            stand7Key = "",
            stand8Key = "",
            result = "",
            freq = "",
            freqVal = "",

    stand2Val = "",
            stand3Val = "",
            stand4Val = "", stand5Val = "", stand6Val = "", stand7Val = "", stand8Val = "";
    String activityVal = "", stand1Val = "", stand9Val = "", areaVal = "";
    String cost1Key = "", cost2Key = "", cost3Key = "", cost4Key = "", status1 = "";
    String cost1Val = "", cost2Val = "", cost3Val = "", cost4Val = "", cost5Val = "";
    String startDate, endDate, theme, revDetails, shop, dateOfImp, machine, ideaBy, presentStatus, countermeasure, benefits,
            cost1, cost2, cost3, cost4, cost5, beforeImageData, afterImageData, standardLoss1,   standardLoss2,
            standardLoss3,
            standardLoss4,
            standardLoss5,
            standardLoss6,
            standardLoss7,
            standardLoss8, standardLoss9, costFreq,
            costVal1, costVal2, costVal3, costVal4, costVal5, costSaved;
    ProgressBar progressBar;
    FrameLayout snackbar_container;
    EditText etCostVal1, etCostVal2, etCostVal3, etCostVal4, etCostVal5, etCostSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btnSubmit = findViewById(R.id.btnSubmit);
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
        try {
            Bundle bundle = getIntent().getExtras();
            kaizenId = bundle.getString("kaizen_id");
            docNo = bundle.getString("docNo");
            revNo = bundle.getString("revNo");
            revDate = bundle.getString("revDate");
            tpmCircleNo = bundle.getString("tpmNo");
            tpmCircleName = bundle.getString("tpmName");
            dateOfImp = bundle.getString("impDate");
            startDate = bundle.getString("start");
            endDate = bundle.getString("end");
            shop = bundle.getString("shop");
            theme = bundle.getString("theme");
            presentStatus = bundle.getString("status");
            status1 = bundle.getString("status1");
            revDetails = bundle.getString("revDetails");
            countermeasure = bundle.getString("countermeasure");
            benefits = bundle.getString("benifit");
            result = bundle.getString("result");
            freq = bundle.getString("freq");
            freqVal = bundle.getString("freqVal");
            machine = bundle.getString("machine");
            activityKey = bundle.getString("activityKey");
            activityVal = bundle.getString("activityVal");
            areaKey = bundle.getString("areaKey");
            areaVal = bundle.getString("areaVal");
            stand1Key = bundle.getString("stand1Key");
            stand2Key = bundle.getString("stand2Key");
            stand3Key = bundle.getString("stand3Key");
            stand4Key = bundle.getString("stand4Key");
            stand5Key = bundle.getString("stand5Key");
            stand6Key = bundle.getString("stand6Key");
            stand7Key = bundle.getString("stand7Key");
            stand8Key = bundle.getString("stand8Key");
            stand1Val = bundle.getString("stand1Val");
            stand2Val = bundle.getString("stand2Val");
            stand3Val = bundle.getString("stand3Val");
            stand4Val = bundle.getString("stand4Val");
            stand5Val = bundle.getString("stand5Val");
            stand6Val = bundle.getString("stand6Val");
            stand7Val = bundle.getString("stand7Val");
            stand8Val = bundle.getString("stand8Val");
            stand9Key = bundle.getString("stand9Key");
            stand9Val = bundle.getString("stand9Val");
            cost1 = bundle.getString("cost1Key");
            cost2 = bundle.getString("cost2Key");
            cost3 = bundle.getString("cost3Key");
            cost4 = bundle.getString("cost4Key");
            cost5 = bundle.getString("cost5Key");
            cost1Val = bundle.getString("cost1Val");
            cost2Val = bundle.getString("cost2Val");
            cost3Val = bundle.getString("cost3Val");
            cost4Val = bundle.getString("cost4Val");
            cost5Val = bundle.getString("cost5Val");
            cost1 = bundle.getString("cost1");
            cost2 = bundle.getString("cost2");
            cost3 = bundle.getString("cost3");
            cost4 = bundle.getString("cost4");
            cost5 = bundle.getString("cost5");
            Log.d("TAG", "onCreate: stand1Val" + stand1Val);

            if (!docNo.isEmpty() || docNo != null) {
                etSerialNo.setText("" + docNo);
            }
            if (!revNo.isEmpty() || revNo != null) {
                etRevNo.setText("" + revNo);
            }
            if (!tpmCircleNo.isEmpty() || tpmCircleNo != null) {
                etTpmCircleNo.setText("" + tpmCircleNo);
            }
            if (!revDate.isEmpty() || revDate != null) {
                etRevDate.setText("" + revDate);
            }
            if (!startDate.isEmpty() || startDate != null) {
                etStartDate.setText("" + startDate);
            }
            if (!endDate.isEmpty() || endDate != null) {
                etEndDate.setText("" + endDate);
            }
            if (!shop.isEmpty() || shop != null) {
                etShop.setText("" + shop);
            }
            if (!machine.isEmpty() || machine != null) {
                etMachine.setText("" + machine);
            }
            if (!tpmCircleName.isEmpty() || tpmCircleName != null) {
                etTpmCircleName.setText("" + tpmCircleName);
            }
            if (!theme.isEmpty() || theme != null) {
                etTheme.setText("" + theme);
            }
            if (!dateOfImp.isEmpty() || dateOfImp != null) {
                etDateOfImp.setText("" + dateOfImp);
            }
            if (!revDetails.isEmpty() || revDetails != null) {
                etRevDetails.setText("" + revDetails);
            }
            if (!countermeasure.isEmpty() || countermeasure != null) {
                etCounterMeasures.setText("" + countermeasure);
            }
            if (!presentStatus.isEmpty() || presentStatus != null) {
                etStatus.setText("" + presentStatus);
            }
            if (!benefits.isEmpty() || benefits != null) {
                etBenefits.setText("" + benefits);
            }
            if (!result.isEmpty() || result != null) {
                etResult.setText("" + result);
            }
            if (!freq.isEmpty() || freq != null) {
                etCostFreq.setText("" + freq);
            }
            if (!freqVal.isEmpty() || freqVal != null) {
                etCostSaved.setText("" + freqVal);
            }
            if (!activityVal.isEmpty() || activityVal != null) {
                etActivityPillers.setText("" + activityVal);
                activityPillers = Collections.singletonList(activityKey);
            }
            if (!areaVal.isEmpty() || areaVal != null) {
                etResultArea.setText("" + areaVal);
                resultArea = Collections.singletonList(areaKey);
            }
            if (!stand1Val.isEmpty() || stand1Val != null) {
                etStandardLoss.setText("" + stand1Val);
            }
            if (!stand1Key.isEmpty() || stand1Key != null) {
                if (stand1Key.equals(""))
                    stand1Key = "0";
                standardsNo1 = Collections.singletonList(Integer.parseInt(stand1Key));
            }
            if (!stand2Val.isEmpty() || stand2Val != null) {
                etStandardLoss2.setText("" + stand2Val);
            }
            if (!stand2Key.isEmpty() || stand2Key != null) {
                if (stand2Key.equals(""))
                    stand2Key = "0";
                standardNo2 = Collections.singletonList(Integer.parseInt(stand2Key));
            }
            if (!stand3Val.isEmpty() || stand3Val != null) {
                etStandardLoss3.setText("" + stand3Val);
            }
            if (!stand3Key.isEmpty() || stand3Key != null) {
                if (stand3Key.equals(""))
                    stand3Key = "0";
                standardNo3 = Collections.singletonList(Integer.parseInt(stand3Key));
            }
            if (!stand4Val.isEmpty() || stand4Val != null) {
                etStandardLoss4.setText("" + stand4Val);
            }
            if (!stand4Key.isEmpty() || stand4Key != null) {
                if (stand4Key.equals(""))
                    stand4Key = "0";
                standardNo4 = Collections.singletonList(Integer.parseInt(stand4Key));
            }
            if (!stand5Val.isEmpty() || stand5Val != null) {
                etStandardLoss5.setText("" + stand5Val);
            }
            if (!stand5Key.isEmpty() || stand5Key != null) {
                if (stand5Key.equals(""))
                    stand5Key = "0";
                standardNo5 = Collections.singletonList(Integer.parseInt(stand5Key));
            }
            if (!stand6Val.isEmpty() || stand6Val != null) {
                etStandardLoss6.setText("" + stand6Val);
            }
            if (!stand6Key.isEmpty() || stand6Key != null) {
                if (stand6Key.equals(""))
                    stand6Key = "0";
                standardNo6 = Collections.singletonList(Integer.parseInt(stand6Key));
            }
            if (!stand7Val.isEmpty() || stand7Val != null) {
                etStandardLoss7.setText("" + stand7Val);
            }
            if (!stand7Key.isEmpty() || stand7Key != null) {
                if (stand7Key.equals(""))
                    stand7Key = "0";
                standardNo7 = Collections.singletonList(Integer.parseInt(stand7Key));
            }
            if (!stand8Val.isEmpty() || stand8Val != null) {
                etStandardLoss8.setText("" + stand8Val);
            }
            if (!stand8Key.isEmpty() || stand8Key != null) {
                if (stand8Key.equals(""))
                    stand8Key = "0";
                standardNo8 = Collections.singletonList(Integer.parseInt(stand8Key));
            }
            if (!stand9Val.isEmpty() || stand9Val != null) {
                etStandardLoss9.setText("" + stand9Val);
            }
            if (!stand9Key.isEmpty() || stand9Key != null) {
                if (stand9Key.equals(""))
                    stand9Key = "0";
                standardNo2 = Collections.singletonList(Integer.parseInt(stand9Key.trim()));
            }
            if (!cost1Val.isEmpty() || cost1Val != null) {
                etCost1.setText("" + cost1Val);
            }
            if (!cost2Val.isEmpty() || cost2Val != null) {
                etCost2.setText("" + cost2Val);
            }
            if (!cost3Val.isEmpty() || cost3Val != null) {
                etCost3.setText("" + cost3Val);
            }
            if (!cost4Val.isEmpty() || cost4Val != null) {
                etCost4.setText("" + cost4Val);
            }
            if (!cost5Val.isEmpty() || cost5Val != null) {
                etCost5.setText("" + cost5Val);
            }
            if (!cost5Val.isEmpty() || cost5Val != null) {
                etCost5.setText("" + cost5Val);
            }
            if (!cost1.isEmpty() || cost1 != null) {
                etCostVal1.setText("" + cost1);
            }
            if (!cost2.isEmpty() || cost2 != null) {
                etCostVal2.setText("" + cost2);
            }
            if (!cost3.isEmpty() || cost3 != null) {
                etCostVal3.setText("" + cost3);
            }
            if (!cost4.isEmpty() || cost4 != null) {
                etCostVal4.setText("" + cost4);
            }
            if (!cost5.isEmpty() || cost5 != null) {
                etCostVal5.setText("" + cost5);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        apiCall1();
        etAfterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePickerDialog();
            }
        });
        etBeforePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePickerDialog();
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
                    apiCall(docNo, revNo, revDate, tpmCircleNo, tpmCircleName, activityPillers, standardsNo1, standardNo2, standardNo3, standardNo6, standardNo7,standardNo8,standardNo9,

                            resultArea, startDate, endDate, theme, revDetails, shop, dateOfImp, machine, ideaBy, presentStatus, countermeasure, result, benefits, beforeImageData, afterImageData);
                }
            }
        });
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

    private void apiCall(String docNo, String revNo, String revDate, String tpmCircleNo, String tpmCircleName,
                         List<String> activityPillers, List<Integer> standardsNo1, List<Integer> standardNo2, List<Integer> standardNo3, List<Integer> standardNo6, List<Integer> standardNo7, List<Integer> standardNo8, List<Integer> standardNo9, List<String> resultArea, String startDate,
                         String endDate, String theme, String revDetails, String shop, String dateOfImp, String machine, String ideaBy, String presentStatus, String countermeasure, String result, String benefits,
                         String beforeImageData, String afterImageData) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionListetener.checkNetworkConnection(this)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Check Your Internet Conneion!", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.GONE);
            APIService apiService = APIClient.getRetrofit().create(APIService.class);
            Call<ApiResponse> call = apiService.updateKaizen(Integer.parseInt(kaizenId), activityPillers,
                    standardsNo1, standardNo2, standardNo3, standardNo6, standardNo7, standardNo8, standardNo9, resultArea,
                    docNo, revNo, revDate, tpmCircleNo, tpmCircleName, startDate,
                    endDate, theme, revDetails, shop, dateOfImp, machine, ideaBy, presentStatus, countermeasure,
                    result, benefits);
            Log.d("TAG", "apiCall update : " + call.request().url() + " " + new Gson().toJson(call.request().body()));
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(UpdateActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        Toast.makeText(UpdateActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                    Log.d("TAG", "onResponse create: " + new Gson().toJson(response.body()));
                    Log.d("TAG", "onResponse create: " + new Gson().toJson(call.request()));
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "onResponse create: Throwable " + t.getMessage());

                }
            });
        }
    }

    private void showPopupMenu(EditText editText, Map<String, String> mapData) {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(UpdateActivity.this, editText);

                for (Map.Entry<String, String> entry : mapData.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();

                    MenuItem menuItem = popupMenu.getMenu().add(value);
                    menuItem.setTitleCondensed(key);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String selectedValue = menuItem.getTitle().toString();
                        String selectedKey = menuItem.getTitleCondensed().toString();
                        int selectedKey1 = 0;
                        if (editText == etActivityPillers)
                            activityPillers = Collections.singletonList(selectedKey);
                        else if (editText == etResultArea)
                            resultArea = Collections.singletonList(selectedKey);
                        else if (editText == etStatus)
                            presentStatus = selectedValue;
                        else if (editText == etResult)
                            result = selectedValue;
                        else if (editText == etCost1) {
                            cost1 = selectedValue;
                        } else if (editText == etCost2) {
                            cost2 = selectedValue;
                        } else if (editText == etCost3) {
                            cost3 = selectedValue;
                        } else if (editText == etCost4) {
                            cost4 = selectedValue;
                        } else if (editText == etCost5) {
                            cost5 = selectedValue;
                        } else if (editText == etStandardLoss) {
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

                        } else if (editText == etStandardLoss9) {
                            selectedKey1 = Integer.parseInt(String.valueOf(menuItem.getTitleCondensed()));
                            standardNo2 = Collections.singletonList(selectedKey1);
                        } else if (editText == etCostFreq)
                            costFreq = selectedKey;
                        editText.setText(selectedValue);
                        Log.d("TAG", "Selected key: " + selectedKey);

                        return true;
                    }
                });

                popupMenu.show();
            }
        });


    }

    private void openImagePickerDialog() {
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
                    if (requestCode == SECOND) {
                        bitmap1 = imageBitmap;
                        etAfterImage.setText("Photo Selected");
                        afterImageData = BitmapUtils.convertBitmapToString(bitmap1);
                    } else {
                        bitmap1 = imageBitmap;
                        etBeforePhoto.setText("Photo Selected");
                        beforeImageData = BitmapUtils.convertBitmapToString(bitmap1);

                    }

                }
            } else if (requestCode == REQUEST_GALLERY_IMAGE) {
                if (data != null && data.getData() != null) {
                    Uri imageUri = data.getData();
                    try {
                        Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                        bitmap2 = imageBitmap;
                        if (requestCode == SECOND) {
                            bitmap2 = imageBitmap;
                            etAfterImage.setText("Photo Selected");
                            afterImageData = BitmapUtils.convertBitmapToString(bitmap2);
                        } else {
                            bitmap2 = imageBitmap;
                            etBeforePhoto.setText("Photo Selected");
                            beforeImageData = BitmapUtils.convertBitmapToString(bitmap2);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void showDatePickerDialog(final EditText editText) {
        // Get the current date
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                selectedyear = selectedyear;
                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

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
                        String formattedDate = String.format(Locale.US, "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);

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
                        String formattedDate = String.format(Locale.US, "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);

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
                        Log.d("TAG", "onResponse:LIST  " + new Gson().toJson(response.body()));
                        KaizenMasterResponse kaizensResponse = response.body();
                        Map<String, String> mapData1 = kaizensResponse.getOperatorKaizens().getActivity();
                        Map<String, String> mapData2 = kaizensResponse.getOperatorKaizens().getLossNumber();
                        Map<String, String> mapData3 = kaizensResponse.getOperatorKaizens().getAreaDepartmentZone();
                        Map<String, String> mapData4 = kaizensResponse.getOperatorKaizens().getCostIncurred();
                        Map<String, String> mapData5 = kaizensResponse.getOperatorKaizens().getCostIncurredFrequency();
                        Map<String, String> mapData6 = kaizensResponse.getOperatorKaizens().getStatus();
                        Map<String, String> mapData7 = kaizensResponse.getOperatorKaizens().getResult();
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
        finishAffinity();
    }
}