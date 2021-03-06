package com.android.fitnessapp.fragments.user_exercise_fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.fragments.BaseFragment;
import com.android.fitnessapp.views.ExerciseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gerard on 21/02/2018.
 */

public class SundayFragment extends BaseFragment {
    public static SundayFragment newInstance(){return new SundayFragment();}

    @BindView(R.id.user_exercise_listview_sun)
    ListView mListView;



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
        View rootView = inflater.inflate(R.layout.fragment_sunday_user, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        ActiveAndroid.initialize(mActivity);

        userExerciseDatabase = new UserExerciseDatabase();

        //Create an arrayList of items for the database
        ArrayList<UserExerciseDatabase> items = new ArrayList<>();
        adapter = new ExerciseListAdapter(mActivity, items);

        //Create a string called day holding the name of the day
        //Create a query for the database to handle then print it out on screen

        String day = "Sunday";
        List<UserExerciseDatabase> results = new Select()
                .from(UserExerciseDatabase.class)
                .where("day = ?", day )
                .execute();
        adapter.addAll(results);

        adapter.notifyDataSetChanged();

        mListView.setAdapter(adapter);




        return rootView;
    }


    //Shows a pop up for the user to add an exercise
    @OnClick(R.id.add_workout_dialog_button_sun)
    public void showExerciseAddDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_exercise_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Add exercise to your workout");

        final EditText exerciseNameEditText = (EditText) dialogView.findViewById(R.id.exercisename_edit_text_mon);
        final EditText exerciseRepEditText = (EditText) dialogView.findViewById(R.id.exercisereps_edit_text_mon);
        final EditText exerciseSetsEditText = (EditText) dialogView.findViewById(R.id.exercisesets_edit_text_mon);

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userExerciseDatabase.name = exerciseNameEditText.getText().toString();
                userExerciseDatabase.reps = exerciseRepEditText.getText().toString();
                userExerciseDatabase.sets = exerciseSetsEditText.getText().toString();
                userExerciseDatabase.save();
                adapter.clear();
                Toast.makeText(mActivity, "Exercise saved", Toast.LENGTH_SHORT).show();
                String day = "Sunday";
                List<UserExerciseDatabase> results = new Select()
                        .from(UserExerciseDatabase.class)
                        .where("day = ?", day )
                        .execute();
                adapter.addAll(results);

                adapter.notifyDataSetChanged();

                mListView.setAdapter(adapter);

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
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
