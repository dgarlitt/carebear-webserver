package com.carebears.servlets;

import com.carebears.*;

public class TheseServlet extends CareBearServlet {
    public String getPath() {
        return new String("/these");
    }

    public void doPut(Request req, Response resp) {
        Session session = Server.CONFIG.getSession();

        if (!session.hasLogData("PUT /these HTTP/1.1")) {
            session.addLogData("PUT /these HTTP/1.1");
        }

        resp.setStatusCode(200);
        resp.send();
    }
}
