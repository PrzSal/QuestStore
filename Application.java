import model.*;
import controller.*;
import view.*;
import dao.*;

public class Application {

    private static final String EXIT = "E";

    public static void main(String[] args) {

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

        ArtifactCategoryModel category = new ArtifactCategoryModel("owoce");
        ArtifactModel artifact1 = new ArtifactModel("mentoring", 200, category);
        artifactDao.addObject(artifact1);

        QuestCategoryModel categoryQuest = new QuestCategoryModel("owoce");
        QuestModel quest1 = new QuestModel("zadanie", 200, categoryQuest);
        questDao.addObject(quest1);

        ClassModel class1 = new ClassModel("codecoo1");
        classDao.addObject(class1);

        loginIntoSystem(adminDao, mentorDao, studentDao, classDao, questDao, artifactDao);
    }

    public static void loginIntoSystem(AdminDAO adminDao, MentorDAO mentorDao,
                                       StudentDAO studentDao, ClassDAO classDao,
                                       QuestDAO questDao, ArtifactDAO artifactDao) {

        String operation;
        UIView view = new UIView();

        do {

            view.clearScreen();
            operation = view.getInput("~~~ Welcome in CODECOOL SHOP :)\n" +
                                             "~~~ Login into system: press any key\n" +
                                             "~~~ Exit: press E\n");

            if (!operation.equals(EXIT)) {

                String login = view.getInput("Enter login: ");
                String password = view.getInput("Enter password: ");

                MentorModel mentor = findMentor(login, mentorDao);
                AdminModel admin = findAdmin(login, adminDao);
                StudentModel student = findStudent(login, studentDao);

                if (mentor != null && mentor.getPassword().equals(password)) {

                    MentorController mentorController = new MentorController();
                    mentorController.startMentorController(studentDao, questDao, artifactDao);
                }

                else if (admin != null && admin.getPassword().equals(password)) {

                    AdminController adminController = new AdminController();
                    adminController.startAdminController(mentorDao, classDao);
                }

                else if (student != null && student.getPassword().equals(password)) {

                    StudentController studentController = new StudentController();
                    //studentController.startStudentController(mentorDao, classDao);
                }

                else {
                    view.printMessage("\nWrong login or password!\n");
                }
            }

       } while (!operation.equals(EXIT));
    }

    public static MentorModel findMentor(String login, MentorDAO mentorDao) {

        for (MentorModel mentor : mentorDao.getObjectList()) {
            if(mentor.getLogin().equals(login)) {
                return mentor;
            }
        }
        return null;
    }

    public static StudentModel findStudent(String login, StudentDAO studentDao) {

        for (StudentModel student : studentDao.getObjectList()) {
            if(student.getLogin().equals(login)) {
                return student;
            }
        }
        return null;
    }

    public static AdminModel findAdmin(String login, AdminDAO adminDao) {

        for (AdminModel admin : adminDao.getObjectList()) {
            if(admin.getLogin().equals(login)) {
                return admin;
            }
        }
        return null;
    }
}
