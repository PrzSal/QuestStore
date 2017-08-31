package dao;

import interfaces.*;
import model.*;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractDAO<T> implements Iterator, DAO<T> {

    protected LinkedList<T> objectsList = new LinkedList<T>();

    public void addObject(T object) {
        objectsList.add(object);
    }

    public void removeObject(String userID) {

        Iterator<T> iter = getIterator();

        while (iter.hasNext()) {
          T object = iter.next();

          if (object.getUserID().equals(userID)) {
            iter.remove();
          }
        }
    }

    public void setObjectList(LinkedList<T> newObjectsList) {
        objectsList = newObjectsList;
    }

    public LinkedList<T> getObjectList() {
        return objectsList;
    }

    public T getObjectBy(String userID) {

        Iterator<T> iter = getIterator();

        while (iter.hasNext()) {
          T object = iter.next();

          if (object.getUserID().equals(userID)) {
            iter.remove();
          }
        }
    }

    public Iterator getIterator() {

        Iterator iterator = this.objectsList.iterator();
        return iterator;
    }

    protected abstract void save(LinkedList<T> objectsList);
    protected abstract LinkedList<T> load(String fileName);
}
