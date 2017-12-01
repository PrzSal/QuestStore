package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.*;
import com.codecool.dream_is_green.model.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public class AdminController implements HttpHandler {

    private Integer countMail;

    private static CookieManager cookie = new CookieManager();
    private static SessionModel session;

    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        FormDataController formDataController = new FormDataController();
        URIModel uriModel = formDataController.parseURI(uri.getPath());
        String userAction = uriModel.getUserAction();
        cookie.refreshCookie(httpExchange);


        if (userAction == null) {
            index(httpExchange);
        } else if (userAction.equals("manage_classes")) {
            manageClasses(httpExchange);
        } else if (userAction.equals("add_mentor")) {
            addMentor(httpExchange);
        } else if (userAction.equals("show_mentors")) {
            showMentors(httpExchange);
        } else if (userAction.equals("add_level")) {
            addLevel(httpExchange);
        } else if (userAction.equals("show_levels")) {
            showLevels(httpExchange);
        } else if (userAction.equals("mail")) {
            MailController mailController = new MailController();
            Integer userId = session.getUserId();
            mailController.showReadMail(httpExchange, userId);
        } else if (userAction.equals("logout")) {
            cookie.resetSession(httpExchange);
        }
    }

    private void index(HttpExchange httpExchange) throws IOException {

        cookie.redirectIfCookieNull(httpExchange);
        String sessionId = cookie.getSessionId(httpExchange);
        SessionDAO sessionDAO = new SessionDAO();
        session = sessionDAO.getSession(sessionId);

        if (session != null) {
            Integer userId = session.getUserId();
            MailController mailController = new MailController();
            countMail = mailController.checkMail(userId);

            String userType = session.getUserType();
            redirectToAdminHome(httpExchange, userType);
        } else {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void redirectToAdminHome(HttpExchange httpExchange,
                                     String userType) throws IOException{
        if(userType.equals("admin")) {
            ResponseController<User> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, countMail, "Home page",
                    "admin/menu_admin.twig","admin/admin_home.twig");
        } else {
            httpExchange.getResponseHeaders().set("Location", "/" + userType);
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void addMentor(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        addMentorPost(httpExchange, method);
        if (method.equals("GET")) {
            ClassDAO classDAO = new ClassDAO();
            classDAO.loadClasses();
            LinkedList<ClassModel> classes = classDAO.getObjectList();
            ResponseController<ClassModel> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, countMail, classes,
                    "classModels", "Add mentor",
                    "admin/menu_admin.twig", "admin/admin_add_mentor.twig");

        }
    }

    private void addMentorPost(HttpExchange httpExchange, String method) throws IOException {
        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            MentorDAO mentorDAO = new MentorDAO();
            FormDataController<PreUserModel> preUser = new FormDataController<>();
            mentorDAO.insertMentor(preUser.parseFormData(formData, "preUser"));
            httpExchange.getResponseHeaders().set("Location", "/admin/show_mentors");
            httpExchange.sendResponseHeaders(302, -1);
        }

    }

    private void addLevel(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            FormDataController<LevelModel> level = new FormDataController<>();
            LevelDAO levelDAO = new LevelDAO();
            levelDAO.insertLevel(level.parseFormData(formData, "level"));
            httpExchange.getResponseHeaders().set("Location", "/admin/show_levels");
            httpExchange.sendResponseHeaders(302, -1);
        }

        if (method.equals("GET")) {
            ResponseController<LevelModel> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, countMail, "Add level",
                    "admin/menu_admin.twig","admin/admin_create_level.twig");
        }
    }

    private void showMentors(HttpExchange httpExchange) throws IOException {
        MentorDAO mentorDAO = new MentorDAO();
        mentorDAO.loadMentors();
        LinkedList<MentorModel> mentors = mentorDAO.getObjectList();
        ResponseController<MentorModel> responseController = new ResponseController<>();
        responseController.sendResponse(httpExchange, countMail, mentors,
                "mentorModels", "Show mentors",
                "admin/menu_admin.twig", "admin/admin_show_mentors.twig");
    }

    private void showLevels(HttpExchange httpExchange) throws IOException {
        LevelDAO levelDAO = new LevelDAO();
        levelDAO.loadLevels();
        LinkedList<LevelModel> levels = levelDAO.getObjectList();
        ResponseController<LevelModel> responseController = new ResponseController<>();
        responseController.sendResponse(httpExchange, countMail, levels,
                "levelModels", "Show levels",
                "admin/menu_admin.twig","admin/admin_show_levels.twig");
    }

    private void manageClasses(HttpExchange httpExchange) throws IOException {
        ClassDAO classDAO = new ClassDAO();
        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            classDAO.loadClasses();
            LinkedList<ClassModel> classes = classDAO.getObjectList();
            ResponseController<ClassModel> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, countMail, classes,
                    "classModels", "Manage classes",
                    "admin/menu_admin.twig", "admin/admin_manage_classes.twig");

        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> inputs = parseFormData(formData);
            String className = inputs.get("className").trim();
            String option = inputs.get("button");

            ClassModel classModel = new ClassModel(className);
            if (option.equals("Add")) {
                classDAO.insertClass(classModel);
            } else if (option.equals("Remove")) {
                classDAO.deleteClass(className);
            }

            httpExchange.getResponseHeaders().set("Location", "/mentor/manage_classes");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}