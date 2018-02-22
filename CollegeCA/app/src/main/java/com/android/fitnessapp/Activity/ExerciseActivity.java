package com.android.fitnessapp.activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.fragments.ArmsFragment;

/**
 * Created by Gerard on 31/01/2018.
 */

public class ExerciseActivity extends BaseFragmentActivity {
    @Override
    Fragment createFragment() {return ArmsFragment.newInstance();}
}
