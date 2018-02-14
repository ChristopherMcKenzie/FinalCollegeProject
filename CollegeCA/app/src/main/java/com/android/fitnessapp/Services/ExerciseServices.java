package com.android.fitnessapp.Services;

import com.android.fitnessapp.Activity.BaseFragmentActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Gerard on 09/02/2018.
 */

public class ExerciseServices {

    private static ExerciseServices mExerciseServices;

    public static ExerciseServices getInstance() {
        if (mExerciseServices == null) {
            mExerciseServices = new ExerciseServices();
        }
        return mExerciseServices;
    }

}