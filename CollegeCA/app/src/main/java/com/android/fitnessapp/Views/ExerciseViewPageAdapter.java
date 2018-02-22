package com.android.fitnessapp.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.fitnessapp.fragments.ArmsFragment;
import com.android.fitnessapp.fragments.BackFragment;
import com.android.fitnessapp.fragments.ChestFragment;
import com.android.fitnessapp.fragments.LegsFragment;
import com.android.fitnessapp.fragments.ShouldersFragment;

/**
 * Created by Gerard on 31/01/2018.
 */

public class ExerciseViewPageAdapter extends FragmentStatePagerAdapter {
    public ExerciseViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;

        switch (position){
            case 0:
                returnFragment = ArmsFragment.newInstance();
                break;
            case 1:
                returnFragment = ShouldersFragment.newInstance();
                break;
            case 2:
                returnFragment = ChestFragment.newInstance();
                break;
            case 3:
                returnFragment = BackFragment.newInstance();
                break;
            case 4:
                returnFragment = LegsFragment.newInstance();
                break;
            default:
                return null;
        }
        return returnFragment;

    }


    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;

        switch (position){
            case 0:
                title = "Arms";
                break;
            case 1:
                title = "Shoulders";
                break;
            case 2:
                title = "Chest";
                break;
            case 3:
                title = "Back";
                break;
            case 4:
                title = "Legs";
                break;
            default:
                return null;
        }

        return title;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
