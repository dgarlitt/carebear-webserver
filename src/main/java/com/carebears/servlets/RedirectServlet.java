package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

public class RedirectServlet extends CareBearServlet {

    @Override
    public void doGet(Request req, Response res) {
        res.setStatusCode(302);
        res.setHeader("Location", "http://localhost:5000/");
        res.send();
    }

    @Override
    public String getPath() {
        return "/redirect";
    }

}
