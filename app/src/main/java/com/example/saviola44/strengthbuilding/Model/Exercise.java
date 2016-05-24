package com.example.saviola44.strengthbuilding.Model;

/**
 * Created by saviola44 on 21.05.16.
 */
public class Exercise {
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

    public Exercise(long id, String nazwa, Long muscleParts, boolean isCompound) {
        this.id = id;
        this.nazwa = nazwa;
        this.muscleParts = muscleParts;
        this.isCompound = isCompound;
    }

    public Exercise(String nazwa, Long muscleParts, boolean isCompound) {
        this.nazwa = nazwa;
        this.muscleParts = muscleParts;
        this.isCompound = isCompound;
    }

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
}
