package com.codecool.dream_is_green.controller.controllers;

import com.codecool.dream_is_green.dao.ClassDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public class AdminController implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        URI uri = httpExchange.getRequestURI();
        Map<String, String> actionData = parseURI(uri.getPath());

        for (String action : actionData.keySet()) {
            if (action.equals("add")) {
                add(httpExchange);
            } else if (action.equals("edit")) {
                edit(httpExchange, actionData.get(action));
            } else {
                index(httpExchange);
            }
        }
    }

    private void index(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/admin/admin_show_classes.html.twig");
        JtwigModel model = JtwigModel.newModel();

        ClassDAO classDao = new ClassDAO();
        classDao.loadClasses();
        model.with("classModels", classDao.getObjectList());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void add(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        String redirect = "";

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            ClassDAO classDao = new ClassDAO();
            classDao.insertClass(parseFormData(formData));
            redirect = "<meta http-equiv=\"refresh\" content=\"0; url=/show_class/\" />";
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/admin/admin_add_class.html.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("redirect", redirect);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void edit(HttpExchange httpExchange, String id) {

    }

    private void delete(int id) {

    }

    private Map<String, String> parseURI (String uri) {
        Map<String, String> actionData = new HashMap<>();
        String[] pairs = uri.split("/");

        if (pairs.length == 4) {
            actionData.put(pairs[2], pairs[3]);
        } else if (pairs.length == 3) {
            actionData.put(pairs[2], "");
        } else {
            actionData.put("", "");
        }

        return actionData;
    }

    private String parseFormData(String formData) {
        System.out.println(formData);
        String className;

        String[] pairs = formData.split("&");

        try {
            className = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");
        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            return null;
        }

        return className;
    }
}