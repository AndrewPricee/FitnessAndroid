package com.example.workoutapp20;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CreateUserActivity extends AppCompatActivity {
    Person CurrentUser;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary,this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void CreateUser(View view){
        EditText name = findViewById(R.id.NewUserName);
        EditText currweight = findViewById(R.id.NewUserCurrWeight);
        EditText currheight = findViewById(R.id.NewUserCurrHeight);
        EditText goalweight = findViewById(R.id.NewUserGoalWeight);
        EditText birthday = findViewById(R.id.NewUserBirthday);
        Switch gender = findViewById(R.id.switch1);
        Switch option = findViewById(R.id.switch2);
        EditText password = findViewById(R.id.NewUserPassword);

        String NewUserName = name.getText().toString();
        double NewUserCurrWeight = Double.parseDouble(currweight.getText().toString());
        double NewUserCurrHeight = Double.parseDouble(currheight.getText().toString());
        double NewUserGoalWeight = Double.parseDouble(goalweight.getText().toString());
        String NewUserBirthday = birthday.getText().toString();
        String NewUserPassword = password.getText().toString();
        String NewUserGender;
        String NewUserOption;
        int NewUserNumberOfEntires = 0;

        if(gender.isChecked()){
            NewUserGender = "female";
        }else{
            NewUserGender = "male";
        }

        if(option.isChecked()){
            NewUserOption = "y";
        }else{
            NewUserOption = "n";
        }

        String[] birthdayArray = NewUserBirthday.split("/");
        int birthdayYear = Integer.parseInt(birthdayArray[2]);
        int birthdayMonth = Integer.parseInt(birthdayArray[1]);
        int birthdayDay = Integer.parseInt(birthdayArray[0]);

        Date today = Calendar.getInstance().getTime();                          //Today's date
        Calendar c = Calendar.getInstance();
        c.set(birthdayYear, birthdayMonth - 1, birthdayDay, 0, 0);
        long diff = today.getTime();
        long diff2 = c.getTime().getTime();
        long thediff = diff - diff2;
        int diffInyears = (int)Math.floor(((double)(thediff) / (1000 * 60 * 60 * 24 )/365));


        Person NewUser = new Person();

        NewUser.Name = NewUserName;
        NewUser.weight = NewUserCurrWeight;
        NewUser.height = NewUserCurrHeight;
        NewUser.Gender = NewUserGender;
        NewUser.Birthday = NewUserBirthday;
        NewUser.Age =  diffInyears ;
        NewUser.GoalWeight = NewUserGoalWeight;
        NewUser.Password = NewUserPassword;
        NewUser.Option = NewUserOption;
        NewUser.numberOfEntries = NewUserNumberOfEntires;

        File file = new File(getApplicationContext().getFilesDir(),"personInfo.txt");

        FileOutputStream outputStream;
        String FileContents = NewUser.Name + "," + NewUser.height + "," + NewUser.weight + "," + NewUser.GoalWeight + "," + NewUser.Age + "," + NewUser.Birthday + "," + NewUser.Gender + "," + NewUser.Password + "," + NewUser.Option + "," + NewUser.numberOfEntries;

        try {
            outputStream = openFileOutput("personInfo.txt", Context.MODE_PRIVATE);
            outputStream.write(FileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
