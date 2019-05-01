package com.example.workoutapp20;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PackageManager m = getPackageManager();
        String s = getPackageName();
        try {
            PackageInfo p = m.getPackageInfo(s, 0);
            s = p.applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException e) {

        }

        File file = new File(getApplicationContext().getFilesDir(),"personInfo.txt");
        if(file.exists()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, CreateUserFirstActivity.class);
            startActivity(intent);
        }
    }
}
