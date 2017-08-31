package dao;

import interfaces.*;

//import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected LinkedList<T> objectsList = new LinkedList<T>();

    public void addObject(T object) {
        objectsList.add(object);
    }

    public void removeObject(T object) {
        objectsList.remove(object);
        // Iterator<T> iter = this.objectsList.iterator();
        //
        // while (iter.hasNext()) {
        //   T object = iter.next();
        //
        //   if (object.getUserID().equals(userID)) {
        //     iter.remove();
        //   }
        // }
    }

    public void setObjectList(LinkedList<T> newObjectsList) {
        objectsList = newObjectsList;
    }

    public LinkedList<T> getObjectList() {
        return objectsList;
    }

    // public T getObjectBy(String userID) {
    //
    //     Iterator<T> iter = this.objectsList.iterator();
    //
    //     while (iter.hasNext()) {
    //       T object = iter.next();
    //
    //       if (object.getUserID().equals(userID)) {
    //         iter.remove();
    //       }
    //     }
    // }

    public String toString() {
        String toStringList = "";
        int count = 1;
        for(T object: this.objectsList) {
            toStringList += count + ". " + object.toString() + "\n";
            count++;
        }

        return toStringList.trim();

    }

}
