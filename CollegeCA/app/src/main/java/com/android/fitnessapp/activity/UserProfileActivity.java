package com.android.fitnessapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.fitnessapp.R;
import com.android.fitnessapp.fragments.UserProfileFragment;

import java.text.DecimalFormat;

import static java.lang.Math.round;

/**
 * Created by Gerard on 12/03/2018.
 */

public class UserProfileActivity extends BaseFragmentActivity {

    private SharedPreferences mSharedPreferences;

    @Override
    Fragment createFragment() {
        return UserProfileFragment.newInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = getApplicationContext().getSharedPreferences("USER_INFO_KEY", Context.MODE_PRIVATE);

    }

    public void getUserBMI(View view)
    {

        String loadHeight = mSharedPreferences.getString("USER_HEIGHT", "");
        String loadWeight = mSharedPreferences.getString("USER_WEIGHT", "");
        float userHeight = Float.parseFloat(loadHeight)/100;
        float userWeight = Float.parseFloat(loadWeight);


        float userBMI = (userWeight/userHeight)/userHeight;

        String userWeightType;

        if(userBMI < 18.5)
        {
            userWeightType = " You are underweight try to get a BMI of 18.5";
        }
        else if(userBMI < 25 && userBMI >= 18.5)
        {
            userWeightType = "You are a Healthy weight";
        }
        else
        {
            userWeightType = " You are overweight try to get a BMI of under 25";
        }

        DecimalFormat df = new DecimalFormat("##.##");

        String finalBMI = String.valueOf(df.format(userBMI));

        TextView userBMIText = findViewById(R.id.user_profile_bmi);



        userBMIText.setText("A BMI of " + finalBMI + "." + userWeightType);
    }

    public void saveHeight(View view)
    {
        EditText userHeight = findViewById(R.id.user_profile_height);

        SharedPreferences.Editor edit = mSharedPreferences.edit().putString("USER_HEIGHT", userHeight.getText().toString());
        edit.apply();
    }

    public void saveWeight(View view)
    {
        EditText userWeight = findViewById(R.id.user_profile_weight);

        SharedPreferences.Editor edit = mSharedPreferences.edit().putString("USER_WEIGHT", userWeight.getText().toString());
        edit.apply();
    }
}
