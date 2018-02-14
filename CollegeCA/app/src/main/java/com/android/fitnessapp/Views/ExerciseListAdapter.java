package com.android.fitnessapp.Views;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.fitnessapp.R;
import com.android.fitnessapp.Utils.ExerciseHomeList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by Gerard on 01/02/2018.
 */

public class ExerciseListAdapter extends ArrayAdapter<ExerciseHomeList> {
    private DatabaseReference mDatabaseRef;

    private static class ViewHolder
    {
        TextView title;
        TextView desc;
    }

    public ExerciseListAdapter(@NonNull Activity context, @NonNull List<ExerciseHomeList> objects) {
        super(context, R.layout.exercise_list_view, objects);


    }

    //View holders allows the items to be cached.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExerciseHomeList exerciseHomeList = getItem(position);

        ViewHolder viewHolder;
        //Check if the view is being reused or not
        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.exercise_list_view, parent, false);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.list_view_exercise_text);
            viewHolder.title = (TextView) convertView.findViewById(R.id.list_view_muscle);
            //Cache it here
            convertView.setTag(viewHolder);

        }
        else
        {
            //Get the cache
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.desc.setText(exerciseHomeList.getText());
        viewHolder.title.setText(exerciseHomeList.getMuscle());

        return convertView;
    }
}
