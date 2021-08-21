package com.jacknie.sample;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectContextPathServlet extends HttpServlet {

    private final String contextPath;

    public RedirectContextPathServlet(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(contextPath);
    }
}
