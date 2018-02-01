package com.android.fitnessapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.fitnessapp.Activity.BaseFragmentActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.Utils.ExerciseHomeList;
import com.android.fitnessapp.Views.ExerciseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Gerard on 31/01/2018.
 */

public class ArmsFragment extends BaseFragment {

    public static ArmsFragment newInstance()
    {
        return new ArmsFragment();
    }

    @BindView(R.id.exercise_listview)
    ListView mListView;

    private Unbinder mUnbinder;

    public List<ExerciseHomeList> exerciseHomeList;
    private BaseFragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_arms, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        exerciseHomeList = new ArrayList<>();

        exerciseHomeList.add(new ExerciseHomeList("Biceps \nBicep curl", "Take a bar bell or two dumbbells." +
                "\nHold your arms down straight and bend your elbow" +
                ",\nbringing the weight upwards, bring the weight up as much as you can making sure your" +
                "\nbiceps are contracted, then control the weight back down to the starting position."));
        exerciseHomeList.add(new ExerciseHomeList("Triceps \nSkullcrusher", "Bicep"));
        exerciseHomeList.add(new ExerciseHomeList("Forearms/Biceps \nZottomon Curl", "Chest"));
        exerciseHomeList.add(new ExerciseHomeList("Triceps\n Rope pulldown", "Back"));
        exerciseHomeList.add(new ExerciseHomeList("Biceps\n Hammer Curl", ""));

            ExerciseListAdapter adapter = new ExerciseListAdapter(mActivity, exerciseHomeList);

            mListView.setAdapter(adapter);

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
