package com.codecool.dream_is_green.model;

public class AbstractTask<T> {

    protected String title;
    protected Integer price;
    protected T category;

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
