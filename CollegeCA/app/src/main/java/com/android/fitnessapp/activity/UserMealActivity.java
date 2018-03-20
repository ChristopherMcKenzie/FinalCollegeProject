package com.android.fitnessapp.activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.fragments.user_meal_fragments.FridayMealFragment;
import com.android.fitnessapp.fragments.user_meal_fragments.MealPostFragment;

/**
 * Created by Gerard on 17/03/2018.
 */

public class UserMealActivity extends BaseFragmentActivity {
    @Override
    Fragment createFragment() {
        return MealPostFragment.newInstance();
    }


}
