package com.android.fitnessapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.android.fitnessapp.Fragments.HomeFragment;
import com.android.fitnessapp.Utils.Constants;

import butterknife.ButterKnife;
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
        Toast.makeText(this, Constants.USER_EMAIL, Toast.LENGTH_SHORT).show();

    }

    public void startWorkoutActivity(View v)
    {
        Intent i = new Intent(this, WorkoutHomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }




}
