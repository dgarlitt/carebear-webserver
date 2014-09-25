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
    public void doPost(Request req, Response res) {
        res.setStatusCode("200 OK");
        res.send();
    }

    @Override
    public void doPut(Request req, Response res) {
        res.setStatusCode("200 OK");
        res.send();
    }

}
