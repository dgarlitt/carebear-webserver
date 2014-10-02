package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;
import com.carebears.Server;

public class LogsServlet extends CareBearServlet {

    public String getPath() {
        return "/logs";
    }

    public void doGet(Request req, Response res) {
        if (req.hasHeader("Authorize")) {
            Server.CONFIG.getSession().setAuthorized();
        }

        boolean authorized = Server.CONFIG.getSession().isAuthorized();

        if (!authorized) {
            res.setStatusCode(401);
            res.setBody("Authentication required");
        }
        else {
            res.setStatusCode(200);
            res.setBody(Server.CONFIG.getSession().getLogData());
        }
        res.send();
    }

}
