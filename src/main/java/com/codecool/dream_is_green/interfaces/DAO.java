package com.codecool.dream_is_green.interfaces;

import java.util.LinkedList;

public interface DAO<T> {

    void addObject(T object);
    void removeObject(T object);
    LinkedList<T> getObjectList();
}
