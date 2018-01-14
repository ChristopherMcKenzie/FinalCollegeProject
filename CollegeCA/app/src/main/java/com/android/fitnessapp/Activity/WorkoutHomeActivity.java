package com.android.fitnessapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.android.fitnessapp.Fragments.*;
/**
 * Created by Gerard on 13/01/2018.
 */

public class WorkoutHomeActivity extends BaseFragmentActivity {
    @Override
    Fragment createFragment() {
        return WorkoutHomeFragment.newInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
