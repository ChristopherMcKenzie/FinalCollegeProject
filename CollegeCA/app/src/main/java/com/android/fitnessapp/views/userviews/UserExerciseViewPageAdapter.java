package com.android.fitnessapp.views.userviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.fitnessapp.fragments.user_exercise_fragments.FridayFragment;
import com.android.fitnessapp.fragments.user_exercise_fragments.MondayFragment;
import com.android.fitnessapp.fragments.user_exercise_fragments.SaturdayFragment;
import com.android.fitnessapp.fragments.user_exercise_fragments.SundayFragment;
import com.android.fitnessapp.fragments.user_exercise_fragments.ThursdayFragment;
import com.android.fitnessapp.fragments.user_exercise_fragments.TuesdayFragment;
import com.android.fitnessapp.fragments.user_exercise_fragments.WednesdayFragment;

/**
 * Created by Gerard on 21/02/2018.
 */

public class UserExerciseViewPageAdapter extends FragmentStatePagerAdapter{

    public UserExerciseViewPageAdapter(FragmentManager fm ){super (fm);}

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;

        switch(position)
        {
            case 0:
                returnFragment = MondayFragment.newInstance();
                break;
            case 1:
                returnFragment = TuesdayFragment.newInstance();
                break;
            case 2:
                returnFragment = WednesdayFragment.newInstance();
                break;
            case 3:
                returnFragment = ThursdayFragment.newInstance();
                break;
            case 4:
                returnFragment = FridayFragment.newInstance();
                break;
            case 5:
                returnFragment = SaturdayFragment.newInstance();
                break;
            case 6:
                returnFragment = SundayFragment.newInstance();
                break;
            default:
                return null;

        }
        return returnFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;

        switch(position)
        {
            case 0:
                title = "Monday";
                break;
            case 1:
                title = "Tuesday";
                break;
            case 2:
                title = "Wednesday";
                break;
            case 3:
                title = "Thursday";
                break;
            case 4:
                title = "Friday";
                break;
            case 5:
                title = "Saturday";
                break;
            case 6:
                title = "Sunday";
                break;
            default:
                return null;
        }
        return title;
    }

    @Override
    public int getCount() {return 7;}
}
