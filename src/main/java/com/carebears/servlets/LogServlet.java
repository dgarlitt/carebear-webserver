package com.carebears.servlets;

import com.carebears.*;

public class LogServlet extends CareBearServlet {
    public String getPath() {
        return new String("/log");
    }

    public void doGet(Request req, Response resp) {
        Session session = Server.CONFIG.getSession();

        if (!session.hasLogData("GET /log HTTP/1.1")) {
            session.addLogData("GET /log HTTP/1.1");
        }

        resp.setStatusCode(200);
        resp.send();
    }
}
