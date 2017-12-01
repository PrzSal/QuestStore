package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.MailBoxDao;
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

    public void showReadMail(HttpExchange httpExchange, Integer userId) throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {

            MailBoxDao mailBoxDao = new MailBoxDao();
            mailBoxDao.loadReadMail(userId, 1);
            mailBoxDao.loadReadMail(userId, 0);
            LinkedList<MailBoxModel> mails = mailBoxDao.getObjectList();
            ResponseController<MailBoxModel> responseController = new ResponseController();
            responseController.sendResponseMail(httpExchange, checkMail(userId), mails, response);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            FormDataController <PreMailModel> formDataController = new FormDataController<>();
            MailBoxDao mailBoxDao = new MailBoxDao();
            mailBoxDao.insertMail(formDataController.parseFormData(formData, "mail"));
            httpExchange.getResponseHeaders().set("Location", "/mail");
            httpExchange.sendResponseHeaders(302, -1);
        }
    }


//    public void showReadMail(HttpExchange httpExchange, Integer userId) throws IOException {
//        String method = httpExchange.getRequestMethod();
//        MailBoxDao mailBoxDao = new MailBoxDao();
//        mailBoxDao.loadReadMail(userId, 0);
//        MailBoxModel mailBoxModel = mailBoxDao.getObjectList().get(0);
//        if (method.equals("POST")) {
//
//            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
//            BufferedReader br = new BufferedReader(isr);
//            String formData = br.readLine();
//            FormDataController <PreMailModel> formDataController = new FormDataController<>();
//            mailBoxDao.insertMail(formDataController.parseFormData(formData, "mail"));
//            httpExchange.getResponseHeaders().set("Location", "/"+mailBoxModel.getUserType()+"/mail");
//            httpExchange.sendResponseHeaders(302, -1);
//        }
//        if (method.equals("GET")) {
//            mailBoxDao.loadReadMail(userId, 0);
//            LinkedList<MailBoxModel> mails = mailBoxDao.getObjectList();
//            MailBoxDao mailBoxDaoAll = new MailBoxDao();
//            mailBoxDaoAll.loadReadMailAll();
//            LinkedList<MailBoxModel> allMails = mailBoxDao.getObjectList();
//            MailBoxModel mailBoxModelAll = allMails.getLast();
//            response = mailBoxModelAll.getReact();
//            ResponseController<MailBoxModel> responseController = new ResponseController();
//            responseController.sendResponseMail(httpExchange, checkMail(userId), mails, response);
//            mailBoxDao.updateReact(mailBoxModel.getIdMail());
//        }
//    }

    public void sendMultiplyMailToMentors(List<MentorModel> mentorsToSendMail, String content, String header, Integer idSender) {
        MailBoxDao mailBoxDao = new MailBoxDao();
        for (MentorModel mentor : mentorsToSendMail) {
            PreMailModel preMailModel = new PreMailModel(content, header, mentor.getUserID(), idSender);
            mailBoxDao.insertMail(preMailModel);
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
