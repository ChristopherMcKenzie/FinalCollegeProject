package com.android.fitnessapp.Utils;

/**
 * Created by Gerard on 30/01/2018.
 */

public class ExerciseList {
    public String muscle;
    public String text;

    public ExerciseList(String muscle, String text) {
        this.muscle = muscle;
        this.text = text;
    }

    public ExerciseList() {
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
