package com.android.fitnessapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fitnessapp.Activity.BaseFragmentActivity;
import com.android.fitnessapp.R;
import com.android.fitnessapp.Services.ExerciseServices;
import com.android.fitnessapp.Utils.Constants;
import com.android.fitnessapp.Utils.ExerciseList;
import com.android.fitnessapp.Views.ExerciseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
import rx.Subscription;

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

    public List<ExerciseList> exerciseList;
    private Socket mSocket;
    private BaseFragmentActivity mActivity;
    private ExerciseServices mES;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            mSocket = IO.socket(Constants.IP_HOST);

        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.i(ArmsFragment.class.getSimpleName(), e.getMessage());
            Toast.makeText(getActivity(), "Can't connect to server", Toast.LENGTH_SHORT).show();
        }
        mES = ExerciseServices.getInstance();

        mSocket.on("exerciseArms", getArmExerciseListener());

        mSocket.connect();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_arms, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        final TextView muscle = (TextView) rootView.findViewById(R.id.list_view_muscle);
        final TextView listView = (TextView) rootView.findViewById(R.id.list_view_exercise_text);

        exerciseList = new ArrayList<>();

        exerciseList.add(new ExerciseList("Biceps \nBicep curl", "Take a bar bell or two dumbbells." +
                "\nHold your arms down straight and bend your elbow" +
                ",\nbringing the weight upwards, bring the weight up as much as you can making sure your" +
                "\nbiceps are contracted, then control the weight back down to the starting position."));
        exerciseList.add(new ExerciseList("Triceps \nSkullcrusher", "Ez bar bell" ));
        exerciseList.add(new ExerciseList("Forearms/Biceps \nZottomon Curl", "Chest"));
        exerciseList.add(new ExerciseList("Triceps\n Rope pulldown", "Back"));
        exerciseList.add(new ExerciseList("Biceps\n Hammer Curl", ""));


        ExerciseListAdapter adapter = new ExerciseListAdapter(mActivity, exerciseList);

        mListView.setAdapter(adapter);



        return rootView;
    }

    private Emitter.Listener sendDummyDataListener()
    {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];

                compositeSubscription.add((Subscription) mES.sendDummyData(mActivity, mSocket));
            }
        };
    }
    private Emitter.Listener getArmExerciseListener()
    {
        return new Emitter.Listener()
        {

            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];

                compositeSubscription.add(mES.getArmExercises(jsonObject, mActivity));
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
