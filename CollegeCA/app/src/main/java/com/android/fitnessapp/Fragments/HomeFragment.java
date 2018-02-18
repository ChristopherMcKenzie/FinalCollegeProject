package com.android.fitnessapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.fitnessapp.Activity.HomeActivity;
import com.android.fitnessapp.Activity.WorkoutHomeActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gerard on 12/01/2018.
 */

public class HomeFragment extends BaseFragment {



    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    private Unbinder mUnbinder;

    @BindView(R.id.home_image_workouts)
    ImageView workoutImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }
}
