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
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.fragments.BaseFragment;
import com.android.fitnessapp.utils.ExerciseList;
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
    @BindView(R.id.user_exercise_edittext_mon)
    EditText userExercise;


    private Unbinder mUnbinder;
    public UserExerciseDatabase userExerciseDatabase;
    private BaseFragmentActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private ExerciseListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monday_user, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        ActiveAndroid.initialize(mActivity);

        userExerciseDatabase = new UserExerciseDatabase();

        //Create an arrayList of items for the database
        ArrayList<UserExerciseDatabase> items = new ArrayList<>();
        adapter = new ExerciseListAdapter(mActivity, items);

        //Create a string called day holding the name of the day
        //Create a query for the database to handle then print it out on screen

        String day = "Monday";
        List<UserExerciseDatabase> results = new Select()
                .from(UserExerciseDatabase.class)
                .where("day = ?", day )
                .execute();
        adapter.addAll(results);

        adapter.notifyDataSetChanged();

        mListView.setAdapter(adapter);



        return rootView;
    }

    @OnClick(R.id.user_exercise_add_button_mon)
    public void addWorkoutMonday() {
        Toast.makeText(mActivity, "Exercise Added", Toast.LENGTH_SHORT).show();
        saveExercise(userExercise);

    }

    public void saveExercise(EditText userInput)
    {
        String exercise = userInput.getText().toString();
        userExerciseDatabase.exerciseName = exercise;
        userExerciseDatabase.day = "Monday";
        userExerciseDatabase.save();
        try
        {
            Thread.sleep(1000);
            adapter.notifyDataSetChanged();
        }
        catch(InterruptedException ex)
        {
            ex.getMessage();
        }

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
