import interfaces.*;
import java.util.Iterator;

public abstract class AbstractDAO<T> implements Iterator, DAO<T> {

    protected LinkedList<T> objectsList = new LinkedList<T>;

    public void addObject(T object) {
        objectsList.add(object);
    }

    public void removeObject(T object) {
        objectsList.remove(object)
    }

    public void setObjectList(LinkedList<T> newObjectsList) {
        objectsList.
    }

    public LinkedList<T> getObjectList() {

    }

    public T getObjectBy(String userID) {

    }

    public Iterator getIterator() {
        return new Iterator();
    }

}
