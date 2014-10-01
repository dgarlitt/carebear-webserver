package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

public class RootServlet extends CareBearServlet {

    @Override
    public void doGet(Request req, Response res) {
        res.setStatusCode(200);
        listDirectory(req, res);
    }

    @Override
    public String getPath() {
        return "/";
    }

}
