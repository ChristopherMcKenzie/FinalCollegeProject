package com.android.fitnessapp.fragments.user_meal_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.fragments.BaseFragment;
import com.android.fitnessapp.views.UserMealViewPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Gerard on 17/03/2018.
 */

public class MealPostFragment extends BaseFragment {
    public static MealPostFragment newInstance(){return new MealPostFragment();}

    @BindView(R.id.fragment_user_meal_tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.fragment_user_meal_viewPager)
    ViewPager mViewPager;

    private BaseFragmentActivity mActivity;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_meal, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        mActivity = (BaseFragmentActivity) getActivity();

        UserMealViewPageAdapter userMealViewPageAdapter =
                new UserMealViewPageAdapter(getActivity().getSupportFragmentManager());

        mViewPager.setAdapter(userMealViewPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        return rootView;

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
