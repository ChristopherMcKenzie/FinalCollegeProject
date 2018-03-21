package com.android.fitnessapp.services;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.activity.HomeActivity;
import com.android.fitnessapp.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.socket.client.Socket;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Gerard on 11/01/2018.
 */

public class LiveAccountServices {
    private static LiveAccountServices mLiveAccountServices;

    private final int USER_ERROR_EMPTY_PASS = 1;
    private final int USER_ERROR_EMPTY_EMAIL = 2;
    private final int USER_ERROR_EMPTY_USERNAME = 3;
    private final int USER_PASS_SHORT = 4;
    private final int SERVER_SUCCESS = 5;
    private final int SERVER_FAIL = 6;
    private final int NO_ERRORS = 7;

    public static LiveAccountServices getInstance()
    {
        if(mLiveAccountServices == null)
        {
            mLiveAccountServices = new LiveAccountServices();
        }
        return mLiveAccountServices;

    }


    public Subscription getAuthToken(JSONObject data, final BaseFragmentActivity baseContext, final SharedPreferences sharedPreference)
    {
        rx.Observable<JSONObject> jsonObservable = rx.Observable.just(data);

        return jsonObservable
                .subscribeOn(Schedulers.io())
                .map(new Func1<JSONObject, List<String>>() {
                    @Override
                    public List<String> call(JSONObject jsonObject) {
                        List<String> userDetails = new ArrayList<>();

                        try {
                            JSONObject getData = jsonObject.getJSONObject("token");
                            String token = (String) getData.get("authToken");
                            String email = (String) getData.get("email");
                            String userName = (String) getData.get("userName");
                            String height = (String) getData.get("weight");
                            String weight = (String) getData.get("height");

                            userDetails.add(token);
                            userDetails.add(userName);
                            userDetails.add(email);
                            userDetails.add(weight);
                            userDetails.add(height);

                            return userDetails;

                        } catch (JSONException e) {
                            e.printStackTrace();
                            return userDetails;
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        String token = strings.get(0);
                        final String userName = strings.get(1);
                        final String email = strings.get(2);
                        final String weight = strings.get(3);
                        final String height = strings.get(4);

                        if(!email.equals("error"))
                        {
                            FirebaseAuth.getInstance().signInWithCustomToken(token)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(!task.isSuccessful())
                                            {
                                                Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                sharedPreference.edit().putString(Constants.USER_EMAIL, email).apply();
                                                sharedPreference.edit().putString(Constants.USER_NAME, userName).apply();
                                                sharedPreference.edit().putString(Constants.USER_WEIGHT, weight).apply();
                                                sharedPreference.edit().putString(Constants.USER_HEIGHT, height).apply();
                                                Toast.makeText(baseContext, "Logged in", Toast.LENGTH_LONG).show();

                                                Intent i = new Intent(baseContext, HomeActivity.class);
                                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                baseContext.startActivity(i);
                                                baseContext.finish();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }


    public Subscription sendLogInInfo(final EditText email, final EditText password, final Socket socket
            , final BaseFragmentActivity baseContext)
    {
        List<String> userDetails = new ArrayList<>();

        userDetails.add(email.getText().toString());
        userDetails.add(password.getText().toString());

        rx.Observable<List<String>> userDetailsObservable = rx.Observable.just(userDetails);

        return userDetailsObservable.subscribeOn(Schedulers.io())
                .map(new Func1<List<String>, Integer>() {
                    @Override
                    public Integer call(List<String> strings) {

                        final String userEmail = strings.get(0);
                        String userPassword = strings.get(1);

                        if(userEmail.isEmpty())
                        {
                            return USER_ERROR_EMPTY_EMAIL;
                        }
                        else if(userPassword.isEmpty())
                        {
                            return USER_ERROR_EMPTY_PASS;
                        }
                        else if(userPassword.length() < 6)
                        {
                            return USER_PASS_SHORT;
                        }
                        else
                        {
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, userPassword)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(!task.isSuccessful()){
                                                Toast.makeText(baseContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }else
                                            {
                                                JSONObject sendData = new JSONObject();
                                                try
                                                {
                                                    sendData.put("email", userEmail);
                                                    socket.emit("userInfo", sendData);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    });
//make sure custom auth token is used
                            FirebaseAuth.getInstance().signOut();

                            return NO_ERRORS;
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                        if(integer.equals(USER_ERROR_EMPTY_EMAIL))
                        {
                            //Is the parameters
                            email.setError("Email address is empty");
                        }
                        else if(integer.equals(USER_ERROR_EMPTY_PASS))
                        {
                            password.setError("Password cannot be blank");
                        }
                        else if(integer.equals(USER_PASS_SHORT))
                        {
                            password.setError("Password must be 6 characters");
                        }
                    }
                });
    }


    public Subscription sendRegistrationInfo(final EditText userName, final EditText email, final EditText password, final EditText weight, final EditText height, final Socket socket)
    {
        List<String> userDetails = new ArrayList<>();

        userDetails.add(userName.getText().toString());
        userDetails.add(email.getText().toString());
        userDetails.add(password.getText().toString());
        userDetails.add(weight.getText().toString());
        userDetails.add(height.getText().toString());

        rx.Observable<List<String>> userDetailsObservable = rx.Observable.just(userDetails);
        return userDetailsObservable
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<String>, Integer>(){

                    @Override
                    public Integer call(List<String> strings) {
                        String userName = strings.get(0);
                        String userEmail = strings.get(1);
                        String userPassword = strings.get(2);
                        String weight = strings.get(3);
                        String height = strings.get(4);
                        if(userName.isEmpty())
                        {
                            return USER_ERROR_EMPTY_USERNAME;
                        }else if(userEmail.isEmpty())
                        {
                            return USER_ERROR_EMPTY_EMAIL;
                        }
                        else if(userPassword.isEmpty())
                        {
                            return USER_ERROR_EMPTY_PASS;
                        }
                        else if(userPassword.length() < 6 )
                        {
                            return USER_PASS_SHORT;
                        }else
                        {
                            JSONObject sendData = new JSONObject();
                            try
                            {
                                sendData.put("email", userEmail);
                                sendData.put("userName", userName);
                                sendData.put("password", userPassword);
                                sendData.put("height", height);
                                sendData.put("weight", weight);
                                socket.emit("userData", sendData);
                                return SERVER_SUCCESS;
                            } catch (JSONException e) {
                                Log.i(LiveAccountServices.class.getSimpleName(), e.getMessage());
                                return SERVER_FAIL;
                            }
                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if(integer.equals(USER_ERROR_EMPTY_EMAIL))
                        {
                            email.setError("Email address is empty");
                        }
                        else if(integer.equals(USER_ERROR_EMPTY_PASS))
                        {
                            password.setError("Password cannot be blank");
                        }
                        else if(integer.equals(USER_PASS_SHORT))
                        {
                            password.setError("Password must be 6 characters");
                        }
                        else if(integer.equals(USER_ERROR_EMPTY_USERNAME))
                        {
                            userName.setError("Can't be null");
                        }
                    }
                });

    }

/*    public Subscription sendRegistrationInfo(final EditText userName, final EditText email, final EditText password,
                                             final EditText height, final EditText weight, final Socket socket)
    {
        List<String> userDetails = new ArrayList<>();
        userDetails.add(userName.getText().toString());
        userDetails.add(email.getText().toString());
        userDetails.add(password.getText().toString());
        userDetails.add(height.getText().toString());
        userDetails.add(weight.getText().toString());
        rx.Observable<List<String>> userDetailsObservable = rx.Observable.just(userDetails);
        return userDetailsObservable
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<String>, Integer>(){
                    @Override
                    public Integer call(List<String> strings) {
                        String userName = strings.get(0);
                        String userEmail = strings.get(1);
                        String userPassword = strings.get(2);
                        String height = strings.get(3);
                        String weight = strings.get(4);
                        if(userName.isEmpty())
                        {
                            return USER_ERROR_EMPTY_USERNAME;
                        }else if(userEmail.isEmpty())
                        {
                            return USER_ERROR_EMPTY_EMAIL;
                        }
                        else if(userPassword.isEmpty())
                        {
                            return USER_ERROR_EMPTY_PASS;
                        }
                        else if(userPassword.length() < 6 )
                        {
                            return USER_PASS_SHORT;
                        }
                        else if(height.isEmpty())
                        {
                               height = "N/A";
                        }
                        else if(weight.isEmpty())
                        {
                                weight = "N/A";
                        }
                        else
                        {
                            JSONObject sendData = new JSONObject();
                            try
                            {
                                sendData.put("email", userEmail);
                                sendData.put("userName", userName);
                                sendData.put("password", userPassword);
                                sendData.put("height", height);
                                sendData.put("weight", weight);
                                socket.emit("userData", sendData);
                                return SERVER_SUCCESS;
                            } catch (JSONException e) {
                                Log.i(LiveAccountServices.class.getSimpleName(), e.getMessage());
                                return SERVER_FAIL;
                            }
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Integer integer) {
                        if(integer.equals(USER_ERROR_EMPTY_EMAIL))
                        {
                            email.setError("Email address is empty");
                        }
                        else if(integer.equals(USER_ERROR_EMPTY_PASS))
                        {
                            password.setError("Password cannot be blank");
                        }
                        else if(integer.equals(USER_PASS_SHORT))
                        {
                            password.setError("Password must be 6 characters");
                        }
                        else if(integer.equals(USER_ERROR_EMPTY_USERNAME))
                        {
                            userName.setError("Can't be null");
                        }
                    }
                });
    }*/


    public Subscription registerResponse(JSONObject data, final BaseFragmentActivity activity) {
        rx.Observable<JSONObject> jsonObjectObservable = rx.Observable.just(data);
        return jsonObjectObservable
                .subscribeOn(Schedulers.io())
                .map(new Func1<JSONObject, String>() {
                    @Override
                    public String call(JSONObject jsonObject) {
                        String message;

                        try {
                            JSONObject json = jsonObject.getJSONObject("message");
                            message = (String) json.get("text");
                            return message;
                        } catch (JSONException e) {
                            return e.getMessage();
                        }
                    }

                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String stringResponse) {
                        if(stringResponse.equals("Success"))
                        {
                            activity.finish();
                            Toast.makeText(activity, "Registeration Successful", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(activity, stringResponse, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}