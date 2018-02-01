package com.android.fitnessapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.fitnessapp.Activity.BaseFragmentActivity;
import com.android.fitnessapp.Activity.ExerciseActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.Utils.ExerciseHomeList;
import com.android.fitnessapp.Views.ExerciseViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Gerard on 15/01/2018.
 */

public class ExercisesHomeFragment extends BaseFragment
{
    public static ExercisesHomeFragment newInstance()
    {
        return new ExercisesHomeFragment();
    }

    /*@BindView(R.id.exercise_listview)
    ListView mlistView;
*/
    @BindView(R.id.fragment_exercise_tabLayout)
    TabLayout mTablayout;

    @BindView(R.id.fragment_exercise_viewPager)
    ViewPager mViewPager;

    List<ExerciseHomeList> exerciseHomeList;

    private BaseFragmentActivity mActivity;

    Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise, container,false);

        mUnBinder = ButterKnife.bind(this, rootView);




        mActivity = (BaseFragmentActivity) getActivity();

        ExerciseViewPageAdapter exerciseViewPageAdapter = new ExerciseViewPageAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(exerciseViewPageAdapter);
        mTablayout.setupWithViewPager(mViewPager);


  //      mlistView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }
}
