package com.operator.app.kalyanitechnoforge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    private static String[] PERMISSION = new String[]{android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int MY_PREMISSIONS_REQUEST_EXTERNAL_STORAGE = 100;
    private static final int PENDING_INTENT_REQUEST_CODE = 123;
    private int READ_PHONE_STATE = PackageManager.PERMISSION_DENIED;
    private int REQUEST_READ_CONTACTS = PackageManager.PERMISSION_DENIED;
    private int CO = PackageManager.PERMISSION_DENIED;
    private int WRITE_EXTERNAL_STORAGE = PackageManager.PERMISSION_DENIED;
    private int ACCESS_FINE_LOCATION = PackageManager.PERMISSION_DENIED;
    SharedPreferenceManager sharedPreferenceManager;
    String mobile="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferenceManager = new SharedPreferenceManager(SplashActivity.this);
        sharedPreferenceManager.connectDB();
        mobile = sharedPreferenceManager.getString("mobile");
        sharedPreferenceManager.closeDB();
        deleteCache(getApplicationContext());
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    if (mobile.isEmpty()){
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i);
                        finish();
                    }

                } catch (Exception e) {
                }
            }
        };

            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            background.start();

    }

    private void requestPermission() {

        REQUEST_READ_CONTACTS= ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS);
        READ_PHONE_STATE = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE);
        WRITE_EXTERNAL_STORAGE = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE);
        ACCESS_FINE_LOCATION = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        String[] requestPermission = new String[3];
        ArrayList<String> permissionList = new ArrayList<>();

        if (REQUEST_READ_CONTACTS != PackageManager.PERMISSION_GRANTED)
            permissionList.add(android.Manifest.permission.READ_CONTACTS);

        if (READ_PHONE_STATE != PackageManager.PERMISSION_GRANTED)
            permissionList.add(android.Manifest.permission.READ_PHONE_STATE);

        if (WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED)
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (ACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED)
            permissionList.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionList.size() > 0) {
            requestPermission = new String[permissionList.size()];
            requestPermission = permissionList.toArray(requestPermission);
            ActivityCompat.requestPermissions(this, requestPermission, MY_PREMISSIONS_REQUEST_EXTERNAL_STORAGE);
        } else {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            Thread background = new Thread() {
                public void run() {
                    try {
                        // Thread will sleep for 3 seconds
                        sleep(3000);
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    } catch (Exception e) {
                    }
                }
            };
            background.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allPermissionGranted = true;
        switch (requestCode) {
            case MY_PREMISSIONS_REQUEST_EXTERNAL_STORAGE: {
                try {
                    int i;
                    for (i = 0; i < permissions.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            allPermissionGranted = false;
                        }
                    }
                    if (allPermissionGranted) {
                        Intent i1 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i1);
                    } else {
                        Toast.makeText(getApplicationContext(), "Without allow permission the app is unable to start this application.",
                                Toast.LENGTH_LONG).show();
                        finish();
                        System.exit(0);
                    }
                    this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
