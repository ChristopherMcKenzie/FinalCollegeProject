package com.android.fitnessapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.utils.ExerciseList;
import com.android.fitnessapp.views.ExerciseListAdapter;

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

    public List<ExerciseList> exerciseList;
    private BaseFragmentActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arms, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        ActiveAndroid.initialize(mActivity);
        final TextView muscle = (TextView) rootView.findViewById(R.id.list_view_muscle);
        final TextView listView = (TextView) rootView.findViewById(R.id.list_view_exercise_text);

        exerciseList = new ArrayList<>();

        UserExerciseDatabase userExerciseDatabase = new UserExerciseDatabase();



        ArrayList<UserExerciseDatabase> items = new ArrayList<>();
        ExerciseListAdapter adapter = new ExerciseListAdapter(mActivity, items);

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
