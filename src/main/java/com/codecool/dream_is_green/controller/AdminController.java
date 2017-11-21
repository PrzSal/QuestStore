package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.ClassDAO;
import com.codecool.dream_is_green.dao.LevelDAO;
import com.codecool.dream_is_green.dao.MentorDAO;
import com.codecool.dream_is_green.dao.SessionDAO;
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
        String redirect = "";

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            MentorDAO mentorDAO = new MentorDAO();
            String name = parseFormData(formData).get(0);
            String surname = parseFormData(formData).get(1);
            String email = parseFormData(formData).get(2);
            String login = parseFormData(formData).get(3);
            String password = parseFormData(formData).get(4);
            String className = parseFormData(formData).get(5);
            mentorDAO.insertMentor(new PreUserModel(name, surname, email, login, password, className));
            redirect = "<meta http-equiv=\"refresh\" content=\"0; url=/admin/show_mentors/\" />";
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();
        ClassDAO classDAO = new ClassDAO();
        classDAO.loadClasses();
        model.with("title", "Add class");
        model.with("menu", "classpath:/templates/admin/menu_admin.twig");
        model.with("main", "classpath:/templates/admin/admin_add_mentor.twig");
        model.with("classModels", classDAO.getObjectList());
        model.with("redirect", redirect);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
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
        String redirect = "";

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            String levelName = parseFormData(formData).get(0);
            String expRequired = parseFormData(formData).get(1);
            LevelDAO levelDAO = new LevelDAO();
            LevelModel levelModel = new LevelModel(levelName, Integer.valueOf(expRequired));
            levelDAO.insertLevel(levelModel);
            redirect = "<meta http-equiv=\"refresh\" content=\"0; url=/admin/show_levels/\" />";
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("title", "Add class");
        model.with("menu", "classpath:/templates/admin/menu_admin.twig");
        model.with("main", "classpath:/templates/admin/admin_create_level.twig");
        model.with("redirect", redirect);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
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
            ClassDAO classDao = new ClassDAO();
            classDao.insertClass(parseFormData(formData).get(0));
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

    private void edit(HttpExchange httpExchange, String id) {

    }

    private void delete(int id) {

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

    private Map<String, String> parseURI (String uri) {
        Map<String, String> actionData = new HashMap<>();
        String[] pairs = uri.split("/");

        if (pairs.length == 4) {
            actionData.put(pairs[2], pairs[3]);
        } else if (pairs.length == 3) {
            actionData.put(pairs[2], "");
        } else if (pairs.length == 2) {
            actionData.put(pairs[1], "");
        } else {
            actionData.put("", "");
        }

        return actionData;
    }

    private ArrayList<String> parseFormData(String formData) {
        System.out.println(formData);
        ArrayList<String> dataToModel = new ArrayList<>();

        String[] pairs = formData.split("&");
        int i = 0;
        try {
            for (String pair : pairs) {
                dataToModel.add(new URLDecoder().decode(pair.split("=")[1], "UTF-8"));

            }
        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            return null;
        }

        return dataToModel;
    }
}