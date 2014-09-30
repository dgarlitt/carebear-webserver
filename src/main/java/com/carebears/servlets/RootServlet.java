package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

import java.io.IOException;

public class RootServlet extends CareBearServlet {
    private String path;

    public RootServlet() {
        path = "/";
    }

    @Override
    public void doGet(Request req, Response res) {
        res.setStatusCode(200);
        listDirectory(req, res);
    }

    @Override
    public String getPath() {
        return path;
    }

}
