package com.android.fitnessapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    public void startExerciseHome(View v)
    {
        Intent i = new Intent(getApplicationContext(), ExercisesHomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}
