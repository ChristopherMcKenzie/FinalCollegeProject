package com.android.fitnessapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.fitnessapp.R;

import butterknife.BindView;

/**
 * Created by Gerard on 13/01/2018.
 */

public class WorkoutHomeFragment extends BaseFragment {
    public static WorkoutHomeFragment newInstance(){
        return new WorkoutHomeFragment();
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.content_frame)
    FrameLayout mFrameLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout_home, container, false);

        return rootView;
    }





}
