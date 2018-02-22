package com.android.fitnessapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fitnessapp.activity.WorkoutPostActivity;
import com.android.fitnessapp.R;

import butterknife.OnClick;

/**
 * Created by Gerard on 13/01/2018.
 */

public class WorkoutHomeFragment extends BaseFragment {
    public static WorkoutHomeFragment newInstance(){
        return new WorkoutHomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout_home, container, false);

        return rootView;
    }

    @OnClick(R.id.add_workout_button)
    public void setWorkoutButton()
    {
        startActivity(new Intent(getActivity(), WorkoutPostActivity.class));
    }





}
