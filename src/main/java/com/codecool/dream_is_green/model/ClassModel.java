package com.codecool.dream_is_green.model;

public class ClassModel {

    private String className;

    public ClassModel(String className) {
        this.className = className;
    }

    public String toString() {

        String str = String.format(className, "%-12");

        return str;
    }

    public String getClassName() {
        return className;
    }
}
