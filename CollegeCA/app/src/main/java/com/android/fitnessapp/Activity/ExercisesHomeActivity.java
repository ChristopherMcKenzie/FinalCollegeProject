package com.android.fitnessapp.activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.fragments.ExercisesHomeFragment;

/**
 * Created by Gerard on 15/01/2018.
 */

public class ExercisesHomeActivity extends BaseFragmentActivity{
    @Override
    Fragment createFragment() {
        return ExercisesHomeFragment.newInstance();
    }
}
