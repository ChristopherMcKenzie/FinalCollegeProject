package com.android.fitnessapp.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Gerard on 17/03/2018.
 */
@Table(name = "Meals")
public class UserMealDatabase extends Model {

    @Column(name = "day")
    public String day;

    @Column(name = "breakfast")
    public String breakfast;

    @Column(name = "lunch")
    public String lunch;

    @Column(name = "dinner")
    public String dinner;

    @Column(name = "supper")
    public String supper;

    public UserMealDatabase()
    {super();}

    public UserMealDatabase(String day, String breakfast, String lunch, String dinner, String supper) {
        this.day = day;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.supper = supper;
    }
}
