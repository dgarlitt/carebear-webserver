package com.carebears.servlets;

import com.carebears.*;

public class RequestsServlet extends CareBearServlet {
    public String getPath() {
        return new String("/requests");
    }

    public void doHead(Request req, Response resp) {
        Session session = Server.CONFIG.getSession();

        if (!session.hasLogData("HEAD /requests HTTP/1.1")) {
            session.addLogData("HEAD /requests HTTP/1.1");
        }

        resp.setStatusCode(200);
        resp.send();
    }
}
