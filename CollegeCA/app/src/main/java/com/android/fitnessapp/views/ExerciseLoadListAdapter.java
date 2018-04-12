package com.android.fitnessapp.views;

import android.app.Activity;
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
import com.android.fitnessapp.database.ExerciseDatabase;
import com.android.fitnessapp.database.UserExerciseDatabase;

import java.util.List;

/**
 * Created by Gerard on 12/04/2018.
 */

public class ExerciseLoadListAdapter extends ArrayAdapter<ExerciseDatabase>{

    private static class ViewHolder
    {
        TextView type;
        TextView name;
    }

    public ExerciseLoadListAdapter(@NonNull Activity context, @NonNull List<ExerciseDatabase> objects) {
        super(context, R.layout.exercise_list_view, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExerciseDatabase exerciseDatabase = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.exercise_list_view, parent, false);
            viewHolder.type = convertView.findViewById(R.id.list_view_exercise_reps);
            viewHolder.name = convertView.findViewById(R.id.list_view_muscle);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(exerciseDatabase.name);
        viewHolder.type.setText(exerciseDatabase.type);

        return convertView;
    }
}
