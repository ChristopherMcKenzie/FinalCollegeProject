package com.android.fitnessapp.views.userviews;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.fitnessapp.R;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.database.UserMealDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerard on 17/03/2018.
 */

public class UserMealListAdapter extends ArrayAdapter<UserMealDatabase> {

    private static class ViewHolder
    {
        TextView breakfast;
    }

    public UserMealListAdapter(@NonNull Context context, @NonNull List<UserMealDatabase> objects) {
        super(context, R.layout.meal_list_view, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        UserMealDatabase mealUserList = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView.findViewById(R.layout.fragment_meal_home);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.meal_list_view, parent, false);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            viewHolder.breakfast.setText(mealUserList.breakfast);
            //   viewHolder.lunch.setText(mealUserList.lunch);
            //   viewHolder.dinner.setText(mealUserList.dinner);
            //   viewHolder.supper.setText(mealUserList.supper);
        }catch(NullPointerException ex)
        {
            Log.d("NullPointer", viewHolder.breakfast.toString() + " +  " +mealUserList.breakfast);
        }
        return convertView;
    }
}
