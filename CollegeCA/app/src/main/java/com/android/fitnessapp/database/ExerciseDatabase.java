package com.android.fitnessapp.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Gerard on 12/04/2018.
 */
@Table(name = "loadInExercise")
public class ExerciseDatabase extends Model {


    @Column(name = "name")
    public String name;
    @Column(name = "type")
    public String type;

    public ExerciseDatabase() {
        super();
    }
}
