package com.android.fitnessapp.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.android.fitnessapp.Activity.BaseFragmentActivity;
import com.android.fitnessapp.Utils.Constants;
import com.google.firebase.database.FirebaseDatabase;

import rx.subscriptions.CompositeSubscription;

public class BaseFragment extends Fragment{

    protected CompositeSubscription compositeSubscription;
    protected SharedPreferences mSharedPreferences;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
