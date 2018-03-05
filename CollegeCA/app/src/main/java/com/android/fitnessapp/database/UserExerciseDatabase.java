package com.android.fitnessapp.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "ExerciseMon")
public class UserExerciseDatabase extends Model {
  //  @Column(name = "email")
   // public String email;

    @Column(name = "day")
    public String day;

    @Column(name = "exerciseName")
    public String exerciseName;



    public UserExerciseDatabase()
    {super();}

    public UserExerciseDatabase(String exerciseName, String day, String reps, String sets) {
        super();
      //  this.email = email;
        this.day = day;
        this.exerciseName = exerciseName;
        //this.reps = reps;

    }
}
