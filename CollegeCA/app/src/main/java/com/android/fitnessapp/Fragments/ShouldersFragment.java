package com.android.fitnessapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fitnessapp.R;

/**
 * Created by Gerard on 31/01/2018.
 */

public class ShouldersFragment extends BaseFragment {
    public static ShouldersFragment newInstance() {return new ShouldersFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoulders, container, false);

        return view;
    }
}
