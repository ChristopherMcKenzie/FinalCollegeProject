package com.android.fitnessapp.views;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.fitnessapp.R;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Gerard on 01/02/2018.
 */

public class ExerciseListAdapter extends ArrayAdapter<UserExerciseDatabase> {

    private static class ViewHolder
    {
        TextView title;
        TextView reps;
        TextView sets;
    }

    public ExerciseListAdapter(@NonNull Activity context, @NonNull List<UserExerciseDatabase> objects) {
        super(context, R.layout.exercise_list_view, objects);


    }

    //View holders allows the items to be cached.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserExerciseDatabase exerciseList = getItem(position);

        ViewHolder viewHolder;
        //Check if the view is being reused or not
        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.exercise_list_view, parent, false);
            viewHolder.reps = convertView.findViewById(R.id.list_view_exercise_reps);
            viewHolder.title = convertView.findViewById(R.id.list_view_muscle);
            viewHolder.sets = convertView.findViewById(R.id.list_view_exercise_sets);
            //Cache it here
            convertView.setTag(viewHolder);
//        String userEmail = sharedPreferences.getString(Constants.USER_EMAIL, "");

        }
        else
        {
            //Get the cache
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //viewHolder.desc.setText(exerciseList.email);
        viewHolder.title.setText(exerciseList.name);
        viewHolder.reps.setText(exerciseList.reps);
        viewHolder.sets.setText(exerciseList.sets);

        return convertView;
    }
}