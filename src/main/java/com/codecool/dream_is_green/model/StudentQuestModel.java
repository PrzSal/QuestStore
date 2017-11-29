package com.codecool.dream_is_green.model;

public class StudentQuestModel extends AbstractTask {

    private Integer studentID;

    public StudentQuestModel(String title, Integer price,
                             QuestCategoryModel questCategory, Integer studentID) {
        super(title, price, questCategory);
        this.studentID = studentID;
    }

    public Integer getStudentID() {
        return studentID;
    }
}

