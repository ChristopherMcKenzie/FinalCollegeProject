package com.android.fitnessapp.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.fitnessapp.fragments.BaseFragment;
import com.android.fitnessapp.fragments.MealHomeFragment;

/**
 * Created by Gerard on 17/03/2018.
 */

public class MealHomeActivity extends BaseFragmentActivity {
    @Override
    Fragment createFragment() {
        return MealHomeFragment.newInstance();
    }

    public void startUserMealActivity(View v)
    {
        Intent i = new Intent(this, UserMealActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
