package com.android.fitnessapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.fitnessapp.Fragments.HomeFragment;

/**
 * Created by Gerard on 12/01/2018.
 */

public class HomeActivity extends BaseFragmentActivity {
    @Override
    Fragment createFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
