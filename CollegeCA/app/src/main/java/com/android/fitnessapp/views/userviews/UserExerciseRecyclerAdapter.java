package com.android.fitnessapp.views.userviews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.database.UserExerciseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerard on 25/02/2018.
 */

public class UserExerciseRecyclerAdapter extends RecyclerView.Adapter{
    private BaseFragmentActivity mActivity;
    private List<UserExerciseDatabase> userDatabase;
    private LayoutInflater mInflator;


    public UserExerciseRecyclerAdapter(BaseFragmentActivity mActivity)
    {
        this.mActivity = mActivity;

        mInflator = mActivity.getLayoutInflater();
        userDatabase = new ArrayList<>();
    }

    public void setExercises(List<UserExerciseDatabase> exercises)
    {
        userDatabase.clear();
        userDatabase.addAll(exercises);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.user_exercise_list_view, parent, false);
        return new UserExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((UserExerciseViewHolder) holder).populate(mActivity, userDatabase.get(position));
    }

    public List<UserExerciseDatabase> getUserExercise()
    {
        return userDatabase;
    }

    @Override
    public int getItemCount() {
        return userDatabase.size();
    }
}
