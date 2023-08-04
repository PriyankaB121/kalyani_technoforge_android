package com.operator.app.kalyanitechnoforge.UiViews;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.operator.app.kalyanitechnoforge.HomeActivity;
import com.operator.app.kalyanitechnoforge.LoginActivity;
import com.operator.app.kalyanitechnoforge.Model.Profile;
import com.operator.app.kalyanitechnoforge.Model.ResponseBody;
import com.operator.app.kalyanitechnoforge.Model.User;
import com.operator.app.kalyanitechnoforge.Model.UserResponse;
import com.operator.app.kalyanitechnoforge.R;
import com.operator.app.kalyanitechnoforge.SharedPreferenceManager;
import com.operator.app.kalyanitechnoforge.SplashActivity;
import com.operator.app.kalyanitechnoforge.config.ConnectionListetener;
import com.operator.app.kalyanitechnoforge.network.APIClient;
import com.operator.app.kalyanitechnoforge.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tvName, tvMobile, tvDept, tvDivision, tvBranch, tvEmpNo;
    ImageButton arrow;
     int userId=0;
     String userName="",mobile="";
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        arrow = root.findViewById(R.id.arrow);
        tvName = root.findViewById(R.id.tvName);
        tvMobile = root.findViewById(R.id.tvMobile);
        tvDept = root.findViewById(R.id.tvDept);
        tvDivision = root.findViewById(R.id.tvDivision);
        tvBranch = root.findViewById(R.id.tvBranch);
        tvEmpNo = root.findViewById(R.id.tvEmpNo);
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getActivity());
        sharedPreferenceManager.connectDB();
        userId = sharedPreferenceManager.getInt("userId");
        userName = sharedPreferenceManager.getString("userName");
        mobile = sharedPreferenceManager.getString("mobile");
        sharedPreferenceManager.closeDB();
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getActivity(),HomeActivity.class);
               startActivity(intent);
               getActivity().finishAffinity();
            }
        });
        apiCall(userId);

        return root;
    }

    private void apiCall(int userId) {
        if (!ConnectionListetener.checkNetworkConnection(getActivity())) {
            Toast.makeText(getActivity(), "Check Your Internet Conneion!", Toast.LENGTH_SHORT).show();
        } else {
            APIService apiService = APIClient.getRetrofit().create(APIService.class);

            Call<UserResponse> call = apiService.getProfile(String.valueOf(userId));
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        UserResponse operatorLogResponse = response.body();
                        int status = operatorLogResponse.getStatus();
                        String message = operatorLogResponse.getMessage();
                        Profile operatorDetails = operatorLogResponse.getUser();
                        String mobile = operatorDetails.getMobile();
                        String name = operatorDetails.getName();
                        String dept = operatorDetails.getDepartment();
                        String branch = operatorDetails.getBranch();
                        String division = operatorDetails.getDivision();
                        String empNo = operatorDetails.getEmployee_no();
                        if (mobile != null) {
                            tvMobile.setText("" + mobile);
                        }
                        if (name != null) {
                            tvName.setText("" + name);
                        }
                        if (branch != null) {
                            tvBranch.setText("" + branch);
                        }
                        if (dept != null) {
                            tvDept.setText("" + dept);
                        }
                        if (division != null) {
                            tvDivision.setText("" + division);
                        }
                        if (empNo != null) {
                            tvEmpNo.setText("" + empNo);
                        }
                        Log.d("MYTAG", "onResponse: " + response.body().getMessage());
                    } else {
                        Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}