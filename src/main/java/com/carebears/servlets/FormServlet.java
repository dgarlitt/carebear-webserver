package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

import java.io.PrintWriter;

public class FormServlet extends CareBearServlet {
    private String path;

    public FormServlet() {
        path = "/form";
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void doPost(Request req, Response res) {
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.0 200 OK");
        writer.flush();
    }

    @Override
    public void doPut(Request req, Response res) {
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.0 200 OK");
        writer.flush();
    }

}
