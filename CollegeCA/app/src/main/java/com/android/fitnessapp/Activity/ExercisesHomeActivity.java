package com.android.fitnessapp.Activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.Fragments.ExercisesHomeFragment;

/**
 * Created by Gerard on 15/01/2018.
 */

public class ExercisesHomeActivity extends BaseFragmentActivity{
    @Override
    Fragment createFragment() {
        return ExercisesHomeFragment.newInstance();
    }
}
