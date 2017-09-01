import controller.*;
import model.*;
import view.*;
import dao.*;

import java.util.ArrayList;
import java.util.LinkedList;



public class Test {

    public static void main(String[] args) {

        UIView view = new UIView();

        // ArrayList<MentorModel> mentorList = new ArrayList<MentorModel>();
        //
        // MentorModel mentor1 = new MentorModel("Mateusz", "Ostafil", "mati@gmail.com");
        // MentorModel mentor2 = new MentorModel("Mateusz", "Steliga", "scooby@gmail.com");
        // MentorModel mentor3 = new MentorModel("Agnieszka", "Koszany", "agi@gmail.com");
        //
        // mentorList.add(mentor1);
        // mentorList.add(mentor2);
        // mentorList.add(mentor3);

        AdminDAO adminDao = new AdminDAO();
        MentorDAO mentorDao = new MentorDAO();
        StudentDAO studentDao = new StudentDAO();
        ClassDAO classDao = new ClassDAO();
        QuestDAO questDao = new QuestDAO();
        ArtifactDAO artifactDao = new ArtifactDAO();

        AdminModel admin = new AdminModel("Janek", "Kowalski", "das", "admin", "admin");
        adminDao.addObject(admin);

        MentorModel mentor1 = new MentorModel("Asia", "oszany", "das", "mentor1", "mentor1");
        mentorDao.addObject(mentor1);

        StudentModel student1 = new StudentModel("Kamil", "Nowak", "das", "student1", "student1");
        studentDao.addObject(student1);

        ArtifactCategoryModel category1 = new ArtifactCategoryModel("learn");
        ArtifactCategoryModel category2 = new ArtifactCategoryModel("relax");
        ArtifactModel artifact1 = new ArtifactModel("mentoring", 200, category1);
        ArtifactModel artifact2 = new ArtifactModel("free day", 500, category2);
        artifactDao.addObject(artifact1);
        artifactDao.addObject(artifact2);

        QuestCategoryModel categoryQuest = new QuestCategoryModel("owoce");
        QuestModel quest1 = new QuestModel("zadanie", 200, categoryQuest);
        questDao.addObject(quest1);

        ClassModel class1 = new ClassModel("codecoo1");
        classDao.addObject(class1);

        // System.out.println(artifactDao);

        AdminController adminController = new AdminController();
        adminController.startAdminController(mentorDao, classDao);

        // StudentController studentController = new StudentController();
        // studentController.startStudentController(student1, artifactDao, questDao);



    }
}
