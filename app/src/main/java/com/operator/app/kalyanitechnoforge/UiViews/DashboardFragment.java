package com.operator.app.kalyanitechnoforge.UiViews;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.operator.app.kalyanitechnoforge.MainActivity;
import com.operator.app.kalyanitechnoforge.Model.KaizenList;
import com.operator.app.kalyanitechnoforge.Model.KaizenResponse;
import com.operator.app.kalyanitechnoforge.R;
import com.operator.app.kalyanitechnoforge.SharedPreferenceManager;
import com.operator.app.kalyanitechnoforge.UiViews.adapter.dashboard.MyListAdapter;
import com.operator.app.kalyanitechnoforge.UiViews.adapter.dashboard.MyListData;
import com.operator.app.kalyanitechnoforge.config.ConnectionListetener;
import com.operator.app.kalyanitechnoforge.network.APIClient;
import com.operator.app.kalyanitechnoforge.service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    private Context context;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
     TextView tvNoData;
    private boolean isApiCallInProgress = false;
    SharedPreferenceManager sharedPreferenceManager;
    AppCompatTextView tvTitle;
    public static Handler handler;
    /**
     * Create instance of Loan term and condition adapter
     */
    private MyListAdapter loanApplicationHistoryAdapter;

    List<MyListData> itemList;
    int userId=0;
    String userName="";
    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_dashboard, container, false);

//        String $ = null;
//        MyListData[] myListData = new MyListData[] {
//                new MyListData("+35.12$","Approved!","25-02-2023" ),
//                new MyListData("+125.0$","Approved!","26-02-2023"),
//                new MyListData("+200.0$","Approved!","27-02-2023"),
//                new MyListData("+35.12$","Approved!","28-02-2023"),
//
//        };
//
//        MyListAdapter adapter = new MyListAdapter(myListData);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
        recyclerView=view.findViewById(R.id.rvAgreementDetailsLetterList);
        swipeRefreshLayout=view.findViewById(R.id.swipeRefreshLayout);
        tvNoData=view.findViewById(R.id.tvNoData);
        tvTitle=view.findViewById(R.id.tvTitle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedPreferenceManager=new SharedPreferenceManager(getActivity());
        sharedPreferenceManager.connectDB();
        userId=sharedPreferenceManager.getInt("userId");
        userName = sharedPreferenceManager.getString("userName");
        sharedPreferenceManager.closeDB();
        tvTitle.setText("Welcome "+userName);
        //initData();
        Log.d("TAG", "onCreateView: "+userId);
        apiCall();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        apiCall();
                        return true;
                }
                return false;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCall();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

/*
    private void apiCall() {
        if (!ConnectionListetener.checkNetworkConnection(getActivity()))
        {
            Toast.makeText(getActivity(), "Check Your Internet Conneion!", Toast.LENGTH_SHORT).show();
        }else {
            tvNoData.setVisibility(View.GONE);
            APIService apiService = APIClient.getRetrofit().create(APIService.class);

            Call<KaizenResponse> call = apiService.getOperatorKaizens(5);

            call.enqueue(new Callback<KaizenResponse>() {
                @Override
                public void onResponse(Call<KaizenResponse> call, Response<KaizenResponse> response) {
                    if (response.isSuccessful()) {
                        KaizenResponse kaizensResponse = response.body();
                        List<KaizenList> kaizens = kaizensResponse.getOperator_kaizens();
                        MyListAdapter myListAdapter=new MyListAdapter(kaizens,getActivity());
                        recyclerView.setAdapter(myListAdapter);
                        // process the kaizens list
                        tvNoData.setVisibility(View.GONE);
                        Log.d("TAG", "kaizens: 11"+kaizens.size());
                    } else {
                        tvNoData.setVisibility(View.VISIBLE);
                        Log.d("TAG", "onFailure: 11");
                    }
                }

                @Override
                public void onFailure(Call<KaizenResponse> call, Throwable t) {
                    Log.d("TAG", "onFailure: "+t.getMessage());
                    tvNoData.setVisibility(View.VISIBLE);
                }
            });
        }
    }
*/
    private void apiCall() {
        if (isApiCallInProgress) {
            return; // Return early if API call is already in progress
        }

        if (!ConnectionListetener.checkNetworkConnection(getActivity())) {
            Toast.makeText(getActivity(), "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
        } else {
            isApiCallInProgress = true; // Set the flag to indicate an API call is in progress

            tvNoData.setVisibility(View.GONE);
            APIService apiService = APIClient.getRetrofit().create(APIService.class);

            Call<KaizenResponse> call = apiService.getOperatorKaizens(String.valueOf(userId));

            call.enqueue(new Callback<KaizenResponse>() {
                @Override
                public void onResponse(Call<KaizenResponse> call, Response<KaizenResponse> response) {
                    isApiCallInProgress = false; // Reset the flag when the API call is complete

                    if (response.isSuccessful()) {
                        Log.d("TAG", "onResponse:LIST  "+new Gson().toJson(response.body()));
                        KaizenResponse kaizensResponse = response.body();
                        List<KaizenList> kaizens = kaizensResponse.getOperator_kaizens();
                        MyListAdapter myListAdapter = new MyListAdapter(kaizens, getActivity());
                        recyclerView.setAdapter(myListAdapter);
                        // process the kaizens list
                        tvNoData.setVisibility(View.GONE);
                        Log.d("TAG", "kaizens:" + kaizens.size()+"  "+kaizens.get(0).getOperator_kaizen_id());
                    } else {
                        tvNoData.setVisibility(View.VISIBLE);
                        Log.d("TAG", "onFailure: 11");
                    }
                }

                @Override
                public void onFailure(Call<KaizenResponse> call, Throwable t) {
                    isApiCallInProgress = false; // Reset the flag when the API call fails
                    Log.d("TAG", "onFailure: " + t.getMessage());
                    tvNoData.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        Assent.setFragment(this, this);
//        isLocationScreenOpen = false;
//        ((MainActivity) context).hideToolbar();
    }
}