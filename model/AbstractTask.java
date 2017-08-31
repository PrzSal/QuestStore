package model;

public class AbstractTask<T> {
    protected String title;
    protected Integer price;
    protected T category;

    public String getTitle() {

        return title;
    }

    public void setTitle(String newTitle) {

        title = newTitle;
    }

    public Integer getPrice() {

        return price;
    }

    public void setPrice(Integer newPrice) {

        price = newPrice;
    }

    public T getCategory() {

        return category;
    }

    public void setCategory(T newCategory) {

        category = newCategory;
    }
}
