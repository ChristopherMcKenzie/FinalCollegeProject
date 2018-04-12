package com.android.fitnessapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.android.fitnessapp.activity.BaseFragmentActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.database.ExerciseDatabase;
import com.android.fitnessapp.database.UserExerciseDatabase;
import com.android.fitnessapp.database.UserMealDatabase;
import com.android.fitnessapp.services.ExerciseServices;
import com.android.fitnessapp.utils.Constants;
import com.android.fitnessapp.utils.ExerciseList;
import com.android.fitnessapp.views.ExerciseListAdapter;
import com.android.fitnessapp.views.ExerciseLoadListAdapter;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Gerard on 31/01/2018.
 */

public class ArmsFragment extends BaseFragment {

    public static ArmsFragment newInstance()
    {
        return new ArmsFragment();
    }

    @BindView(R.id.exercise_listview)
    ListView mListView;


    private Unbinder mUnbinder;

    public ExerciseDatabase exerciseDatabase;
    private BaseFragmentActivity mActivity;
    private ExerciseServices mES;
    private Socket mSocket;
    private ExerciseLoadListAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            mSocket = IO.socket(Constants.IP_HOST);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mES = ExerciseServices.getInstance();
        mSocket.on("exerciseArm", databaseListener());

        mSocket.connect();
    }

//Use IDs for exercise in Firebase

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arms, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        ActiveAndroid.initialize(mActivity);
        //final TextView muscle = (TextView) rootView.findViewById(R.id.list_view_muscle);
        //final TextView listView = (TextView) rootView.findViewById(R.id.list_view_exercise_text);



        exerciseDatabase = new ExerciseDatabase();

        ArrayList items = new ArrayList();
        adapter = new ExerciseLoadListAdapter(mActivity, items);
        String type = "arms";

        /*List<ExerciseDatabase> result = new Select()
                .from(ExerciseDatabase.class)
                .where("type = ?", type)
                .execute();

        adapter.addAll(result);
*/

        mListView.setAdapter(adapter);



        return rootView;
    }

    private Emitter.Listener databaseListener()
    {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];

                compositeSubscription.add(mES.getExercises(jsonObject, mActivity));
            }
        };
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
