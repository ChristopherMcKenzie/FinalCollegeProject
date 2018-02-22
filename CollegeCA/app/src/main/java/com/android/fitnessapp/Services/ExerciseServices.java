package com.android.fitnessapp.services;

import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.utils.ExerciseList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Gerard on 09/02/2018.
 */

public class ExerciseServices {

    private final int SERVER_SUCCESS = 1;
    private final int SERVER_FAILURE = 2;


    private static ExerciseServices mExerciseServices;

    public static ExerciseServices getInstance() {
        if (mExerciseServices == null) {
            mExerciseServices = new ExerciseServices();
        }
        return mExerciseServices;
    }

    public Subscription addUserExcercises(final EditText exercise, JSONObject data, final BaseFragmentActivity baseFragmentActivity,
                                          final SharedPreferences sharedPreferences)
    {
        List<String> userExerciseDetails = new ArrayList<>();

        userExerciseDetails.add(exercise.getText().toString());

        rx.Observable<List<String>> userExerciseObservable = rx.Observable.just(userExerciseDetails);

        return (Subscription) userExerciseObservable.subscribeOn(Schedulers.io())
                .map(new Func1<List<String>, Integer>() {
                    @Override
                    public Integer call(List<String> strings) {

                    return 1;
                    }
                });
    }

    public Subscription getArmExercises(JSONObject data, final BaseFragmentActivity baseFragmentActivity )
    {
        rx.Observable<JSONObject> jsonObservable = rx.Observable.just(data);

        Toast.makeText(baseFragmentActivity, "getArmExercises method", Toast.LENGTH_SHORT).show();

        return jsonObservable
                .subscribeOn(Schedulers.io())
                .map(new Func1<JSONObject, List<ExerciseList>>() {

                    @Override
                    public List<ExerciseList> call(JSONObject jsonObject) {
                        List<ExerciseList> exerciseDetails = new ArrayList<>();

                        try
                        {
                            JSONObject getData = jsonObject.getJSONObject("exerciseArm");
                            String name = (String) getData.get("name");
                            String desc = (String) getData.get("description");

                            exerciseDetails.add(new ExerciseList(name, desc));

                            return exerciseDetails;
                        }catch(JSONException e)
                        {
                            e.printStackTrace();
                            return exerciseDetails;
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ExerciseList>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ExerciseList> exerciseLists) {
                        if(exerciseLists.isEmpty())
                        {
                            Toast.makeText(baseFragmentActivity, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }

    /*
    This allows me to send data to the server in a JSON format. Once this gets called the next thing on list will be called
    so sendDummyData() then call getArmExercise() and retrieve the data from the database
     */
    public Observable<Integer> sendDummyData(final BaseFragmentActivity baseFragmentActivity,
                                             final Socket socket)
    {
        final List<String> dummyDataArray = new ArrayList<>();
        //In order to send my dummy data I need to get cretae a list
        //Add to the list and pull a single string from it
        dummyDataArray.add("dummydata");

        rx.Observable<List<String>> dummyDataObservable = rx.Observable.just(dummyDataArray);
        return dummyDataObservable
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<String>, Integer>() {
                    @Override
                    public Integer call(List<String> strings) {

                        String dummyDataToBeSent = strings.get(0);
                        JSONObject sendData = new JSONObject();
                        try {
                            sendData.put("dummy", dummyDataToBeSent);
                            socket.emit("dummyData", sendData);
                            return SERVER_SUCCESS;
                        } catch (JSONException e) {
                            return SERVER_FAILURE;
                        }
                    }
                });
    }
}