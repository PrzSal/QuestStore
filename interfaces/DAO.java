package interfaces;

import java.util.LinkedList;

public interface DAO<T> {

    public void addObject(T object);
    public void removeObject(String userID);
    public void setObjectList(LinkedList<T> objectsList);
    public LinkedList<T> getObjectList();
    public T getObjectBy(String userID);

}
