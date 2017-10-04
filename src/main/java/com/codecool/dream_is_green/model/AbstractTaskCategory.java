package com.codecool.dream_is_green.model;

public class AbstractTaskCategory {

    protected String name;

    public AbstractTaskCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
