package com.example.saviola44.strengthbuilding.Database.DAO;

import java.util.List;

/**
 * Created by saviola44 on 21.05.16.
 */
public interface DAO<T> {

    long saveElement(T element);
    void delete(T element);
    List<T> getAllElements();

}
