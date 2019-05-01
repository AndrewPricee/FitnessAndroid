package com.example.workoutapp20;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    Person CurrentUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CurrentUser = (Person) getIntent().getSerializableExtra("CurrentUser");
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary,this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        TextView goalweight = findViewById(R.id.textView2);
        TextView currweight = findViewById(R.id.textView4);
        TextView currBMI = findViewById(R.id.CurrentBMIValue);

        double BMI = Math.round((CurrentUser.weight / ((CurrentUser.height /100) * (CurrentUser.height /100)))*100) / 100;

        if( 24.9 >= BMI && BMI >= 18.5){
            currBMI.setTextColor(getResources().getColor(R.color.color1));
        }else{
            currBMI.setTextColor(getResources().getColor(R.color.color2));
        }

        currBMI.setText(String.valueOf(BMI));
        goalweight.setText(String.valueOf(CurrentUser.GoalWeight));
        currweight.setText(String.valueOf(CurrentUser.weight));
    }

    public void ViewUser(View view){
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtra("CurrentUser", CurrentUser);
        startActivity(intent);
    }
}
