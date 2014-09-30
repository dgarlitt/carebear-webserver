package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

public class FormServlet extends CareBearServlet {
    private String path;

    public FormServlet() {
        path = "/form";
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void doGet(Request req, Response res) {
        res.setStatusCode(200);

        String cookie = req.getCookie("data");
        if (req.getCookie("data") != null) {
            res.setBody("data = " + cookie);
        }

        res.send();
    }

    @Override
    public void doPost(Request req, Response res) {
        res.setStatusCode(200);

        res.send();
    }

    @Override
    public void doPut(Request req, Response res) {
        res.setStatusCode(200);
        res.send();
    }

}
