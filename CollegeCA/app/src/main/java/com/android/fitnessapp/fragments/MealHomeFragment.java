package com.android.fitnessapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.activity.MealHomeActivity;
import com.android.fitnessapp.activity.UserMealActivity;
import com.android.fitnessapp.fragments.user_meal_fragments.MealPostFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gerard on 17/03/2018.
 */

public class MealHomeFragment extends BaseFragment {

    public static MealHomeFragment newInstance(){return new MealHomeFragment();}

    @BindView(R.id.meal_user_button)
    TextView mealUserButton;

    private BaseFragmentActivity mActivity;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meal_home, container, false);
        mUnbinder = ButterKnife.bind(mActivity);


        return rootView;

    }

    @OnClick(R.id.post_activity)
    public void setMealUserButton()
    {
        Intent i = new Intent(mActivity, UserMealActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

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
