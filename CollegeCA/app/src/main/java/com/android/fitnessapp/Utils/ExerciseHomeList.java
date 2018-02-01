package com.android.fitnessapp.Utils;

/**
 * Created by Gerard on 30/01/2018.
 */

public class ExerciseHomeList {
    protected String muscle;
    protected String text;

    public ExerciseHomeList(String muscle, String text) {
        this.muscle = muscle;
        this.text = text;
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
