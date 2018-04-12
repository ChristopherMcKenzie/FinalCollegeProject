package com.android.fitnessapp.services;

import com.activeandroid.ActiveAndroid;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.utils.ExerciseList;
import com.android.fitnessapp.views.ExerciseListAdapter;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Gerard on 03/04/2018.
 */

public class ExerciseServices {
    private static ExerciseServices mExerciseSerivces;

    public static ExerciseServices getInstance()
    {
        if(mExerciseSerivces == null)
        {
            mExerciseSerivces = new ExerciseServices();
        }
        return mExerciseSerivces;
    }

    public Subscription getExercises(JSONObject data, final BaseFragmentActivity baseFragmentActivity)
    {
        rx.Observable<JSONObject> jsonObservable = rx.Observable.just(data);

        return jsonObservable
                .subscribeOn(Schedulers.io())
                .map(new Func1<JSONObject, List<String>>() {
                    @Override
                    public List<String> call(JSONObject jsonObject) {
                        List<String> exerciseDetails = new ArrayList<>();

                        try
                        {
                            JSONObject getData = jsonObject.getJSONObject("exerciseArm");
                            String day = (String) getData.get("bodyType");
                            String name = (String) getData.get("exName");

                            exerciseDetails.add(day);
                            exerciseDetails.add(name);

                            return exerciseDetails;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return exerciseDetails;
                        }
                    }

                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>()
                {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        String name = strings.get(1);
                        String type = strings.get(0);

                        if(!name.equals("error") || !type.equals("error"))
                        {
                            UserExerciseDatabase userDatabase = null;
                            ActiveAndroid.initialize(baseFragmentActivity);
                            for(int i = 0; i < strings.size(); i++) {
                                userDatabase.name = name;
                                userDatabase.day = type;
                            }
                        }
                    }
                });

    }
}

