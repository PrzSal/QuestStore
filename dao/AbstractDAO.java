package dao;

import interfaces.*;
import model.*;

import java.util.Iterator;

public abstract class AbstractDAO<T> implements Iterator, DAO<T> {

    protected LinkedList<T> objectsList = new LinkedList<T>;

    public void addObject(T object) {
        objectsList.add(object);
    }

    public void removeObject(String userID) {

        Iterator<TodoItem> iter = objectsList.iterator();

        while (iter.hasNext()) {
          T object = iter.next();

          if (object.getUserID().equals(userID) {
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

        for (T object )
    }

    public Iterator getIterator() {
        return new Iterator();
    }

}
