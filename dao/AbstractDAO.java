package dao;

import interfaces.*;
import java.util.LinkedList;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected LinkedList<T> objectsList = new LinkedList<T>();

    public void addObject(T object) {
        objectsList.add(object);
    }
  
    public void removeObject(T object) {
        objectsList.remove(object);
    }

    public void setObjectList(LinkedList<T> newObjectsList) {
        objectsList = newObjectsList;
    }

    public LinkedList<T> getObjectList() {
        return objectsList;
    }

    public String toString() {
      
        String toStringList = "";
        int count = 1;
        for(T object: this.objectsList) {
            toStringList += count + ". | " + object.toString() + "\n";
            count++;
        }
        return toStringList.trim();
    }
}
