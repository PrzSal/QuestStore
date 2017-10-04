package com.codecool.dream_is_green.model;

public class AbstractTask<T> {

    protected String title;
    protected Integer price;
    protected T category;

    public AbstractTask(String title, Integer price, T questCategory) {
        this.title = title;
        this.price = price;
        this.category = questCategory;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {

        return price;
    }

    public T getCategory() {
        return category;
    }
}
