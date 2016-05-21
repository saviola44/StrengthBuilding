package com.example.saviola44.strengthbuilding.Model;

/**
 * Created by saviola44 on 21.05.16.
 */
public class Exercise {
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

    public Exercise(String nazwa, Long muscleParts, boolean isCompound) {
        this.nazwa = nazwa;
        this.muscleParts = muscleParts;
        this.isCompound = isCompound;
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
}
