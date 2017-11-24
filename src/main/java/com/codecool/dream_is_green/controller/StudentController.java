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
    Integer walletTeam = 0;
    LinkedList<ArtifactModel> li;

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
            teamShopping(httpExchange, 3);
        }  else if (userAction.equals("mail")) {
            mailController = new MailController();
            mailController.showReadMail(httpExchange, 3);
        } else if (userAction.equals("logout")) {
            clearCookie(httpExchange);
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

            TeamDao teamDao = new TeamDao();
            teamDao.loadDataAboutTeam(teamId);
            offerToBuy(teamDao.getObjectList());
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("artifactModels", li);
            model.with("title", "Team shop");
            model.with("counterMail", countMail);
            model.with("menu", "classpath:/templates/student/student_menu.twig");
            model.with("main", "classpath:/templates/student/student_team_shop.twig");
            model.with("data1", "classpath:/templates/student/data.twig");
            model.with("state", checkState(teamId));
            String titleArt = teamDao.getObjectList().get(0).getArtifactModel().getTitle();

            if (titleArt.length() == 0) {
                model.with("titleArt", "empty");
                model.with("price", "0");
            } else {
                model.with("titleArt", titleArt);
                model.with("price", teamDao.getObjectList().get(0).getArtifactModel().getPrice());
            }

            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    private void offerToBuy(LinkedList<TeamShoppingModel> teamShoppingModels) {

        ArtifactDAO artifactDAO = new ArtifactDAO();
        artifactDAO.loadArtifact();
        li = new LinkedList<>();
        walletTeam = 0;
        for (StudentModel member : teamShoppingModels.get(0).getStudentModels()) {
            walletTeam += showCoolcoins(member.getUserID());

        }
        for(ArtifactModel artifact : artifactDAO.getObjectList()) {
            if (walletTeam >= artifact.getPrice()) {
                li.add(artifact);
            }
        }
    }

    private Integer checkState(Integer teamId) {
        TeamDao teamDao = new TeamDao();
        teamDao.loadDataAboutTeam(teamId);
        Integer state = teamDao.getObjectList().get(0).getState();
        return state;
    }

    private Integer showCoolcoins(Integer userId) {
        StudentDAO studentDAO = new StudentDAO();
        StudentModel studentModel = studentDAO.getStudent(userId);
        WalletDAO walletDAO = new WalletDAO();
        walletDAO.loadCoolcoinsToWallet(studentModel);
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

                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/student_main.twig");
                JtwigModel model = JtwigModel.newModel();
                model.with("title", "Home student");
                model.with("menu", "classpath:/templates/student/student_menu.twig");
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