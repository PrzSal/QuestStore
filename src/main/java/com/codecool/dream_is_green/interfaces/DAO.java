package com.codecool.dream_is_green.interfaces;

import java.util.LinkedList;

public interface DAO<T> {

    void addObject(T object);
    void removeObject(T object);
    void setObjectList(LinkedList<T> objectsList); //Method ready to use, pass test
    LinkedList<T> getObjectList();
}
