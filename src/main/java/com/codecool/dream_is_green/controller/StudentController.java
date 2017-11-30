package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.*;
import com.codecool.dream_is_green.model.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public class StudentController implements HttpHandler {

    private Integer countMail;

    private LinkedList<ArtifactModel> li;

    private static CookieManager cookie = new CookieManager();
    private static SessionModel session;


    public void handle(HttpExchange httpExchange) throws IOException {

        URI uri = httpExchange.getRequestURI();
        URIModel uriModel = parseURI(uri.getPath());
        String userAction = uriModel.getUserAction();

        if (userAction == null) {
            index(httpExchange);
        } else if (userAction.equals("show_quests")) {
            showQuests(httpExchange);
        } else if (userAction.equals("show_artifacts")) {
            showArtifacts(httpExchange);
        } else if (userAction.equals("wallet")) {
            showWallet(httpExchange);
        } else if (userAction.equals("buy_artifact")) {
            buyArtifact(httpExchange);
        } else if (userAction.equals("use_artifact")) {
            useArtifact(httpExchange);
        } else if (userAction.equals("do_quest")) {
            doQuest(httpExchange);
        } else if (userAction.equals("level")) {
            showLevel(httpExchange);
        } else if (userAction.equals("team_shop")) {
            teamShopping(httpExchange);
        }  else if (userAction.equals("mail")) {
            MailController mailController = new MailController();
            Integer userId = session.getUserId();
            mailController.showReadMail(httpExchange, userId);
        } else if (userAction.equals("logout")) {
            clearCookie(httpExchange);
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
            redirectToStudentHome(httpExchange, userType);
        } else {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void redirectToStudentHome(HttpExchange httpExchange, String userType) throws IOException{
        if(userType.equals("student")) {
            ResponseController<User> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, countMail,  "Home page",
                    "student/student_menu.twig","student/student_home.twig");
        } else {
            httpExchange.getResponseHeaders().set("Location", "/" + userType);
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void showQuests(HttpExchange httpExchange) throws IOException {
        QuestDAO questDAO = new QuestDAO();
        questDAO.loadQuest();
        LinkedList<QuestModel> quests = questDAO.getObjectList();
        ResponseController<QuestModel> responseController = new ResponseController<>();
        responseController.sendResponse(httpExchange, countMail, quests,
                "questsModels", "Show quests",
                "student/student_menu.twig", "student/student_show_quests.twig");
    }

    private void showArtifacts(HttpExchange httpExchange) throws IOException {
        ArtifactDAO artifactDAO = new ArtifactDAO();
        artifactDAO.loadArtifact();
        LinkedList<ArtifactModel> artifacts = artifactDAO.getObjectList();
        ResponseController<ArtifactModel> responseController = new ResponseController<>();
        responseController.sendResponse(httpExchange, countMail, artifacts,
                "artifactsModels", "Show artifacts",
                "student/student_menu.twig", "student/student_show_artifacts.twig");
    }

    private void buyArtifact(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        Integer userId = session.getUserId();
        WalletDAO walletDAO = new WalletDAO();
        Integer currentCoolCoins = walletDAO.getStudentCoolCoins(userId);

        if (method.equals("GET")) {
            ArtifactDAO artifactDAO = new ArtifactDAO();
            artifactDAO.loadArtifact();
            LinkedList<ArtifactModel> artifacts = artifactDAO.getObjectList();
            ResponseController<ArtifactModel> responseController = new ResponseController<>();
            responseController.sendBuyArtifactResponse(httpExchange, countMail, artifacts, currentCoolCoins,
                    "artifactsModels", "Buy artifact",
                    "student/student_menu.twig", "student/student_buy_artifact.twig");

        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> inputs = parseFormData(formData);
            String title = inputs.get("title").trim();
            String titleRep = title.replaceAll("\\s+","\n");
            String category = inputs.get("category").trim();
            String priceStr = inputs.get("price");
            Integer price = Integer.parseInt(priceStr);

            ArtifactDAO artifactDAO = new ArtifactDAO();
            ArtifactModel testArtifact = artifactDAO.getUserArtifact(title, userId);

            if(testArtifact == null) {
                ArtifactCategoryModel artifactCategory = new ArtifactCategoryModel(category);
                ArtifactModel artifact = new ArtifactModel(titleRep, price, artifactCategory);
                artifactDAO.insertUserArtifact(artifact, userId);

                if(currentCoolCoins - price >= 0) {
                    walletDAO.updateStudentCoolCoins(currentCoolCoins - price, userId);
                }
            }

            httpExchange.getResponseHeaders().set("Location", "/student/buy_artifact");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void useArtifact(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        Integer userId = session.getUserId();

        if (method.equals("GET")) {
            WalletDAO walletDAO = new WalletDAO();
            LinkedList<ArtifactModel> studentArtifacts = walletDAO.getStudentUnUsedArtifacts(userId);
            ResponseController<ArtifactModel> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, countMail, studentArtifacts,
                    "artifactsModels", "Use artifact",
                    "student/student_menu.twig", "student/student_use_artifact.twig");
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> inputs = parseFormData(formData);
            String title = inputs.get("title").trim();

            WalletDAO walletDAO = new WalletDAO();
            walletDAO.setArtifactOnUsed(title, userId);

            httpExchange.getResponseHeaders().set("Location", "/student/use_artifact");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void doQuest(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        Integer studentID = session.getUserId();

        if (method.equals("GET")) {
            QuestDAO questDAO = new QuestDAO();
            questDAO.loadQuest();
            LinkedList<String> titles = questDAO.loadQuestsTitle(studentID);
            LinkedList<QuestModel> quests = questDAO.getObjectList();
            System.out.println(titles);
            ResponseController<QuestModel> responseController = new ResponseController<>();
            responseController.sendQuestResponse(httpExchange, countMail, quests, titles,
                    "questsModels", "Do quest",
                    "student/student_menu.twig", "student/student_do_quest.twig");
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> inputs = parseFormData(formData);
            String title = inputs.get("title").trim();
            String category = inputs.get("category").trim();
            Integer price = Integer.valueOf(inputs.get("price"));

            QuestCategoryModel questCategoryModel = new QuestCategoryModel(category);
            QuestModel questModel = new QuestModel(title, price, questCategoryModel);

            QuestDAO questDAO = new QuestDAO();
            questDAO.insertStudentQuest(questModel, studentID);

            httpExchange.getResponseHeaders().set("Location", "/student/do_quest");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void showWallet(HttpExchange httpExchange) throws IOException {
        Integer userId = session.getUserId();

        WalletDAO walletDAO = new WalletDAO();
        LinkedList<ArtifactModel> studentArtifacts = walletDAO.getStudentArtifacts(userId);
        Integer studentCoolCoins = walletDAO.getStudentCoolCoins(userId);
        ResponseController<ArtifactModel> responseController = new ResponseController<>();
        responseController.sendResponseWallet(httpExchange, countMail, studentArtifacts,
                "artifactsModels", "Wallet", studentCoolCoins,
                "student/student_menu.twig", "student/student_show_wallet.twig");
    }

    private void showLevel(HttpExchange httpExchange) throws IOException {
        Integer userId = session.getUserId();

        WalletDAO walletDAO = new WalletDAO();
        Integer studentCoolCoins = walletDAO.getStudentCoolCoins(userId);
        LevelDAO levelDAO = new LevelDAO();
        Integer studentExp = studentCoolCoins - 1000;
        LevelModel studentLevel = levelDAO.getLevelByStudentExp(studentExp);
        LevelModel previousLevel = levelDAO.getPreviousLevel(studentExp);
        LevelModel nextLevel = levelDAO.getNextLevel(studentExp);

        ResponseController<LevelModel> responseController = new ResponseController<>();
        responseController.sendResponseLevel(httpExchange, countMail, "Level",
                studentExp, studentLevel, previousLevel, nextLevel,
                "student/student_menu.twig", "student/student_show_level.twig");
    }

    private void teamShopping(HttpExchange httpExchange) throws IOException{

        String method = httpExchange.getRequestMethod();
        Integer teamId = session.getTeamId();
        Integer userId = session.getUserId();
        Integer walletTeam = 0;

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            FormDataController<TeamShoppingModel> formDataTeamModel = new FormDataController<>();
            StudentDAO studentDAO = new StudentDAO();
            studentDAO.loadStudents();
            TeamDao teamDao = new TeamDao();
            teamDao.loadDataAboutTeam(teamId);

            MailController mailController = new MailController();
            TeamShoppingModel temporary = temporaryTeamModel(teamDao);
            Integer artifactPrice = artifactPrice(temporary);

            if (formData.compareTo("voteYes") > 0) {
                studentDAO.updateStudent(userId, "voted", "yes");
                TeamShoppingModel teamShoppingModel = formDataTeamModel.parseFormData(formData, "voteYes", teamId, userId);
                teamDao.updateDataAboutTeam(teamId, "votes", String.valueOf(teamShoppingModel.getVotes()));
                checkMaxVotes(teamShoppingModel);
                teamDao.updateDataAboutTeam(teamId, "state", String.valueOf(teamShoppingModel.getState()));

            }  else if (formData.compareTo("voteNo") > 0) {
                FormDataController<PreMailModel> formDataController = new FormDataController<>();
                PreMailModel preMailModel = formDataController.parseFormData(formData, "voteNo", teamId, userId);
                TeamShoppingModel teamShoppingModel = teamDao.getObjectList().get(0);
                List<StudentModel> students = teamShoppingModel.getStudentModels();
                String header = preMailModel.getHeader();
                String content = preMailModel.getContent();
                mailController.sendMultiplyMailToStudents(students, content, header, userId);
                resetDataInTeamDao(teamShoppingModel, studentDAO, teamDao, teamId, artifactPrice);

            } else if (formData.compareTo("newPurchase") > 0) {
                TeamShoppingModel teamShoppingModel = formDataTeamModel.parseFormData(formData, "newPurchase", teamId, userId);
                teamDao.updateDataAboutTeam(teamId, "artifact_id", teamShoppingModel.getArtifactModel().getTitle());
                teamDao.updateDataAboutTeam(teamId, "state", String.valueOf(teamShoppingModel.getState()));

            } else if (formData.compareTo("mark") > 0) {
                TeamShoppingModel teamShoppingModel = formDataTeamModel.parseFormData(formData, "mark", teamId, userId);
                resetDataInTeamDao(teamShoppingModel, studentDAO, teamDao, teamId, artifactPrice);
                String header = "Use an artifact";
                String content = "Dear Codecooler \n, Your team use an artifact: " + teamShoppingModel.getArtifactModel().getTitle() + ". " +
                                 "You will receive detailed information soon from the Mentor. " +
                                 "Regards, Your Mentor ";
                MentorDAO mentorDAO = new MentorDAO();
                mentorDAO.loadMentors();
                StudentModel studentModel = teamShoppingModel.getStudentModels().get(0);
                List<MentorModel> mentors = mentorDAO.getMentors(studentModel);
                mailController.sendMultiplyMailToStudents(teamShoppingModel.getStudentModels(), content, header, mentors.get(0).getUserID());

                header = "New group purchase from " + temporary.getNameTeam() + " .";
                content = "Dear Mentors, team " + temporary.getNameTeam() + " buy artifact: " + temporary.getArtifactModel().getTitle() +
                          ". Please contact the team to discuss the purchase. Regards Admin";
                mailController.sendMultiplyMailToMentors(mentors, content, header, 1);

            }

            httpExchange.getResponseHeaders().set("Location", "/student/team_shop");
            httpExchange.sendResponseHeaders(302, -1);
        }

        if (method.equals("GET")) {
            TeamDao teamDao = new TeamDao();
            teamDao.loadDataAboutTeam(teamId);
            TeamShoppingModel teamShoppingModel = teamDao.getObjectList().get(0);
            walletTeam = getWalletTeam(teamShoppingModel);
            LinkedList<ArtifactModel> artifactToBuy = offerToBuy(walletTeam);
            List<StudentModel> members = teamShoppingModel.getStudentModels();
            Integer voted = checkVoted(userId);
            Integer state = checkState(teamId);
            String teamName = teamShoppingModel.getNameTeam();
            ResponseController<ArtifactModel> responseController = new ResponseController<>();
            responseController.sendResponseTeaamShop(httpExchange, countMail, artifactToBuy, state, voted, teamDao, teamName, walletTeam, members);
        }
    }

    private LinkedList<ArtifactModel> offerToBuy(Integer walletTeam ) {

        ArtifactDAO artifactDAO = new ArtifactDAO();
        artifactDAO.loadArtifact();
        li = new LinkedList<>();
        for(ArtifactModel artifact : artifactDAO.getObjectList()) {
            if (walletTeam >= artifact.getPrice()) {
                li.add(artifact);
            }
        }
        return li;
    }

    private Integer getWalletTeam(TeamShoppingModel teamShoppingModel) {
        Integer walletTeam = 0;
        for (StudentModel member : teamShoppingModel.getStudentModels()) {
            walletTeam += showCoolcoins(member.getUserID());

        }
        if (teamShoppingModel.getArtifactModel().getTitle() != null) {
            walletTeam -= teamShoppingModel.getArtifactModel().getPrice();
        }

        return walletTeam;
    }

    private Integer artifactPrice(TeamShoppingModel teamShoppingModel) {
        Integer price = teamShoppingModel.getArtifactModel().getPrice();
        return price;
    }

    private Integer checkState(Integer teamId) {
        TeamDao teamDao = new TeamDao();
        teamDao.loadDataAboutTeam(teamId);
        Integer state = teamDao.getObjectList().get(0).getState();
        return state;
    }

    private Integer checkVoted(Integer userId) {
        StudentDAO studentDAO = new StudentDAO();
        String voted = studentDAO.getStudent(userId).getVoted();
        System.out.println(voted);
        if (voted.equals("yes")) {
            return 1;
        } else {
            return 0;
        }
    }

    private void checkMaxVotes(TeamShoppingModel teamShoppingModel) {
        Integer counterVotes =  teamShoppingModel.getStudentModels().size();
        if (teamShoppingModel.getVotes() == counterVotes) {
            teamShoppingModel.setState(2);
        }
    }

    private Integer showCoolcoins(Integer userId) {
        StudentDAO studentDAO = new StudentDAO();
        StudentModel studentModel = studentDAO.getStudent(userId);
        WalletDAO walletDAO = new WalletDAO();
        walletDAO.loadCoolcoinsToWallet(studentModel);
        return studentModel.getWallet().getCoolCoins();
    }

    private void clearCookie(HttpExchange httpExchange) throws IOException {
        cookie.cleanCookie(httpExchange);

        httpExchange.getResponseHeaders().set("Location", "/login");
        httpExchange.sendResponseHeaders(302,-1);
    }

    private TeamShoppingModel temporaryTeamModel(TeamDao teamDao) {
        TeamShoppingModel teamShoppingModel = teamDao.getObjectList().get(0);
        return teamShoppingModel;
    }

    private void resetDataInTeamDao(TeamShoppingModel teamShoppingModel, StudentDAO studentDAO, TeamDao teamDao, Integer teamId, Integer artifactPrice) {
        teamShoppingModel.setState(0);
        teamShoppingModel.setVotes(0);
        WalletDAO walletDAO = new WalletDAO();
        Integer priceToUser = artifactPrice / teamShoppingModel.getStudentModels().size();
        Integer state = teamShoppingModel.getState();
        Integer votes = teamShoppingModel.getVotes();
        teamShoppingModel.getArtifactModel().setTitle(null);
        String artifactId = teamShoppingModel.getArtifactModel().getTitle();

        for (StudentModel student : teamShoppingModel.getStudentModels()) {
            walletDAO.loadCoolcoinsToWallet(student);
            Integer wallet = student.getWallet().getCoolCoins() - priceToUser;
            walletDAO.updateStudentCoolCoins(wallet, student.getUserID());
            studentDAO.updateStudent(student.getUserID(), "voted", "no");
        }

        teamDao.updateDataAboutTeam(teamId, "artifact_id", artifactId);
        teamDao.updateDataAboutTeam(teamId, "state", String.valueOf(state));
        teamDao.updateDataAboutTeam(teamId, "votes", String.valueOf(votes));
    }

    private URIModel parseURI (String uri) {
        String[] pairs = uri.split("/");
        URIModel uriModel = new URIModel();

        if (pairs.length == 3) {
            uriModel = new URIModel(pairs[2]);
        }
        return uriModel;
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