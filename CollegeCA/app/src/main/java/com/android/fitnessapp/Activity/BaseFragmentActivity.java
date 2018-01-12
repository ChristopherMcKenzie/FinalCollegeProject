package com.android.fitnessapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.android.fitnessapp.R;
import com.android.fitnessapp.Utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Gerard on 11/01/2018.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {


    abstract Fragment createFragment();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_INFO_KEY, Context.MODE_PRIVATE);
        final String userEmail = sharedPreferences.getString(Constants.USER_EMAIL, "");

        mAuth = FirebaseAuth.getInstance();



        if(!((this instanceof LoginActivity || (this instanceof RegisterActivity)))){
            mListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user == null)
                    {
                        Intent i = new Intent(getApplication(), LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                    else if(userEmail.equals(""))
                    {
                        FirebaseAuth.getInstance().signOut();
                        finish();
                    }
                }
            };
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_fragment_base_fragment);

        if(fragment == null)
        {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                .add(R.id.activity_fragment_base_fragment, fragment)
                .commit();
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!((this instanceof LoginActivity || (this instanceof RegisterActivity)))) {
            mAuth.addAuthStateListener(mListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!((this instanceof LoginActivity || (this instanceof RegisterActivity)))) {
            mAuth.removeAuthStateListener(mListener);
        }
    }
}
