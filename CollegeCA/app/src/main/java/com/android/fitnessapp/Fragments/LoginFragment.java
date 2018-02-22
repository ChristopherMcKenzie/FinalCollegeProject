package com.android.fitnessapp.fragments;

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

import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.activity.RegisterActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.services.LiveAccountServices;
import com.android.fitnessapp.utils.Constants;

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

public class LoginFragment extends BaseFragment {
    @BindView(R.id.fragment_login_email)
    EditText mEmailText;
    @BindView(R.id.fragment_login_password)
    EditText mPasswordText;
    @BindView(R.id.fragment_login_button)
    Button mLogInButton;
    @BindView(R.id.fragment_login_register)
    Button mLogInRegister;

    private Unbinder mUnbinder;
    private Socket mSocket;
    private BaseFragmentActivity mActivity;
    private LiveAccountServices mLCA;

    public static LoginFragment newInstances()
    {
        return new LoginFragment();
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

        mLCA = LiveAccountServices.getInstance();
        mSocket.on("token", tokenListener());


        mSocket.connect();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }


    private Emitter.Listener tokenListener()
    {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];

                compositeSubscription.add(mLCA.getAuthToken(jsonObject, mActivity, mSharedPreferences));

            }
        };
    }

    @OnClick(R.id.fragment_login_button)
    public void setmLogInButton()
    {
        compositeSubscription.add(mLCA.sendLogInInfo(mEmailText,
                mPasswordText, mSocket, mActivity));
    }

    @OnClick(R.id.fragment_login_register)
    public void setmLogInRegister()
    {
        startActivity(new Intent(getActivity(), RegisterActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
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
