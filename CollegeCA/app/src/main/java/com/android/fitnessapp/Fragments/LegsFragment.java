package com.android.fitnessapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fitnessapp.R;

/**
 * Created by Gerard on 31/01/2018.
 */

public class LegsFragment extends BaseFragment {

    public static LegsFragment newInstance(){return new LegsFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_legs, container, false);

        return view;
    }
}
