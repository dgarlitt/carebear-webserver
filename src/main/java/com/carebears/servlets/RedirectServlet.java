package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

import java.io.PrintWriter;

public class RedirectServlet extends CareBearServlet {
    private String path;

    public RedirectServlet() {
        path = "/redirect";
    }

    @Override
    public void doGet(Request req, Response res) {
        res.setStatusCode(302);
        res.setHeader("Location", "http://localhost:5000/");
        res.send();
    }

    @Override
    public String getPath() {
        return path;
    }

}
