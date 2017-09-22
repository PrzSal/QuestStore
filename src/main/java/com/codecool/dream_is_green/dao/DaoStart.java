package com.codecool.dream_is_green.dao;

public class DaoStart {

    private static AdminDAO adminDao = new AdminDAO();
    private static MentorDAO mentorDao = new MentorDAO();
    private static StudentDAO studentDao = new StudentDAO();
    private static ClassDAO classDao = new ClassDAO();
    private static QuestDAO questDao = new QuestDAO();
    private static ArtifactDAO artifactDao = new ArtifactDAO();

    public static MentorDAO getMentorDao() {
        return mentorDao;
    }

    public static ClassDAO getClassDao() {
        return classDao;
    }

    public static AdminDAO getAdminDao() {
        return adminDao;
    }

    public static StudentDAO getStudentDao() {
        return studentDao;
    }

    public static QuestDAO getQuestDao() {
        return questDao;
    }

    public static ArtifactDAO getArtifactDao() {
        return artifactDao;
    }
}
