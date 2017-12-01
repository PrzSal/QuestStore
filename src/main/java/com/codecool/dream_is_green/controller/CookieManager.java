package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.SessionDAO;
import com.codecool.dream_is_green.model.SessionModel;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.UUID;

public class CookieManager implements HttpHandler {

    private HttpCookie cookie = null;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            cookie = HttpCookie.parse(cookieStr).get(0);

        } else {
            UUID sessionId = UUID.randomUUID();
            cookie = new HttpCookie("SessionId", String.valueOf(sessionId));
            httpExchange.getResponseHeaders().add("Set-cookie", "SessionCookie=" + cookie.getValue() + "; Max-Age=300;");
        }
    }

    public String getSessionId(HttpExchange httpExchange) {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        cookie = HttpCookie.parse(cookieStr).get(0);
        String sessionId = cookie.getValue();

        return sessionId;
    }

    public void cleanCookie(HttpExchange httpExchange) {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        cookie = HttpCookie.parse(cookieStr).get(0);
        SessionDAO sessionDAO = new SessionDAO();
        sessionDAO.deleteSession(cookie.getValue());
        httpExchange.getResponseHeaders().add("Set-cookie", "SessionCookie=" + cookie.getValue() + "; Max-Age=0; Path=/");
    }

    public void refreshCookie(HttpExchange httpExchange) {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        cookie = HttpCookie.parse(cookieStr).get(0);
        httpExchange.getResponseHeaders().add("Set-cookie", "SessionCookie=" + cookie.getValue() + "; Max-Age=300; Path=/");
    }

    public void redirectIfCookieNull(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        if(cookieStr == null) {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(302, -1);
        }
    }

    public void resetSession(HttpExchange httpExchange) throws IOException {
        this.cleanCookie(httpExchange);

        httpExchange.getResponseHeaders().set("Location", "/login");
        httpExchange.sendResponseHeaders(302, -1);
    }

}