package com.android.fitnessapp.views.userviews;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.fitnessapp.R;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.views.ExerciseListAdapter;

import java.util.List;

/**
 * Created by Gerard on 21/02/2018.
 */

public class UserExerciseListAdapter extends ArrayAdapter<UserExerciseDatabase> {

    public static class ViewHolder
    {
        TextView exercise;
    }

    public UserExerciseListAdapter(@NonNull Context context, @NonNull List<UserExerciseDatabase> objects) {
        super(context, R.layout.user_exercise_list_view, objects);
    }


    //Allows the items to be cached without reloading it over again


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserExerciseDatabase userExerciseList = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.user_exercise_list_view, parent, false);
            viewHolder.exercise = convertView.findViewById(R.id.user_exercise_textview);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.exercise.setText(userExerciseList.exerciseName);

        return convertView;

    }
}
