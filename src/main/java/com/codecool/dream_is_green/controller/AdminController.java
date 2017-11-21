package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.ClassDAO;
import com.codecool.dream_is_green.dao.LevelDAO;
import com.codecool.dream_is_green.dao.MentorDAO;
import com.codecool.dream_is_green.dao.SessionDAO;
import com.codecool.dream_is_green.model.ClassModel;
import com.codecool.dream_is_green.model.LevelModel;
import com.codecool.dream_is_green.model.PreUserModel;
import com.codecool.dream_is_green.model.URIModel;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;
import java.util.logging.Level;

public class AdminController implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        URIModel uriModel = parseURI(uri.getPath());
        String userAction = uriModel.getUserAction();

        if (userAction == null) {
            index(httpExchange);
        } else if (userAction.equals("add_class")) {
            addClass(httpExchange);
        } else if (userAction.equals("show_classes")) {
            showClasses(httpExchange);
        } else if (userAction.equals("add_mentor")) {
            addMentor(httpExchange);
        } else if (userAction.equals("show_mentors")) {
            showMentors(httpExchange);
        } else if (userAction.equals("add_level")) {
            addLevel(httpExchange);
        } else if (userAction.equals("show_levels")) {
            showLevels(httpExchange);
        } else if (userAction.equals("logout")) {
            clearCookie(httpExchange);
        }
    }

    private void index(HttpExchange httpExchange) throws IOException {

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        if(cookieStr == null) {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(302,-1);

        } else {
            HttpCookie cookie;
            cookie = HttpCookie.parse(cookieStr).get(0);
            String sessionId = cookie.getValue();
            Map sessionMap = SessionDAO.getSession();

            if (sessionMap.containsKey(sessionId)) {

                MentorDAO mentorDAO = new MentorDAO();
                String userName = (String) sessionMap.get(sessionId);
                String userType = mentorDAO.getUserType(userName);

                if(userType.equals("admin")) {

                    JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
                    JtwigModel model = JtwigModel.newModel();
                    model.with("title", "Home admin");
                    model.with("menu", "classpath:/templates/admin/menu_admin.twig");
                    model.with("main", "classpath:/templates/admin/admin_home.twig");
                    String response = template.render(model);

                    httpExchange.sendResponseHeaders(200, response.length());
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                } else {
                    httpExchange.getResponseHeaders().set("Location", "/" + userType);
                    httpExchange.sendResponseHeaders(302,-1);
                }

            } else {
                httpExchange.getResponseHeaders().set("Location", "/login");
                httpExchange.sendResponseHeaders(302,-1);
            }
        }
    }

    private void showClasses(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();
        ClassDAO classDAO = new ClassDAO();
        classDAO.loadClasses();
        model.with("classModels", classDAO.getObjectList());
        model.with("title", "Show classes");
        model.with("menu", "classpath:/templates/admin/menu_admin.twig");
        model.with("main", "classpath:/templates/admin/admin_show_classes.twig");
        String response = template.render(model);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void addMentor(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

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

        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
            JtwigModel model = JtwigModel.newModel();
            ClassDAO classDAO = new ClassDAO();
            classDAO.loadClasses();
            model.with("title", "Add class");
            model.with("menu", "classpath:/templates/admin/menu_admin.twig");
            model.with("main", "classpath:/templates/admin/admin_add_mentor.twig");
            model.with("classModels", classDAO.getObjectList());
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private void showMentors(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();

        MentorDAO mentorDAO = new MentorDAO();
        mentorDAO.loadMentors();
        model.with("title", "Show mentors");
        model.with("menu", "classpath:/templates/admin/menu_admin.twig");
        model.with("main", "classpath:/templates/admin/admin_show_mentors.twig");
        model.with("mentorModels", mentorDAO.getObjectList());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
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
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("title", "Add class");
            model.with("menu", "classpath:/templates/admin/menu_admin.twig");
            model.with("main", "classpath:/templates/admin/admin_create_level.twig");
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
  
    private void showLevels(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();

        LevelDAO levelDAO = new LevelDAO();
        levelDAO.loadLevels();
        model.with("levelModels", levelDAO.getObjectList());
        model.with("title", "Show levels");
        model.with("menu", "classpath:/templates/admin/menu_admin.twig");
        model.with("main", "classpath:/templates/admin/admin_show_levels.twig");
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void addClass(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            FormDataController<ClassModel> classModel = new FormDataController<>();
            ClassDAO classDao = new ClassDAO();
            classDao.insertClass(classModel.parseFormData(formData, "class"));
            httpExchange.getResponseHeaders().set("Location", "/admin/show_classes");
            httpExchange.sendResponseHeaders(302, -1);
        }

        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("title", "Add class");
            model.with("menu", "classpath:/templates/admin/menu_admin.twig");
            model.with("main", "classpath:/templates/admin/admin_add_class.twig");
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private void clearCookie(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        HttpCookie cookie;
        cookie = HttpCookie.parse(cookieStr).get(0);
        SessionDAO.deleteSession(cookie.getValue());
        httpExchange.getResponseHeaders().add("Set-cookie", "first=" + cookie.getValue() + "; Max-Age=0; Path=/");

        httpExchange.getResponseHeaders().set("Location", "/login");
        httpExchange.sendResponseHeaders(302,-1);
    }

    private URIModel parseURI (String uri) {
        String[] pairs = uri.split("/");
        URIModel uriModel = new URIModel();

        if (pairs.length == 3) {
            uriModel = new URIModel(pairs[2]);
        }
        return uriModel;
    }
}