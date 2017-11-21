package com.codecool.dream_is_green.controller;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public class ResponseController<T> {

    public void sendResponse(HttpExchange httpExchange, LinkedList<T> objectsList,
                             String objectModels, String title,
                             String path) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with(objectModels, objectsList);
        model.with("title", title);
        model.with("menu", "classpath:/templates/admin/menu_admin.twig");
        model.with("main", "classpath:/templates/admin/" + path + ".twig");
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
