package com.example.saviola44.strengthbuilding;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.saviola44.strengthbuilding.Model.Exercise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saviola44 on 24.05.16.
 */
//reprezentuje pojedynczy trening, normalny plan treningowy składa sie z kilku np 3-5 takich treninow
public class Training implements Parcelable{
    String trainingLabel;
    String trainingDescription;
    ArrayList<Exercise> exercises;
    ArrayList<Integer> exercisesSeries;

    public Training() {
        exercises = new ArrayList<>();
        exercisesSeries = new ArrayList<>();
    }
    protected Training(Parcel in){
        trainingLabel = in.readString();
        trainingDescription = in.readString();
        exercises = in.createTypedArrayList(Exercise.CREATOR);
        exercisesSeries = (ArrayList<Integer>) in.readSerializable();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    public void setTrainingLabel(String trainingLabel) {
        this.trainingLabel = trainingLabel;
    }

    public void setTrainingDescription(String trainingDescription) {
        this.trainingDescription = trainingDescription;
    }

    public String getTrainingLabel() {
        return trainingLabel;
    }

    public String getTrainingDescription() {
        return trainingDescription;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public List<Integer> getExercisesSeries() {
        return exercisesSeries;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trainingLabel);
        dest.writeString(trainingDescription);
        dest.writeTypedList(exercises);
        dest.writeSerializable(exercisesSeries);
    }
}
