package com.example.saviola44.strengthbuilding.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saviola44 on 21.05.16.
 */
public class ExerciseInfo implements Parcelable {
    long id;
    String nazwa;
    Long muscleParts;
    boolean isCompound; //czy cwiczenie wielostawowe

    public String getNazwa() {
        return nazwa;
    }

    public Long getMuscleParts() {
        return muscleParts;
    }

    public boolean isCompound() {
        return isCompound;
    }

    @Override
    public String toString() {
        return nazwa;
    }

    public ExerciseInfo(long id, String nazwa, Long muscleParts, boolean isCompound) {
        this.id = id;
        this.nazwa = nazwa;
        this.muscleParts = muscleParts;
        this.isCompound = isCompound;
    }

    public ExerciseInfo(String nazwa, Long muscleParts, boolean isCompound) {
        id=0;
        this.nazwa = nazwa;
        this.muscleParts = muscleParts;
        this.isCompound = isCompound;
    }

    protected ExerciseInfo(Parcel in){
        id = in.readLong();
        nazwa = in.readString();
        muscleParts = in.readLong();
        isCompound = in.readByte() != 0;
    }

    public static final Creator<ExerciseInfo> CREATOR = new Creator<ExerciseInfo>() {
        @Override
        public ExerciseInfo createFromParcel(Parcel in) {
            return new ExerciseInfo(in);
        }

        @Override
        public ExerciseInfo[] newArray(int size) {
            return new ExerciseInfo[size];
        }
    };
    public long getId() {
        return id;
    }


    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setMuscleParts(Long muscleParts) {
        this.muscleParts = muscleParts;
    }

    public void setIsCompound(boolean isCompound) {
        this.isCompound = isCompound;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nazwa);
        dest.writeLong(muscleParts);
        dest.writeByte((byte) (isCompound ? 1 : 0));
    }
}
