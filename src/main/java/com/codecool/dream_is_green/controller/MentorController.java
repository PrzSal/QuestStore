package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.*;
import com.codecool.dream_is_green.model.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;

public class MentorController implements HttpHandler {

    private Integer countMail;
    private Integer userId = 0;
    private Integer state = 0;
    private LinkedList<StudentModel> temporaryStudents;
    private TeamShoppingModel teamShoppingModel;
    private static CookieManager cookie = new CookieManager();

    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        FormDataController formDataController = new FormDataController();
        URIModel uriModel = formDataController.parseURI(uri.getPath());
        String userAction = uriModel.getUserAction();
        MailController mailController = new MailController();
        countMail = mailController.checkMail(10);

        if (userAction == null) {
            index(httpExchange);
//        } else if (userAction.equals("add_student")) {
//            addStudent(httpExchange);
        } else if (userAction.equals("add_artifact")) {
            manageArtifact(httpExchange);
        } else if (userAction.equals("mark_quest")) {
            markQuest(httpExchange);
        } else if (userAction.equals("show_students")) {
            showStudents(httpExchange);
        } else if (userAction.equals("create_team")) {
            createTeam(httpExchange);
        } else if (userAction.equals("show_artifacts")) {
            showArtifacts(httpExchange);
        } else if (userAction.equals("show_quests")) {
            showQuests(httpExchange);
        } else if (userAction.equals("logout")) {
            clearCookie(httpExchange);
        } else if (userAction.equals("mail")) {
            mailController = new MailController();
            mailController.showReadMail(httpExchange, 10);
        }

    }

    private void index(HttpExchange httpExchange) throws IOException {

        cookie.redirectIfCookieNull(httpExchange);
        String sessionId = cookie.getSessionId(httpExchange);
        SessionDAO sessionDAO = new SessionDAO();
        SessionModel session = sessionDAO.getSession(sessionId);

        if (session != null) {

            String userType = session.getUserType();
            userId = session.getUserId();
            redirectToMentorHome(httpExchange, userType);
        } else {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void redirectToMentorHome(HttpExchange httpExchange,
                                     String userType) throws IOException{
        if(userType.equals("mentor")) {
            ResponseController<User> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, "Home page",
                    "mentor/menu_mentor.twig","mentor/mentor_home.twig");
        } else {
            httpExchange.getResponseHeaders().set("Location", "/" + userType);
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void clearCookie(HttpExchange httpExchange) throws IOException {
        cookie.cleanCookie(httpExchange);

        httpExchange.getResponseHeaders().set("Location", "/login");
        httpExchange.sendResponseHeaders(302,-1);
    }

    private void showArtifacts(HttpExchange httpExchange) throws IOException {
        ArtifactDAO artifactDAO = new ArtifactDAO();
        artifactDAO.loadArtifact();
        LinkedList<ArtifactModel> artifacts = artifactDAO.getObjectList();
        ResponseController<ArtifactModel> responseController = new ResponseController<>();
        responseController.sendResponse(httpExchange, countMail, artifacts,
                "artifactModels", "Show artifacts",
                "mentor/menu_mentor.twig","mentor/mentor_show_artifact.twig");
    }

    private void showQuests(HttpExchange httpExchange) throws IOException {
        QuestDAO questDAO = new QuestDAO();
        questDAO.loadQuest();
        LinkedList<QuestModel> quests = questDAO.getObjectList();
        ResponseController<QuestModel> responseController = new ResponseController<>();
        responseController.sendResponse(httpExchange, countMail, quests,
                "questModels", "Show quests",
                "mentor/menu_mentor.twig", "mentor/mentor_show_quest.twig");
    }

    private void showStudents(HttpExchange httpExchange) throws IOException {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.loadStudents();
        LinkedList<StudentModel> students = studentDAO.getObjectList();
        ResponseController<StudentModel> responseController = new ResponseController<>();
        responseController.sendResponse(httpExchange, countMail, students,
                "studentModels", "Show students",
                "mentor/menu_mentor.twig", "mentor/mentor_show_student.twig");
    }

    private void markQuest(HttpExchange httpExchange) throws IOException {
        QuestDAO questDAO = new QuestDAO();
        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            LinkedList<StudentQuestModel> studentQuestModels = questDAO.loadStudentsWithQuests();
            ResponseController<StudentQuestModel> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, countMail, studentQuestModels,
                    "studentQuestModels", "Mark quest",
                    "mentor/menu_mentor.twig", "mentor/mentor_mark_quest.twig");
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> inputs = parseFormData(formData);
            String title = inputs.get("title").trim();
            String category = inputs.get("category").trim();
            Integer price = Integer.valueOf(inputs.get("price"));
            Integer studentID = Integer.valueOf(inputs.get("student_id"));
            QuestCategoryModel questCategoryModel = new QuestCategoryModel(category);
            StudentQuestModel studentQuestModel = new StudentQuestModel(title, price, questCategoryModel, studentID);

            questDAO.deleteStudentWithQuest(studentQuestModel);

            httpExchange.getResponseHeaders().set("Location", "/mentor/mark_quest");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }


    private void manageArtifact(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            ArtifactDAO artifactDAO = new ArtifactDAO();
            artifactDAO.loadArtifact();
            LinkedList<ArtifactModel> artifacts = artifactDAO.getObjectList();
            ResponseController<ArtifactModel> responseController = new ResponseController<>();
            responseController.sendResponse(httpExchange, countMail, artifacts,
                    "artifactsModels", "Add artifact",
                    "mentor/menu_mentor.twig", "mentor/mentor_add_artifact.twig");

        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> inputs = parseFormData(formData);
            String title = inputs.get("title").trim();

            String category = inputs.get("category").trim();
            String priceStr = inputs.get("price");
            Integer price = Integer.parseInt(priceStr);
            String option = inputs.get("button");
            ArtifactCategoryModel artifactCategoryModel = new ArtifactCategoryModel(category);
            ArtifactModel artifactModel = new ArtifactModel(title, price, artifactCategoryModel);
            ArtifactDAO artifactDAO = new ArtifactDAO();

            if (option.equals("Add")) {
                artifactDAO.insertArtifact("ArtifactsTable", artifactModel, 0);
            } else if (option.equals("Remove")) {
                artifactDAO.deleteArtifact(title);
            } else if (option.equals("Update")) {
                artifactDAO.updateArtifactsTable(artifactModel);
                artifactDAO.updateArtifactStudents(artifactModel);
            }

            httpExchange.getResponseHeaders().set("Location", "/mentor/add_artifact");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }

    private void createTeam(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        LinkedList<StudentModel> students = new LinkedList<>();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            TeamDao teamDao = new TeamDao();
            System.out.println(state + formData);
            if (formData.compareTo("name") > 0 && state == 0) {
                FormDataController<TeamShoppingModel> formDataController = new FormDataController<>();
                teamShoppingModel = formDataController.parseFormData(formData, "nextChooseTeam");
                teamDao.insertNewTeam(teamShoppingModel);
                changeState(1);
            } else if (formData.compareTo("nextPage") > 0 && state == 1) {
                System.out.println(formData + state);
                FormDataController<LinkedList<StudentModel>> formDataController = new FormDataController<>();
                students = formDataController.chooseStudentsFromUser(formData);
                temporaryStudents = temporaryStudentsList(students);
                changeState(2);
            } else if (formData.compareTo("confirm") > 0 && state == 2) {
                updateStudents();
                changeState(0);
            }

            httpExchange.getResponseHeaders().set("Location", "/mentor/create_team");
            httpExchange.sendResponseHeaders(302, -1);
        }
        if (method.equals("GET")) {
            if (state <= 1) {
                students = chooseStudents();
            }
            if (students.size() == 0) {
                students = temporaryStudents;

            }
            ResponseController<StudentModel> responseController = new ResponseController<>();
            responseController.sendResponseCreateTeam(httpExchange, countMail, students, state);
        }
    }
    private void changeState(Integer newState) {
        state = newState;
    }

    private LinkedList<StudentModel> temporaryStudentsList(LinkedList<StudentModel> students) {
        LinkedList<StudentModel> studentsNew = students;
        return students;
    }

    private LinkedList<StudentModel> chooseStudents() {
        MentorDAO mentorDAO = new MentorDAO();
        StudentDAO studentDAO = new StudentDAO();
        MentorModel mentorModel = mentorDAO.getMentor(userId);
        studentDAO.loadStudents();
        LinkedList<StudentModel> students = new LinkedList<>();
        for (StudentModel student : studentDAO.getObjectList()) {
            if (student.getClassName().equals(mentorModel.getClassName())) {
                if (student.getTeamId() == 0) {
                    students.add(student);
                }
            }
        }
        return students;
    }

    private void updateStudents() {
        StudentDAO studentDAO = new StudentDAO();
        TeamDao teamDao = new TeamDao();
        Integer teamId = teamDao.getTeamId(teamShoppingModel);
        for (StudentModel student : temporaryStudents) {
            studentDAO.updateStudent(student.getUserID(), "team_id", String.valueOf(teamId));
        }
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