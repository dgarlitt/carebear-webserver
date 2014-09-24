package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

import java.io.PrintWriter;

public class RootServlet extends CareBearServlet {
    private String path;

    public RootServlet() {
        path = "/";
    }

    @Override
    public void doGet(Request req, Response res) {
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.0 200 OK");
        writer.flush();
    }

    @Override
    public String getPath() {
        return path;
    }

}
