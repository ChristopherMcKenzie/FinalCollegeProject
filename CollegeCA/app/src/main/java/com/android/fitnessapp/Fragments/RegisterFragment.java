package com.android.fitnessapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.fitnessapp.Activity.BaseFragmentActivity;
import com.android.fitnessapp.Activity.LoginActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.Services.LiveAccountServices;
import com.android.fitnessapp.Utils.Constants;

import org.json.JSONObject;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Gerard on 11/01/2018.
 */

public class RegisterFragment extends BaseFragment {

    @BindView(R.id.fragment_register_email)
    EditText mEmailText;
    @BindView(R.id.fragment_register_username)
    EditText mUsernameText;
    @BindView(R.id.fragment_register_password)
    EditText mUserPassword;

    @BindView(R.id.fragment_register_registerButton)
    Button mRegiserButton;
    @BindView(R.id.fragment_register_login)
    Button mLoginRegButton;

    private Unbinder mUnbinder;
    private Socket mSocket;

    private BaseFragmentActivity mActivity;


    private LiveAccountServices mLiveAccountServices;
    public static RegisterFragment newInstance()
    {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mSocket = IO.socket(Constants.IP_HOST);
        } catch (URISyntaxException e) {
            Log.i(LoginFragment.class.getSimpleName(), e.getMessage());
            Toast.makeText(getActivity(), "Cannot connect", Toast.LENGTH_SHORT).show();
        }
        mSocket.connect();
        mSocket.on("message", accountResponse());
        mLiveAccountServices = LiveAccountServices.getInstance();
    }

    @OnClick(R.id.fragment_register_registerButton)
    public void setmRegiserButton()
    {
        compositeSubscription.add(mLiveAccountServices.sendRegistrationInfo(
                mUsernameText, mEmailText, mUserPassword, mSocket
        ));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);


        return rootView;
    }


    @OnClick(R.id.fragment_register_login)
    public void setmLoginRegButton()
    {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    private Emitter.Listener accountResponse()
    {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                compositeSubscription.add(mLiveAccountServices.registerResponse(data, mActivity));
            }
        };
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }

    //Stops a null exception happening becasue of RX java
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
