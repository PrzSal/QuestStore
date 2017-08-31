package interfaces;

public interface DAO<T> {

    public void addObject(T);
    public void removeObject(T);
    public void setObjectList(LinkedList<T>);
    public LinkedList<T> getObjectList();
    public T getObjectBy(String userID);

}
