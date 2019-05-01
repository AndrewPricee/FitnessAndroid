package com.example.workoutapp20;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {
Person CurrentUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary,this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        CurrentUser = (Person) getIntent().getSerializableExtra("CurrentUser");

        TextView usersname = findViewById(R.id.UsersName);
        TextView usersweight = findViewById(R.id.UsersWeight);
        TextView usersheight = findViewById(R.id.UsersHeight);
        TextView usersbirthday = findViewById(R.id.UsersBirthday);
        TextView usersgender = findViewById(R.id.UsersGender);
        TextView usersgoalweight = findViewById(R.id.UsersGoalWeight);

        usersname.setText(CurrentUser.Name);
        usersweight.setText(String.valueOf(CurrentUser.weight));
        usersheight.setText(String.valueOf(CurrentUser.height));
        usersbirthday.setText(CurrentUser.Birthday);
        usersgender.setText(CurrentUser.Gender);
        usersgoalweight.setText(String.valueOf(CurrentUser.GoalWeight));
    }

    public void GoHome(View view){
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtra("CurrentUser", CurrentUser);
        startActivity(intent);
    }

    public void GoSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("CurrentUser", CurrentUser);
        startActivity(intent);
    }
}
