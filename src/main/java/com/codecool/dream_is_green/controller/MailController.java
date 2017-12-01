package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.MailBoxDao;
import com.codecool.dream_is_green.dao.SessionDAO;
import com.codecool.dream_is_green.model.*;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MailController {
    Integer response=0;

    public Integer checkMail(Integer userId ) {

        MailBoxDao mailBoxDao = new MailBoxDao();
        mailBoxDao.loadReadMail(userId, 1);
        Integer counter = mailBoxDao.getObjectList().size();
        return counter;

    }

    public void showReadMail(HttpExchange httpExchange) throws IOException {
        CookieManager cookie = new CookieManager();
        String sessionId = cookie.getSessionId(httpExchange);
        SessionDAO sessionDAO = new SessionDAO();
        SessionModel session = sessionDAO.getSession(sessionId);
        String userType = session.getUserType();
        Integer userId = session.getUserId();
        System.out.println(userId);
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {

            MailBoxDao mailBoxDao = new MailBoxDao();
            mailBoxDao.loadReadMail(userId, 1);
            mailBoxDao.loadReadMail(userId, 0);
            LinkedList<MailBoxModel> mails = mailBoxDao.getObjectList();
            ResponseController<MailBoxModel> responseController = new ResponseController();
            String menu = null;
            if (userType.equals("admin")){
                menu = "admin/menu_admin.twig";
            } else if (userType.equals("mentor")) {
                menu = "mentor/menu_mentor.twig";
            } else if (userType.equals("student")) {
                menu = "student/student_menu.twig";
            }
            responseController.sendResponse(httpExchange, session, checkMail(userId), mails,
                    "mailModels", "Show Mail",
                    menu, "admin/admin_mail.twig");
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            FormDataController <PreMailModel> formDataController = new FormDataController<>();
            MailBoxDao mailBoxDao = new MailBoxDao();
            mailBoxDao.insertMail(formDataController.parseFormData(formData, "mail"));
            httpExchange.getResponseHeaders().set("Location", "/"+session.getUserType()+"/mail");
            httpExchange.sendResponseHeaders(302, -1);
        }
    }

    public void sendMultiplyMailToMentors(List<MentorModel> mentorsToSendMail, String content, String header, Integer idSender) {
        MailBoxDao mailBoxDao = new MailBoxDao();
        Integer temporaryId = 0;
        int i = 0;
        for (MentorModel mentor : mentorsToSendMail) {
            if (i > 0) {
                temporaryId = mentorsToSendMail.get(i-1).getUserID();
            }
            if (!temporaryId.equals(mentor.getUserID())) {
                PreMailModel preMailModel = new PreMailModel(content, header, mentor.getUserID(), idSender);
                mailBoxDao.insertMail(preMailModel);
            }
            i++;
        }
    }

    public void sendMultiplyMailToStudents(List<StudentModel> studentsToSendMail, String content, String header, Integer idSender) {
        MailBoxDao mailBoxDao = new MailBoxDao();
        for (StudentModel student : studentsToSendMail) {
            PreMailModel preMailModel = new PreMailModel(content, header, student.getUserID(), idSender);
            mailBoxDao.insertMail(preMailModel);
        }
    }

}
