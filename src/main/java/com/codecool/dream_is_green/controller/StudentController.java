package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.*;
import com.codecool.dream_is_green.model.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public class StudentController implements HttpHandler {

    Integer countMail;
    Integer walletStudent;

    private static CookieManager cookie = new CookieManager();

    public void handle(HttpExchange httpExchange) throws IOException {

        URI uri = httpExchange.getRequestURI();
        URIModel uriModel = parseURI(uri.getPath());
        String userAction = uriModel.getUserAction();
        MailController mailController = new MailController();
        countMail = mailController.checkMail(3);
        walletStudent = showCoolcoins(3);


        if (userAction == null) {
            index(httpExchange);
        } else if (userAction.equals("team_shop")) {
            teamShopping(httpExchange, 1 );
        }  else if (userAction.equals("mail")) {
            mailController = new MailController();
            mailController.showReadMail(httpExchange, 3);
        }
    }

    private void teamShopping(HttpExchange httpExchange, Integer teamId) throws IOException{
        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
//            FormDataController<ClassModel> classModel = new FormDataController<>();
//            ClassDAO classDao = new ClassDAO();
//            classDao.insertClass(classModel.parseFormData(formData, "class"));
//            httpExchange.getResponseHeaders().set("Location", "/admin/show_classes");
//            httpExchange.sendResponseHeaders(302, -1);
        }

        if (method.equals("GET")) {
            ResponseController<TeamShoppingModel> responseController = new ResponseController<>();
            TeamDao teamDao = new TeamDao();
            teamDao.loadDataAboutTeam(teamId);
            LinkedList<TeamShoppingModel> teamShoppingModels = teamDao.getObjectList();
            responseController.sendResponse(httpExchange, countMail, teamShoppingModels, "teamShopModels",
                    "Team shop", "student_team_shop", "student");

        }
    }

    private Integer showCoolcoins(Integer userId) {
        StudentDAO studentDAO = new StudentDAO();
        StudentModel studentModel = studentDAO.getStudent(userId);
        WalletDAO walletDAO = new WalletDAO();
        walletDAO.loadCoolcoinsToWallet(studentModel);
        System.out.println(studentModel.getWallet().getCoolCoins());
        return studentModel.getWallet().getCoolCoins();
    }

    private void index(HttpExchange httpExchange) throws IOException {

        cookie.redirectIfCookieNull(httpExchange);
        String sessionId = cookie.getSessionId(httpExchange);
        SessionDAO sessionDAO = new SessionDAO();
        SessionModel session = sessionDAO.getSession(sessionId);

        if (session != null) {

            String userType = session.getUserType();

            if(userType.equals("student")) {

                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
                JtwigModel model = JtwigModel.newModel();
                model.with("title", "Home student");
                model.with("menu", "classpath:/templates/student/menu_student.twig");
                model.with("main", "classpath:/templates/student/student_home.twig");
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