package com.android.fitnessapp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fitnessapp.R;
import com.android.fitnessapp.activity.BaseFragmentActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gerard on 12/03/2018.
 */

public class UserProfileFragment extends BaseFragment {
    public static UserProfileFragment newInstance(){return new UserProfileFragment();}

    //BMI formula (weight(KG)/height(M))/height/(M)

    private Unbinder mUnbinder;
    private BaseFragmentActivity mActivity;
    public SharedPreferences mSharedPreferences;

    TextView userEmail;
    EditText userHeight;
    EditText userWeight;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);
        mUnbinder = ButterKnife.bind(mActivity, rootView);

        mSharedPreferences = mActivity.getSharedPreferences("USER_INFO_KEY", Context.MODE_PRIVATE);
        if(mSharedPreferences != null )
        {
            String loadEmail = mSharedPreferences.getString("USER_EMAIL", "");
            String loadHeight = mSharedPreferences.getString("USER_HEIGHT", "");
            String loadWeight = mSharedPreferences.getString("USER_WEIGHT", "");

            userEmail = rootView.findViewById(R.id.user_profile_email);
            userHeight = rootView.findViewById(R.id.user_profile_height);
            userWeight = rootView.findViewById(R.id.user_profile_weight);

            userEmail.setText("Email: " + loadEmail);
            userWeight.setText(loadWeight);
            userHeight.setText(loadHeight);



        }



        return rootView;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
