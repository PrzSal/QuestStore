package model;

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

    public String getClassName() {
        return className;
    }

    public LinkedList<MentorModel> getMentorsList() {
        return mentorsList;
    }

    public LinkedList<StudentModel>  gettudentsList() {
        return studentsList;
    }

    public void addMentor(MentorModel mentor) {
        mentorsList.add(mentor);
    }

    public void addStudent(StudentModel student) {
        studentsList.add(student);
    }

    public void removeMentor(MentorModel mentor) {
        mentorsList.remove(mentor);
    }

    public void removeStudent(StudentModel student) {
        studentsList.remove(student);
    }

    public String toString() {
        return className;
    }
}
