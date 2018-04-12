package com.android.fitnessapp.fragments.user_exercise_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.fragments.BaseFragment;
import com.android.fitnessapp.views.UserExerciseViewPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Gerard on 09/02/2018.
 */

public class WorkoutPostFragment extends BaseFragment {

    public static WorkoutPostFragment newInstance(){return new WorkoutPostFragment();}

    @BindView(R.id.fragment_user_exercise_tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.fragment_user_exercise_viewPager)
    ViewPager mViewPager;

    private BaseFragmentActivity mActivity;
    private Unbinder mUnbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_exercise, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        mActivity = (BaseFragmentActivity) getActivity();

        UserExerciseViewPageAdapter userExerciseViewPageAdapter =
                new UserExerciseViewPageAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(userExerciseViewPageAdapter);
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
