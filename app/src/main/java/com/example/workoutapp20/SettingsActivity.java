package com.example.workoutapp20;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.io.FileOutputStream;

public class SettingsActivity extends AppCompatActivity {
    Person CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary,this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        CurrentUser = (Person) getIntent().getSerializableExtra("CurrentUser");

        EditText name = findViewById(R.id.UpdateUserName);
        EditText birthday = findViewById(R.id.UpdateUserBirthday);
        EditText goal = findViewById(R.id.UpdateUserGoalWeight);
        Switch gender = findViewById(R.id.UpdateUserGenderSwitch);

        name.setText(CurrentUser.Name);
        birthday.setText(CurrentUser.Birthday);
        goal.setText(String.valueOf(CurrentUser.GoalWeight));


        if(CurrentUser.Gender.equals("male")){
            gender.setChecked(false);
        }else{
            gender.setChecked(true);
        }
    }


    public void UpdateInfo(View view){
        EditText name = findViewById(R.id.UpdateUserName);
        EditText birthday = findViewById(R.id.UpdateUserBirthday);
        EditText goal = findViewById(R.id.UpdateUserGoalWeight);
        Switch gender = findViewById(R.id.UpdateUserGenderSwitch);

        String UpdateUserName = name.getText().toString();
        String UpdateUserBirthday = birthday.getText().toString();
        String UpdateUserGoal = goal.getText().toString();
        String UpdateUserGender;

        if(gender.isChecked()){
            UpdateUserGender = "female";
        }else{
            UpdateUserGender = "male";
        }

        CurrentUser.Gender = UpdateUserGender;
        CurrentUser.Name = UpdateUserName;
        CurrentUser.GoalWeight = Double.parseDouble(UpdateUserGoal);
        CurrentUser.Birthday = UpdateUserBirthday;

        FileOutputStream outputStream;
        String FileContents = CurrentUser.Name + "," + CurrentUser.height + "," + CurrentUser.weight + "," + CurrentUser.GoalWeight + "," + CurrentUser.Age + "," + CurrentUser.Birthday + "," + CurrentUser.Gender + "," + CurrentUser.Password + "," + CurrentUser.Option + "," + CurrentUser.numberOfEntries;

        try {
            outputStream = openFileOutput("personInfo.txt", Context.MODE_PRIVATE);
            outputStream.write(FileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GoBack(View view){
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtra("CurrentUser", CurrentUser);
        startActivity(intent);
    }

}
