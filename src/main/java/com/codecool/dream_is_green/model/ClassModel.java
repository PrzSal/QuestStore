package com.codecool.dream_is_green.model;

import java.util.LinkedList;


public class ClassModel {

    private String className;
    private LinkedList<MentorModel> mentorsList;
    private LinkedList<StudentModel> studentsList;

    public ClassModel(String className) {
        this.className = className;
        mentorsList = new LinkedList<MentorModel>();
        studentsList = new LinkedList<StudentModel>();
    }

    public String toString() {

        String str = String.format(className, "%-12");

        return str;
    }
}
