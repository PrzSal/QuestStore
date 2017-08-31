package interfaces;

public interface DAO<T> {

    public void addObject(T object);
    public void removeObject(T object);
    public void setObjectList(LinkedList<T> objectsList);
    public LinkedList<T> getObjectList();
    public T getObjectBy(String userID);

}
