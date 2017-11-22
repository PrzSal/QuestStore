package com.codecool.dream_is_green.controller;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public class ResponseController<T> {

    public void sendResponse(HttpExchange httpExchange, Integer counterMail, LinkedList<T> objectsList,
                             String objectModels, String title,
                             String path, String userType) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with(objectModels, objectsList);
        model.with("title", title);
        if (userType.equals("admin")) {
            model.with("menu", "classpath:/templates/admin/menu_admin.twig");
            model.with("main", "classpath:/templates/admin/" + path + ".twig");
        } else if (userType.equals("mentor")) {
            model.with("menu", "classpath:/templates/mentor/menu_mentor.twig");
            model.with("main", "classpath:/templates/mentor/" + path + ".twig");
        } else if (userType.equals("student")) {
            model.with("menu", "classpath:/templates/student/menu_student.twig");
            model.with("main", "classpath:/templates/student/" + path + ".twig");
        }
        model.with("counterMail", counterMail);

        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void sendResponse(HttpExchange httpExchange, String title,
                             String path) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("title", title);
        model.with("menu", "classpath:/templates/admin/menu_admin.twig");
        model.with("main", "classpath:/templates/admin/" + path + ".twig");
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
