package com.operator.app.kalyanitechnoforge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.operator.app.kalyanitechnoforge.uihelper.Utilities;

public class MainActivity extends AppCompatActivity {

    /**
     * create instance of viewBinding
     * @param savedInstanceState
     */

    BottomNavigationView nvBottmNavigationMenu;

    private MenuItem selectedBottomMenuItem;

    private NavController navController;

    private Context context;

    private static MainActivity mainActivity = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.navHostFragment);
//        Utilities.getInstance().setLightStatusBar(this, ContextCompat.getColor(MainActivity.this, android.R.color.transparent));
//        setUpToolBar();
        setUpBottomMenu();
        setFragmentDestinationChangeListener();
         nvBottmNavigationMenu = (BottomNavigationView) findViewById(R.id.nvBottmNavigationMenu);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Function to change the bottom menu text.
     */
    private void setUpBottomMenu() {
        addBottomSheetStrings();

        navController = Navigation.findNavController(this, R.id.navHostFragment);

        nvBottmNavigationMenu.setItemIconTintList(null);
        nvBottmNavigationMenu.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
            }
        });

      nvBottmNavigationMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    selectedBottomMenuItem = item;
                    navController.navigate(R.id.action_dashboard);
                    return true;

                case R.id.nav_pay:
                    selectedBottomMenuItem = item;
                    navController.navigate(R.id.action_profile);
                    return true;
            }
            return false;
        });
    }

        public void addBottomSheetStrings() {
       nvBottmNavigationMenu.getMenu().findItem(R.id.home);
       nvBottmNavigationMenu.getMenu().findItem(R.id.nav_pay);
//        bwHome.setTitle("Home");
//        bwPay.setTitle("Profile");
    }


    /*
     * Set selected Item Menu of bottom
     */
    public void updateBottomNavMenu(int itemId) {
        navController = Navigation.findNavController(this, R.id.navHostFragment);
        // NavigationUI.setupWithNavController(mainBinding.nvBottmNavigationMenu, navController);
        nvBottmNavigationMenu.setOnNavigationItemSelectedListener(null);
        nvBottmNavigationMenu.findViewById(itemId).performClick();
        nvBottmNavigationMenu.setSelectedItemId(itemId);
        setUpBottomMenu();
    }


    /**
     * Function to configure toolbar with back button and title
     *
     * @param shouldBackButtonVisible
     * @param title
     * @param setEndOf
     * @param isLogoutBtnVisible
     * @param toolbarItemColor
     */
//    public void configureToolBar(boolean shouldBackButtonVisible, String title, int toolbarItemColor, boolean setEndOf, boolean isLogoutBtnVisible) {
//        ivToolBarBackArrow.setVisibility(shouldBackButtonVisible ? View.VISIBLE : View.GONE);
//        mainBinding.ivToolBarBackArrow.setOnClickListener(view -> onBackPressed());
//        mainBinding.toolbarTitle.setVisibility(View.VISIBLE);
//        mainBinding.toolbarTitle.setText(title);
//        mainBinding.appBarLayout.setElevation(0);
//        mainBinding.toolbar.setElevation(0);
//        mainBinding.toolbarTitle.setTextColor(getResources().getColor(toolbarItemColor));
//        mainBinding.ivToolBarBackArrow.setColorFilter(getResources().getColor(toolbarItemColor), PorterDuff.Mode.SRC_ATOP);
//        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//        /*if (setEndOf) {
//            params.addRule(RelativeLayout.END_OF, R.id.ivToolBarBackArrow);
//            mainBinding.rlToolbarTitle.setLayoutParams(params);
//        } else {
//            mainBinding.rlToolbarTitle.setLayoutParams(params);
//        }*/
//        if (isLogoutBtnVisible) {
//            mainBinding.ivToolLogout.setVisibility(View.VISIBLE);
//        } else {
//            mainBinding.ivToolLogout.setVisibility(View.GONE);
//        }
//        // hide share icon by default
//        enableShareIcon(false, null);
////        enableHomeIcon(false, null);
//    }

//    /**
//     * This is used to show or hide share icon button
//     *
//     * @param isVisible flag to show or hide share icon
//     */
//    public void enableShareIcon(boolean isVisible, View.OnClickListener listener) {
//        mainBinding.ivToolBarShareIcon.setVisibility(isVisible ? View.VISIBLE : View.GONE);
//        if (listener != null) {
//            mainBinding.ivToolBarShareIcon.setOnClickListener(listener);
//        }
//    }

    /**
     * This is used to show or hide share icon button
     *
     * @param isVisible flag to show or hide share icon
     */
//    public void enableHomeIcon(boolean isVisible, View.OnClickListener listener) {
//        mainBinding.ivLoanHome.setVisibility(isVisible ? View.VISIBLE : View.GONE);
//        if (listener != null) {
//            mainBinding.ivLoanHome.setOnClickListener(listener);
//        }
//    }

    /**
     * This method use for logout click listener
     */
//    private void logoutClickListener() {
//        mainBinding.ivToolLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                logoutDialog();
//            }
//        });
//    }

    /**
     * Function to hide toolbar
     */
    public void hideToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    /**
     * Function to configure the toolbar on dashboard
     */
//    private void setUpToolBar() {
//        setSupportActionBar(mainBinding.toolbar);
//        final ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//        mainBinding.toolbar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Utilities.getInstance().hideSoftKeyboard(MainActivity.this, view);
//                return false;
//            }
//        });
//    }

    /**
     * Function to show toolbar
     */
    public void showToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }
    public BottomNavigationView getBottomNavigationView() {
        return nvBottmNavigationMenu;
    }

    public static MainActivity getInstance() {
        if (mainActivity == null) {
            mainActivity = new MainActivity();
        }
        return mainActivity;
    }

    /*
     * Set visibility of Bottom navigation toolbar using destination ID.
     */
    private void setFragmentDestinationChangeListener() {
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switch (destination.getId()) {
                                    case R.id.dashboard:
                                    case R.id.profile:
                                }
                            }
                        });
                    }
                }, 100);

            }
        });
    }
}