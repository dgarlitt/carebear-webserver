package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

public class LogsServlet extends CareBearServlet {

    public String getPath() {
        return "/logs";
    }

    public void doGet(Request req, Response res) {
        res.setStatusCode(401);
        res.setBody("Authentication required");
    }

}
