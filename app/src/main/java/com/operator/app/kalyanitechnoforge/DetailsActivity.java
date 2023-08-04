package com.operator.app.kalyanitechnoforge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.operator.app.kalyanitechnoforge.Model.DetailsResponse;
import com.operator.app.kalyanitechnoforge.Model.KaizenMasterResponse;
import com.operator.app.kalyanitechnoforge.Model.Profile;
import com.operator.app.kalyanitechnoforge.Model.ResponseBody;
import com.operator.app.kalyanitechnoforge.Model.UserResponse;
import com.operator.app.kalyanitechnoforge.UiViews.DashboardFragment;
import com.operator.app.kalyanitechnoforge.config.ConnectionListetener;
import com.operator.app.kalyanitechnoforge.databinding.ActivityDetailsBinding;
import com.operator.app.kalyanitechnoforge.network.APIClient;
import com.operator.app.kalyanitechnoforge.service.APIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    ImageView btnUpdate, btnDel;
    ImageButton arrow;
    ActivityDetailsBinding binding;
    String kaizenId;
    private boolean isApiCallInProgress = false;
    String activityKey = "", stand1Key = "",
            stand2Key = "",
            stand3Key = "",
            stand4Key = "",
            stand5Key = "",
            stand6Key = "",
            stand7Key = "",
            stand8Key = "",

    stand2Val = "",
            stand3Val = "",
            stand4Val = "", stand5Val = "", stand6Val = "", stand7Val = "", stand8Val = "",



            stand9Key = "", status1="",areaKey = "",tmpCirleName="",result="",freq="",freqVal="";
    String activityVal = "", stand1Val = "", stand9Val = "", areaVal = "";
    String cost1Key = "", cost2Key = "", cost3Key = "", cost4Key = "",cost5Key="";
    String cost1Val = "", cost2Val = "", cost3Val = "", cost4Val = "",cost5Val="";
    String cost1 = "", cost2 = "", cost3 = "", cost4 = "",cost5="";
    List<String> activityPillers;
    List<String> Standard_loss_number1;
    List<String> Standard_loss_number2;
    List<String> Standard_loss_number3;
    List<String> Standard_loss_number4;
    List<String> Standard_loss_number5;
    List<String> Standard_loss_number6;
    List<String> Standard_loss_number7;
    List<String> Standard_loss_number8;
    List<String> Standard_loss_number9;
    List<String> resultArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDel = findViewById(R.id.btnDel);
        arrow = findViewById(R.id.arrow);
        Bundle bundle = getIntent().getExtras();
        kaizenId = bundle.getString("kaizen_id");
        Log.d("TAG", "onCreate: " + kaizenId);
        activityPillers = new ArrayList<>();
        Standard_loss_number1 = new ArrayList<>();
        Standard_loss_number2 = new ArrayList<>();
        Standard_loss_number3 = new ArrayList<>();
        Standard_loss_number4 = new ArrayList<>();
        Standard_loss_number5 = new ArrayList<>();
        Standard_loss_number6 = new ArrayList<>();
        Standard_loss_number7 = new ArrayList<>();
        Standard_loss_number8 = new ArrayList<>();
        Standard_loss_number9 = new ArrayList<>();
        resultArea = new ArrayList<>();
        activityPillers = new ArrayList<>();
        apiCall(Integer.parseInt(kaizenId));

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, UpdateActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("kaizen_id", kaizenId);
                bundle1.putString("docNo", binding.tvDocNo.getText().toString());
                bundle1.putString("revNo", binding.tvRevNo.getText().toString());
                bundle1.putString("revDate", binding.tvRevDate.getText().toString());
                bundle1.putString("tpmNo", binding.tvTPMCircleNo.getText().toString());
                  bundle1.putString("tpmName",tmpCirleName);
                  bundle1.putString("result",result);
                  bundle1.putString("freq",freq);
                  bundle1.putString("freqVal",freqVal);
                bundle1.putString("theme", binding.tvTheme.getText().toString());
                bundle1.putString("shop", binding.tvShop.getText().toString());
                bundle1.putString("start", binding.tvStartDate.getText().toString());
                bundle1.putString("end", binding.tvEndDate.getText().toString());
                bundle1.putString("impDate", binding.tvDateOfImp.getText().toString());
                bundle1.putString("revDetails", binding.tvRevDetails.getText().toString());
                bundle1.putString("machine", binding.tvMachine.getText().toString());
                bundle1.putString("status", binding.tvPresentStatus.getText().toString());
                bundle1.putString("status1", status1);
                bundle1.putString("countermeasure", binding.tvCounterMeasure.getText().toString());
                bundle1.putString("benifit", binding.tvBenifits.getText().toString());
                bundle1.putString("activityKey", activityKey);
                bundle1.putString("activityVal", activityVal);
                bundle1.putString("areaKay", areaKey);
                bundle1.putString("areaVal", areaVal);
                bundle1.putString("stand1Key", stand1Key);
                bundle1.putString("stand2Key", stand2Key);
                bundle1.putString("stand3Key", stand3Key);
                bundle1.putString("stand4Key", stand4Key);
                bundle1.putString("stand5Key", stand5Key);
                bundle1.putString("stand6Key", stand6Key);
                bundle1.putString("stand7Key", stand7Key);
                bundle1.putString("stand8Key", stand8Key);
                bundle1.putString("stand1Val", stand1Val);
                bundle1.putString("stand2Val", stand2Val);
                bundle1.putString("stand3Val", stand3Val);
                bundle1.putString("stand4Val", stand4Val);
                bundle1.putString("stand5Val", stand5Val);
                bundle1.putString("stand6Val", stand6Val);
                bundle1.putString("stand7Val", stand7Val);
                bundle1.putString("stand8Val", stand8Val);
                bundle1.putString("stand9Key", stand9Key);
                bundle1.putString("stand9Val", stand9Val);
                bundle1.putString("cost1Key", cost1Key);
                bundle1.putString("cost2Key", cost2Key);
                bundle1.putString("cost3Key", cost3Key);
                bundle1.putString("cost4Key", cost4Key);
                bundle1.putString("cost5Key", cost5Key);
                bundle1.putString("cost1Val", cost1Val);
                bundle1.putString("cost2Val", cost2Val);
                bundle1.putString("cost3Val", cost3Val);
                bundle1.putString("cost4Val", cost4Val);
                bundle1.putString("cost5Val", cost5Val);

                bundle1.putString("cost1", cost1);
                bundle1.putString("cost2", cost2);
                bundle1.putString("cost3", cost3);
                bundle1.putString("cost4", cost4);
                bundle1.putString("cost5", cost5);
                //bundle1.putString("result",binding.tvCounterMeasure.getText().toString());
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();
            }
        });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiDelete(kaizenId);
            }
        });
    }

    private void apiDelete(String userId) {
        if (!ConnectionListetener.checkNetworkConnection(DetailsActivity.this)) {
            Toast.makeText(DetailsActivity.this, "Check Your Internet Conneion!", Toast.LENGTH_SHORT).show();
        } else {
            APIService apiService = APIClient.getRetrofit().create(APIService.class);

            Call<ResponseBody> call = apiService.delete(userId);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        ResponseBody operatorLogResponse = response.body();
                        int status = operatorLogResponse.getStatus();
                        String message = operatorLogResponse.getMessage();
                        Toast.makeText(DetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        DashboardFragment.handler.sendEmptyMessage(0);
                        Log.d("MYTAG", "onResponse: " + response.body().getMessage());
                    } else {
                        Toast.makeText(DetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(DetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void apiCall(int kaizen_id) {
        if (!ConnectionListetener.checkNetworkConnection(this)) {
            Toast.makeText(this, "Check Your Internet Conneion!", Toast.LENGTH_SHORT).show();
        } else {
            APIService apiService = APIClient.getRetrofit().create(APIService.class);

            Call<DetailsResponse> call = apiService.getDetails(kaizen_id);
            call.enqueue(new Callback<DetailsResponse>() {
                @Override
                public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                    Log.d("MYTAG", "onResponse DetailsResponse: " + kaizen_id + "   " + new Gson().toJson(response.body()));

                    if (response.isSuccessful()) {
                        try {
                            DetailsResponse operatorLogResponse = response.body();
                            Log.d("MYTAG", "onResponse DetailsResponse: " + new Gson().toJson(response.body()));
                            String status = operatorLogResponse.getSt();
                            String message = operatorLogResponse.getMsg();
                            DetailsResponse.OperatorKaizen operatorDetails = operatorLogResponse.getOperator_kaizens();
                            String docNo = operatorDetails.getDoc_no();
                            String revNo = operatorDetails.getRev_no();
                            String circleNo = operatorDetails.getTpm_circle_no();
                            String revDate = operatorDetails.getRev_date();
                            activityPillers = operatorDetails.getActivity_pillers();
                           if(operatorDetails.getAfter_image()!=null){
                               binding.llAfter.setVisibility(View.VISIBLE);
                               Log.d("MYTAG", "onResponse: "+"https://www.kalyanitechnoforge.cloud"+operatorDetails.getAfter_image());
                               Glide.with(DetailsActivity.this)
                                       .load("https://www.kalyanitechnoforge.cloud"+operatorDetails.getAfter_image())
                                       .into(binding.ivAfter);

                           }else {
                               binding.llAfter.setVisibility(View.GONE);
                           }
                            if(operatorDetails.getBefore_image()!=null){
                                binding.llBefore.setVisibility(View.VISIBLE);
                                Glide.with(DetailsActivity.this)
                                        .load("https://www.kalyanitechnoforge.cloud"+operatorDetails.getAfter_image())
                                        .into(binding.ivBefore);

                            }else {
                                binding.llBefore.setVisibility(View.GONE);
                            }
                            Standard_loss_number1 = operatorDetails.getStandard_loss_number1();
                            Standard_loss_number2 = operatorDetails.getStandard_loss_number2();
                            Standard_loss_number3 = operatorDetails.getStandard_loss_number3();
                            Standard_loss_number4 = operatorDetails.getStandard_loss_number4();
                            Standard_loss_number5 = operatorDetails.getStandard_loss_number5();
                            Standard_loss_number6 = operatorDetails.getStandard_loss_number6();
                            Standard_loss_number7 = operatorDetails.getStandard_loss_number7();
                            Standard_loss_number8 = operatorDetails.getStandard_loss_number8();
                            Standard_loss_number9 = operatorDetails.getStandard_loss_number9();
                            resultArea = operatorDetails.getResult_area();
                            String startDate = operatorDetails.getKaizen_statrt_date();
                            String endDate = operatorDetails.getKaizen_end_date();
                            String theme = operatorDetails.getTheme();
                            String revDetails = operatorDetails.getRev_details();
                            String shop = operatorDetails.getShop();
                            String dateOfImp = operatorDetails.getDate_of_implimentation();
                            String machine = operatorDetails.getMachine();
                            String present_status = operatorDetails.getPresent_status();
                            String counterMeasure = operatorDetails.getCountermeasure();
                            String benifits = operatorDetails.getBenefits();
                            tmpCirleName = operatorDetails.getTpm_circle_name();
                            result = operatorDetails.getResult();
                            freq = operatorDetails.getCost_incurred_frequency();
                            freqVal = operatorDetails.getCost_incurred_frequency_input();

                            String cost11 = operatorDetails.getCost_incurred_1();
                            String cost21 = operatorDetails.getCost_incurred_2();
                            String cost31 = operatorDetails.getCost_incurred_3();
                            String cost41 = operatorDetails.getCost_incurred_4();
                            String cost51 = operatorDetails.getCost_incurred_5();

                             cost1 = String.valueOf(operatorDetails.getCost_incurred_input_1());
                             cost2 = String.valueOf(operatorDetails.getCost_incurred_input_2());
                             cost3 = String.valueOf(operatorDetails.getCost_incurred_input_3());
                             cost4 = String.valueOf(operatorDetails.getCost_incurred_input_4());
                             cost5 = String.valueOf(operatorDetails.getCost_incurred_input_5());
                            if (!docNo.isEmpty() || docNo != null) {
                                binding.tvDocNo.setText("" + docNo);
                            }
                            if (!revNo.isEmpty() || revNo != null) {
                                binding.tvRevNo.setText("" + revNo);
                            }
                            if (!circleNo.isEmpty() || circleNo != null) {
                                binding.tvTPMCircleNo.setText("" + circleNo);
                            }
                            if (!revDate.isEmpty() || revDate != null) {
                                binding.tvRevDate.setText("" + revDate);
                            }

                            if (startDate != null || !startDate.isEmpty()) {
                                binding.tvStartDate.setText("" + startDate);
                            }
                            if (endDate != null || !endDate.isEmpty()) {
                                binding.tvEndDate.setText("" + endDate);
                            }
                            if (theme != null || !theme.isEmpty()) {
                                binding.tvTheme.setText("" + theme);
                            }
                            if (revDetails != null || !revDetails.isEmpty()) {
                                binding.tvRevDetails.setText("" + revDetails);
                            }

                            if (shop != null || !shop.isEmpty()) {
                                binding.tvShop.setText("" + shop);
                            }
                            if (!dateOfImp.isEmpty() || dateOfImp != null) {
                                binding.tvDateOfImp.setText("" + dateOfImp);
                            }
                            if (!machine.isEmpty() || machine != null) {
                                binding.tvMachine.setText("" + machine);
                            }
                            if (!present_status.isEmpty() || present_status != null) {
                                binding.tvPresentStatus.setText("" + present_status);
                            }
                            if (!counterMeasure.isEmpty() || counterMeasure != null) {
                                binding.tvCounterMeasure.setText("" + counterMeasure);
                            }
                            if (!benifits.isEmpty() || benifits != null) {
                                binding.tvBenifits.setText("" + benifits);
                            }
                            if (cost1 != null) {
                                binding.tvCostIncured1.setText("" + cost1);
                            }
                            if (cost2 != null) {
                                binding.tvCostIncured2.setText("" + cost2);
                            }
                            if (cost3 != null) {
                                binding.tvCostIncured3.setText("" + cost3);
                            }
                            if (cost4 != null) {
                                binding.tvCostIncured4.setText("" + cost4);
                            }
                            if (cost5 != null) {
                                binding.tvCostIncured5.setText("" + cost5);
                            }
                            apiCall1( cost11, cost21, cost31, cost41, cost51,cost1,cost2,cost3,cost4,cost5);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        Log.d("MYTAG", "onResponse: " + response.body().getMsg());
                    } else {
                        Toast.makeText(DetailsActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DetailsResponse> call, Throwable t) {
                    Log.d("MYTAG", "onFailure: " + t.getMessage());

                }
            });
        }
    }

    private void apiCall1(String cost11,String cost21,String cost31,String cost41,String cost51,String cost1,String cost2,String cost3,String cost4,String cost5) {
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
                        for (Map.Entry<String, String> entry : mapData1.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (activityPillers.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvActivityPillers.setText("" + value);
                                activityKey = key;
                                activityVal = value;
                            }
                        }
                        for (Map.Entry<String, String> entry : mapData2.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (Standard_loss_number1.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss1.setText("" + value);
                                stand1Key = key;
                                stand1Val = value;
                            }
                            if (Standard_loss_number2.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss2.setText("" + value);
                                stand2Key = key;
                                stand2Val = value;
                            }
                            if (Standard_loss_number3.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss3.setText("" + value);
                                stand3Key = key;
                                stand3Val = value;
                            }
                            if (Standard_loss_number4.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss4.setText("" + value);
                                stand4Key = key;
                                stand4Val = value;
                            }
                            if (Standard_loss_number5.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss5.setText("" + value);
                                stand5Key = key;
                                stand5Val = value;
                            }
                            if (Standard_loss_number6.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss6.setText("" + value);
                                stand6Key = key;
                                stand6Val = value;
                            }
                            if (Standard_loss_number7.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss7.setText("" + value);
                                stand7Key = key;
                                stand7Val = value;
                            }
                            if (Standard_loss_number8.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss8.setText("" + value);
                                stand8Key = key;
                                stand8Val = value;
                            }

                            if (Standard_loss_number9.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvStandardLoss9.setText("" + value);
                                stand9Key = key;
                                stand9Val = value;
                            }
                        }
                        for (Map.Entry<String, String> entry : mapData3.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (resultArea.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvResultArea.setText("" + value);
                                areaKey = key;
                                areaVal = value;
                            }
                        }
                        for (Map.Entry<String, String> entry : mapData6.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (status1.contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvPresentStatus.setText("" + value);
                                status1 = key;
                            }
                        }
                        for (Map.Entry<String, String> entry : mapData4.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (cost11.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvCostIncured1.setText(""+value+"\n" + cost1);
                                cost1Key = key;
                                cost1Val = value;
                            }
                            if (cost21.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvCostIncured2.setText(""+value+"\n" + cost2);
                                cost2Key = key;
                                cost2Val = value;
                            }
                            if (cost31.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvCostIncured3.setText(""+value+"\n" + cost3);
                                cost3Key = key;
                                cost3Val = value;
                            }
                            if (cost41.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvCostIncured4.setText(""+value+"\n" + cost4);
                                cost4Key = key;
                                cost4Val = value;
                            }
                            if (cost51.toString().contains(key)) {
                                Log.d("TAG", "Value for key 'm': " + value);
                                binding.tvCostIncured5.setText(""+value+"\n" + cost5);
                                cost5Key = key;
                                cost5Val = value;
                            }
                        }
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