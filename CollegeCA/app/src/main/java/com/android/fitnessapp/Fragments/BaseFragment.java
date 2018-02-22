package com.android.fitnessapp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.activeandroid.ActiveAndroid;
import com.android.fitnessapp.utils.Constants;

import rx.subscriptions.CompositeSubscription;

public class BaseFragment extends Fragment{

    protected CompositeSubscription compositeSubscription;
    protected SharedPreferences mSharedPreferences;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(getActivity());
        compositeSubscription = new CompositeSubscription();
        mSharedPreferences = getActivity().getSharedPreferences(Constants.USER_INFO_KEY, Context.MODE_PRIVATE);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //lets retry the subscription
        compositeSubscription.clear();
    }
}
