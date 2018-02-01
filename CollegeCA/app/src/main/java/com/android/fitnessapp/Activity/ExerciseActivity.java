package com.android.fitnessapp.Activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.Fragments.ArmsFragment;

/**
 * Created by Gerard on 31/01/2018.
 */

public class ExerciseActivity extends BaseFragmentActivity {
    @Override
    Fragment createFragment() {return ArmsFragment.newInstance();}
}
