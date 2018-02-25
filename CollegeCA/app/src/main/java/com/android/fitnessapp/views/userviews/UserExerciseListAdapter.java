package com.android.fitnessapp.views.userviews;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.fitnessapp.R;
import com.android.fitnessapp.database.UserExerciseDatabase;

import java.util.List;

/**
 * Created by Gerard on 23/02/2018.
 */

public class UserExerciseListAdapter extends ArrayAdapter<UserExerciseDatabase> {

    private static class ViewHolder
    {
        TextView name;
    }

    public UserExerciseListAdapter(@NonNull Context context, @NonNull List<UserExerciseDatabase> objects) {
        super(context, R.layout.exercise_list_view, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserExerciseDatabase exerciseUserList = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.exercise_list_view, parent, false);
            //viewHolder.name = convertView.findViewById(R.id.user_exercise_textview);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(exerciseUserList.exerciseName);

        return convertView;
    }
}
