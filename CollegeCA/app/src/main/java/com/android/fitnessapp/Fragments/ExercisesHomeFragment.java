package com.android.fitnessapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.fitnessapp.R;

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

    @BindView(R.id.exercise_listview)
    ListView mlistView;

    Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_home, container,false);

        mUnBinder = ButterKnife.bind(this, rootView);

        String[] exercises = {"Arms", "Shoulders", "Chest", "Back", "Legs"};

        ArrayAdapter<String> listViewAdapater = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                exercises
        );

        mlistView.setAdapter(listViewAdapater);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }
}
