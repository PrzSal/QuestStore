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

        MentorDAO mentorDao = new MentorDAO();
        ClassDAO classDao = new ClassDAO();

        MentorModel mentor1 = new MentorModel("Asia", "oszany", "das", "mentor1", "mentor1");
        mentorDao.addObject(mentor1);

        ClassModel class1 = new ClassModel("codecoo1");
        classDao.addObject(class1);



        AdminController admin = new AdminController();
        admin.startAdminController(mentorDao, classDao);



    }
}
