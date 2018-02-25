package com.android.fitnessapp.views.userviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.database.UserExerciseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gerard on 25/02/2018.
 */

public class UserExerciseViewHolder extends ViewHolder{

    @BindView(R.id.user_exercise_name)
    TextView exerciseName;
    @BindView(R.id.user_exercise_reps)
    TextView exerciseReps;
    @BindView(R.id.user_exercise_sets)
    TextView exerciseSets;


    public UserExerciseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(BaseFragmentActivity mActivity, UserExerciseDatabase database)
    {
        exerciseName.setText(database.exerciseName);
//        exerciseReps.setText(database.reps);
    }
}
