package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.MailBoxDao;
import com.codecool.dream_is_green.model.MailBoxModel;
import com.codecool.dream_is_green.model.PreMailModel;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MailController {

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
            responseController.sendResponse(httpExchange, checkMail(userId), mails,
                    "mailModels", "Show Mail",
                    "admin/menu_admin.twig", "admin/admin_mail.twig");
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            System.out.println(formData);
            FormDataController <PreMailModel> formDataController = new FormDataController<>();
            MailBoxDao mailBoxDao = new MailBoxDao();
            mailBoxDao.insertMail(formDataController.parseFormData(formData, "mail"));
            httpExchange.getResponseHeaders().set("Location", "/mail");
            httpExchange.sendResponseHeaders(302, -1);
        }
    }
}