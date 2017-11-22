package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.ClassDAO;
import com.codecool.dream_is_green.dao.LevelDAO;
import com.codecool.dream_is_green.dao.MentorDAO;
import com.codecool.dream_is_green.dao.SessionDAO;
import com.codecool.dream_is_green.model.LevelModel;
import com.codecool.dream_is_green.model.PreUserModel;
import com.codecool.dream_is_green.model.URIModel;

import com.codecool.dream_is_green.model.SessionModel;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentController implements HttpHandler {

    Integer countMail;
    private static CookieManager cookie = new CookieManager();

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

        cookie.redirectIfCookieNull(httpExchange);
        String sessionId = cookie.getSessionId(httpExchange);
        SessionDAO sessionDAO = new SessionDAO();
        SessionModel session = sessionDAO.getSession(sessionId);

        if (session != null) {

            String userType = session.getUserType();

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

    private void clearCookie(HttpExchange httpExchange) throws IOException {
        cookie.cleanCookie(httpExchange);

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