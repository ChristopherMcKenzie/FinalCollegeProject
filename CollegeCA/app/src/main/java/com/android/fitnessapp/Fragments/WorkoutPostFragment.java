package com.android.fitnessapp.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.fitnessapp.Activity.BaseFragmentActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Gerard on 09/02/2018.
 */

public class WorkoutPostFragment extends BaseFragment{

    public static WorkoutPostFragment newInstance(){return new WorkoutPostFragment();}

    @BindView(R.id.first_monday)
    EditText mEditTextMonday;
    @BindView(R.id.test)
    TextView testTextView;
    @BindView(R.id.add_monday_button)
    Button workoutMondayButton;

    private BaseFragmentActivity mActivity;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout_post, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);


        return rootView;
    }


/*
    Use a string array <item> have presets, let the user change the presets
 */
    public void editWorkoutMonday(View v)
    {
        String mondayWorkout = mEditTextMonday.toString();
        testTextView.setText(mondayWorkout);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseFragmentActivity) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
}
