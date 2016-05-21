package com.example.saviola44.strengthbuilding.Model;

/**
 * Created by saviola44 on 22.05.16.
 */
public class Muscle {
    long id;
    String name;

    public Muscle(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Muscle() {
    }

    public void setId(long id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
