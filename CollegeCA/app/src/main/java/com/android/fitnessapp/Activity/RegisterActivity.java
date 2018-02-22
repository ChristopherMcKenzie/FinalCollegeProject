package com.android.fitnessapp.activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.fragments.RegisterFragment;

/**
 * Created by Gerard on 11/01/2018.
 */

public class RegisterActivity extends BaseFragmentActivity {


    @Override
    Fragment createFragment() {
        return RegisterFragment.newInstance();
    }
}
