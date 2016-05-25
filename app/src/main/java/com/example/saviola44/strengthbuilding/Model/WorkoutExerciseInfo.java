package com.example.saviola44.strengthbuilding.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by saviola44 on 25.05.16.
 */
public class WorkoutExerciseInfo implements Parcelable{
    ExerciseInfo exercise;
    int numberOfSeries;
    double maxWeight;

    public WorkoutExerciseInfo(ExerciseInfo exercise) {
        this.exercise = exercise;
        numberOfSeries=1;
        maxWeight=0;
    }

    public WorkoutExerciseInfo(int numberOfSeries, ExerciseInfo exercise, double maxWeight) {
        this.numberOfSeries = numberOfSeries;
        this.exercise = exercise;
        this.maxWeight = maxWeight;
    }

    public WorkoutExerciseInfo(ExerciseInfo exercise, int numberOfSeries) {
        this.exercise = exercise;
        this.numberOfSeries = numberOfSeries;
        maxWeight=0;
    }
    protected WorkoutExerciseInfo(Parcel in){
        exercise = in.readParcelable(ExerciseInfo.class.getClassLoader());
        numberOfSeries = in.readInt();
        maxWeight = in.readDouble();
    }
    public static final Creator<WorkoutExerciseInfo> CREATOR = new Creator<WorkoutExerciseInfo>() {
        @Override
        public WorkoutExerciseInfo createFromParcel(Parcel in) {
            return new WorkoutExerciseInfo(in);
        }

        @Override
        public WorkoutExerciseInfo[] newArray(int size) {
            return new WorkoutExerciseInfo[size];
        }
    };

    public ExerciseInfo getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseInfo exercise) {
        this.exercise = exercise;
    }

    public int getNumberOfSeries() {
        return numberOfSeries;
    }

    public void setNumberOfSeries(int numberOfSeries) {
        this.numberOfSeries = numberOfSeries;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(exercise, flags);
        dest.writeInt(numberOfSeries);
        dest.writeDouble(maxWeight);
    }

    @Override
    public String toString() {
        return exercise.toString() + " " + numberOfSeries + " serie";
    }
}
