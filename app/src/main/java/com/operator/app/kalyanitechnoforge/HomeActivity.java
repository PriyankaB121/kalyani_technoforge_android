package com.operator.app.kalyanitechnoforge;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.operator.app.kalyanitechnoforge.UiViews.DashboardFragment;
import com.operator.app.kalyanitechnoforge.UiViews.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView btm;
    FloatingActionButton fab;
    public static FragmentTransaction obj1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btm = (BottomNavigationView) findViewById(R.id.nvBottmNavigationMenu);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        DashboardFragment home = new DashboardFragment();

        obj1 = getSupportFragmentManager().beginTransaction();
        obj1.replace(R.id.placeholder,home).commit();
        btm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        DashboardFragment home = new DashboardFragment();
                        FragmentTransaction obj1 = getSupportFragmentManager().beginTransaction();
                        obj1.replace(R.id.placeholder,home).commit();
                        break;
                    case R.id.nav_pay:
                        ProfileFragment search = new ProfileFragment();
                        FragmentTransaction obj2 = getSupportFragmentManager().beginTransaction();
                        obj2.replace(R.id.placeholder,search).commit();
                        break;
//                    case R.id.add:
//                        addFragment add = new addFragment();
//                        FragmentTransaction obj3 = getSupportFragmentManager().beginTransaction();
//                        obj3.replace(R.id.placeholder,add).commit();
//                        break;
//                    case R.id.favorite:
//                        favoriteFragment favorite = new favoriteFragment();
//                        FragmentTransaction obj4 = getSupportFragmentManager().beginTransaction();
//                        obj4.replace(R.id.placeholder,favorite).commit();
//                        break;
//                    case R.id.person:
//                        profileFragment profile = new profileFragment();
//                        FragmentTransaction obj5 = getSupportFragmentManager().beginTransaction();
//                        obj5.replace(R.id.placeholder,profile).commit();
//                        break;
                }
                return true;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
    }
