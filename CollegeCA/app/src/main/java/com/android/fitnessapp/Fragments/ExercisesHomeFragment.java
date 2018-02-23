package com.android.fitnessapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.utils.ExerciseList;
import com.android.fitnessapp.views.ExerciseViewPageAdapter;

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

    List<ExerciseList> exerciseList;

    private BaseFragmentActivity mActivity;

    Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_view_pager, container,false);

        mUnBinder = ButterKnife.bind(this, rootView);


        mActivity = (BaseFragmentActivity) getActivity();

        ExerciseViewPageAdapter exerciseViewPageAdapter =
                new ExerciseViewPageAdapter(getActivity().getSupportFragmentManager());
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
