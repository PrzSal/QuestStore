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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentController implements HttpHandler {

    Integer countMail;

    public void handle(HttpExchange httpExchange) throws IOException {

        URI uri = httpExchange.getRequestURI();
        URIModel uriModel = parseURI(uri.getPath());
        String userAction = uriModel.getUserAction();
        MailController mailController = new MailController();
        countMail = mailController.checkMail(3);

        if (userAction == null) {
            index(httpExchange);
        } else if (userAction.equals("team_shopping")) {
            teamShopping(httpExchange);
        }  else if (userAction.equals("mail")) {
            mailController = new MailController();
            mailController.showReadMail(httpExchange, 3);
        }
    }

    private void teamShopping(HttpExchange httpExchange) {

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

                if(userType.equals("student")) {

                    JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/student_home.twig");
                    JtwigModel model = JtwigModel.newModel();
                    String response = template.render(model);

                    httpExchange.sendResponseHeaders(200, response.getBytes().length);
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