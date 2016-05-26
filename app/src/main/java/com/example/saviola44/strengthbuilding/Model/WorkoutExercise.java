package com.example.saviola44.strengthbuilding.Model;

/**
 * Created by saviola44 on 26.05.16.
 */
public class WorkoutExercise {
    ExerciseInfo exercise;
    int weight;
    int reps;
    String comment;

    public WorkoutExercise(ExerciseInfo exercise) {
        this.exercise = exercise;
    }

    public int getWeight() {
        return weight;
    }

    public ExerciseInfo getExercise() {
        return exercise;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
