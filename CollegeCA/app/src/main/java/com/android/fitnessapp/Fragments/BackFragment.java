package com.android.fitnessapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.views.ExerciseListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Gerard on 31/01/2018.
 */

public class BackFragment extends BaseFragment {

    public static BackFragment newInstance(){return new BackFragment();}

    @BindView(R.id.list_view_back)
    ListView mListView;

    private Unbinder mUnbinder;
    private BaseFragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_back, container, false);
        mUnbinder = ButterKnife.bind(this, view);



        ArrayList<UserExerciseDatabase> items = new ArrayList<>();

        ExerciseListAdapter adapter = new ExerciseListAdapter(mActivity, items);

        mListView.setAdapter(adapter);

        return view;
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
