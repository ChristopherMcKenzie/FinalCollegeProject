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
import com.android.fitnessapp.database.UserMealDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.android.fitnessapp.R.layout.meal_list_view;

/**
 * Created by Gerard on 09/04/2018.
 */

public class MealListAdapter extends ArrayAdapter<UserMealDatabase> {

    private static class ViewHolder
    {
        TextView name;
    }

    public MealListAdapter(@NonNull Activity context, @NonNull List<UserMealDatabase> objects)
    {
        super(context, meal_list_view, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserMealDatabase mealList = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.meal_list_view, parent, false);
            viewHolder.name = convertView.findViewById(R.id.meal_name);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(mealList.breakfast);

        return convertView;
    }
}
