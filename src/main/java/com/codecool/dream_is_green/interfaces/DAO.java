package com.codecool.dream_is_green.interfaces;

import java.util.LinkedList;

public interface DAO<T> {

    public void addObject(T object);
    public void removeObject(T object);
    public void setObjectList(LinkedList<T> objectsList);
    public LinkedList<T> getObjectList();
}
