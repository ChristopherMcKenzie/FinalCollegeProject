package com.android.fitnessapp.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Gerard on 05/04/2018.
 */
@Table(name = "exercises")
public class UserExerciseDatabase extends Model {

    @Column(name = "day")
    public String day;
    @Column(name = "name")
    public String name;
    @Column(name = "sets")
    public String sets;
    @Column(name = "reps")
    public String reps;

    public UserExerciseDatabase() {
        super();
    }
}
