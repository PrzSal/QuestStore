package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.SessionDAO;
import com.codecool.dream_is_green.dao.UserDAO;
import com.codecool.dream_is_green.model.SessionModel;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class LoginController implements HttpHandler {

    private static CookieManager cookie = new CookieManager();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        cookie.handle(httpExchange);
        cookie.redirectIfCookieNull(httpExchange);

        String sessionId = cookie.getSessionId(httpExchange);
        SessionDAO sessionDAO = new SessionDAO();
        SessionModel session = sessionDAO.getSession(sessionId);

        if(session != null) {
            String userType = session.getUserType();
            httpExchange.getResponseHeaders().set("Location", "/" + userType);
            httpExchange.sendResponseHeaders(302,-1);

        } else {

            String method = httpExchange.getRequestMethod();

            if (method.equals("GET")) {
                String response = this.getLoginTemplate();
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }

            if (method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String formData = br.readLine();

                Map<String, String> inputs = parseFormData(formData);
                String username = inputs.get("username");
                String password = inputs.get("password");

                this.loginHandle(httpExchange, username, password);
            }
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

    private String getLoginTemplate() {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login/login_Page.twig");
        JtwigModel model = JtwigModel.newModel();
        String response = template.render(model);

        return response;
    }

    public void loginHandle(HttpExchange httpExchange, String userName, String password) throws IOException {

        UserDAO userDAO = new UserDAO();
        String currentPassword = userDAO.getUserPassword(userName);
        String userType = userDAO.getUserType(userName);

        if (password.equals(currentPassword)) {
            SessionModel newSession = new SessionModel(cookie.getSessionId(httpExchange), userName, userType);
            SessionDAO sessionDAO = new SessionDAO();
            sessionDAO.insertSession(newSession);

            httpExchange.getResponseHeaders().set("Location", "/" + userType);
            httpExchange.sendResponseHeaders(302,-1);

        } else {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(302,-1);
        }
    }
}
