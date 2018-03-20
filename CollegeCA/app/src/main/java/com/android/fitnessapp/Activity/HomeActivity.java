package com.android.fitnessapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.android.fitnessapp.fragments.HomeFragment;
import com.android.fitnessapp.fragments.WorkoutHomeFragment;
import com.android.fitnessapp.utils.Constants;

import butterknife.Unbinder;

/**
 * Created by Gerard on 12/01/2018.
 */

public class HomeActivity extends BaseFragmentActivity {
    @Override
    Fragment createFragment() {
        return HomeFragment.newInstance();
    }

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void startWorkoutActivity(View v)
    {
        Intent i = new Intent(this, WorkoutHomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void startMealActivity(View v)
    {
        Toast.makeText(this, "dadasda", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), MealHomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }



}
