package com.android.fitnessapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fitnessapp.R;

/**
 * Created by Gerard on 15/01/2018.
 */

public class ExercisesHomeFragment extends BaseFragment
{
    public static ExercisesHomeFragment newInstance()
    {
        return new ExercisesHomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_excercise_home, container,false);

        return rootView;
    }
}
