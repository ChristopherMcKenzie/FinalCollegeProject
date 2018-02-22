package com.android.fitnessapp.fragments.user_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.fragments.BaseFragment;
import com.android.fitnessapp.views.ExerciseListAdapter;
import com.android.fitnessapp.views.userviews.UserExerciseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gerard on 21/02/2018.
 */

public class MondayFragment extends BaseFragment {
    public static MondayFragment newInstance(){return new MondayFragment();}

    @BindView(R.id.user_exercise_listview_mon)
    ListView mListView;
    @BindView(R.id.user_exercise_add_button_mon)
    Button mWorkoutButton;

    private Unbinder mUnbinder;
    private BaseFragmentActivity mActivity;

    public List<UserExerciseDatabase> userExercises;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(mActivity);
    }
    public UserExerciseDatabase mUserExerciseDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monday_user, container, false);
        mUnbinder = ButterKnife.bind(getActivity(), rootView);

        //Create an arrayList to populate the database
        //and the adapter
        userExercises = new ArrayList<>();

        ExerciseListAdapter adapter = new ExerciseListAdapter(mActivity, userExercises);

        //android.database.sqlite.SQLiteException: no such table
        //Any time a table is added or dropped must update the version in the manifest


        List<UserExerciseDatabase> queryResults = new Select()
                .from(UserExerciseDatabase.class)
                .where("day = ?", "monday")
                .execute();

        mListView.setAdapter(adapter);

        return rootView;
    }

    @OnClick(R.id.user_exercise_add_button_mon)
    public void addWorkout( final EditText userInput)
    {
        //String userEmail = mSharedPreferences.getString("USER_EMAIL", "");
        mUserExerciseDatabase.day = "Monday";
        mUserExerciseDatabase.exerciseName = userInput.getText().toString();
        mUserExerciseDatabase.save();


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
