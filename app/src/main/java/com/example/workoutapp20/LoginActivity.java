package com.example.workoutapp20;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoginActivity extends AppCompatActivity {

    Person CurrentUser;
    String[] userInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        File file = new File(getApplicationContext().getFilesDir(),"personInfo.txt");
        if(file.exists()){
            try {
                BufferedReader buffreader = new BufferedReader(new FileReader(file));
                String line, line1 = "";
                try {
                    while ((line = buffreader.readLine()) != null) {
                        line1 += line;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                CurrentUser = new Person();
                userInfo = line1.split(",");
                CurrentUser.Name = userInfo[0];
                CurrentUser.height = Double.parseDouble(userInfo[1]);
                CurrentUser.weight = Double.parseDouble(userInfo[2]);
                CurrentUser.GoalWeight = Double.parseDouble(userInfo[3]);
                CurrentUser.Age = Integer.parseInt(userInfo[4]);
                CurrentUser.Birthday = userInfo[5];
                CurrentUser.Gender = userInfo[6];
                CurrentUser.Password = userInfo[7];
                CurrentUser.Option = userInfo[8];
                CurrentUser.numberOfEntries = Integer.parseInt(userInfo[9]);

                if(CurrentUser.Option.equals("y")){
                    Intent intent = new Intent(this, HomePageActivity.class);
                    intent.putExtra("CurrentUser", CurrentUser);
                    startActivity(intent);
                }

            } catch (FileNotFoundException e) {
                String error = e.getMessage();
            }
        }
    }

    public void Login(View view){
        EditText username = findViewById(R.id.Loginusername);
        EditText password = findViewById(R.id.Loginpassword);

        String usernamestring = username.getText().toString();
        String passwordstring = password.getText().toString();

        if(usernamestring.equals(CurrentUser.Name) && passwordstring.equals(CurrentUser.Password)){
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra("CurrentUser", CurrentUser);
            startActivity(intent);
        }
    }



}
