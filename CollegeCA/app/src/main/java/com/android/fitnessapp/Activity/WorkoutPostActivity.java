package com.android.fitnessapp.Activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.Fragments.WorkoutPostFragment;

/**
 * Created by Gerard on 09/02/2018.
 */

public class WorkoutPostActivity extends BaseFragmentActivity{
    @Override
    Fragment createFragment() {
        return WorkoutPostFragment.newInstance();
    }

}
