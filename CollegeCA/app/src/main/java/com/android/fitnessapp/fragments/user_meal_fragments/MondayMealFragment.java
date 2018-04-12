package com.android.fitnessapp.fragments.user_meal_fragments;

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
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.database.UserMealDatabase;
import com.android.fitnessapp.fragments.BaseFragment;
import com.android.fitnessapp.views.MealListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gerard on 17/03/2018.
 */

public class MondayMealFragment extends BaseFragment {

    public static MondayMealFragment newInstance(){return new MondayMealFragment();}


    @BindView(R.id.meal_monday_user_list_view)
    ListView mListView;

    private Unbinder mUnbinder;
    public UserMealDatabase userMealDatabase;
    private BaseFragmentActivity mActivity;

    private MealListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monday_user_meal, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        ActiveAndroid.initialize(mActivity);

        userMealDatabase = new UserMealDatabase();

        ArrayList items = new ArrayList();

        adapter = new MealListAdapter(mActivity, items);


        String day = "Monday";

        List<UserMealDatabase> result = new Select()
                .from(UserMealDatabase.class)
                .where("day = ?", day)
                .execute();



        adapter.addAll(result);

        adapter.notifyDataSetChanged();

        mListView.setAdapter(adapter);

        return rootView;

    }


    @OnClick(R.id.add_meal_dialog_button_mon)
    public void showExerciseAddDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_meal_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Add exercise to your workout");

        final EditText mealBreakfast = (EditText) dialogView.findViewById(R.id.meal_edit_text_breakfast);
        final EditText mealLunch = (EditText) dialogView.findViewById(R.id.meal_edit_text_lunch);
        final EditText mealDinner = (EditText) dialogView.findViewById(R.id.meal_edit_text_dinner);
        final EditText mealSupper = dialogView.findViewById(R.id.meal_edit_text_supper);


        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String day = "Monday";
                userMealDatabase.day = day;
                userMealDatabase.breakfast = mealBreakfast.getText().toString();
              /*  userMealDatabase.lunch = mealLunch.getText().toString();
                userMealDatabase.dinner = mealDinner.getText().toString();
                userMealDatabase.supper = mealSupper.getText().toString();*/
                userMealDatabase.save();
                adapter.clear();
                
                List<UserMealDatabase> results = new Select()
                        .from(UserMealDatabase.class)
                        .where("day = ?", day )
                        .execute();
                adapter.addAll(results);

                adapter.notifyDataSetChanged();

                mListView.setAdapter(adapter);



                Toast.makeText(mActivity, "Meal saved", Toast.LENGTH_SHORT).show();

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
