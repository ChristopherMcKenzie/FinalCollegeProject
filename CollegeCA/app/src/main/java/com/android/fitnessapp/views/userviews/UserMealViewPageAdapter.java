package com.android.fitnessapp.views.userviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.fitnessapp.fragments.user_meal_fragments.*;

/**
 * Created by Gerard on 17/03/2018.
 */

public class UserMealViewPageAdapter extends FragmentStatePagerAdapter {


    public UserMealViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;

        switch(position)
        {
            case 0:
                returnFragment = MondayMealFragment.newInstance();
                break;
            case 1:
                returnFragment = TuesdayMealFragment.newInstance();
                break;
            case 2:
                returnFragment = WednesdayMealFragment.newInstance();
                break;
            case 3:
                returnFragment = ThursdayMealFragment.newInstance();
                break;
            case 4:
                returnFragment = FridayMealFragment.newInstance();
                break;
            case 5:
                returnFragment = SaturdayMealFragment.newInstance();
                break;
            case 6:
                returnFragment = SundayMealFragment.newInstance();
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
    public int getCount() {
        return 7;
    }
}
