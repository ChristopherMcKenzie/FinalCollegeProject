package com.android.fitnessapp.Activity;

import android.support.v4.app.Fragment;

import com.android.fitnessapp.Fragments.LoginFragment;


public class LoginActivity extends BaseFragmentActivity{


    @Override
    Fragment createFragment() {
        return LoginFragment.newInstances();
    }






}



/*
private Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            mSocket = IO.socket(Constants.IP_HOST);
        } catch (URISyntaxException e) {
            Log.i(LoginActivity.class.getSimpleName(), e.getMessage());
        }
        mSocket.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }
 */
//192.168.0.57