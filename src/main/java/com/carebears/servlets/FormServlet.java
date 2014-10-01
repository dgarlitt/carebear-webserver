package com.carebears.servlets;

import com.carebears.*;

public class FormServlet extends CareBearServlet {

    private Session session;

    public FormServlet() {
        session = Server.CONFIG.getSession();
    }

    @Override
    public String getPath() {
        return "/form";
    }

    @Override
    public void doGet(Request req, Response res) {
        res.setStatusCode(200);

        if (session.getData() != null) {
            res.setBody("data = " + session.getData());
        }

        res.send();
    }

    @Override
    public void doPost(Request req, Response res) {
        res.setStatusCode(200);
        session.setData(req.getParam("data"));
        res.send();
    }

    @Override
    public void doPut(Request req, Response res) {
        res.setStatusCode(200);
        session.setData(req.getParam("data"));
        res.send();
    }

    @Override
    public void doDelete(Request req, Response res) {
        res.setStatusCode(200);
        session.setData(null);
        res.send();
    }

}
