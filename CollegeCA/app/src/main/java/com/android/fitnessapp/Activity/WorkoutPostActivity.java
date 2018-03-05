package com.android.fitnessapp.activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.fragments.user_exercise_fragments.WorkoutPostFragment;

/**
 * Created by Gerard on 09/02/2018.
 */

public class WorkoutPostActivity extends BaseFragmentActivity{
    @Override
    Fragment createFragment() {
        return WorkoutPostFragment.newInstance();
    }



}
