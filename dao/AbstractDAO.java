import interfaces.*;

public abstract class AbstractDAO implements Iteratr, DAO {

    public void addObject(T);
    public void removeObject(T);
    public void setObjectList(LinkedList<T>);
    public LinkedList<T> getObjectList();
    public T getObjectBy(String userID);
    public Iterator getIterator();

}
