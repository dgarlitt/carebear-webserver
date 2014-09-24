package com.carebears;

import com.carebears.Request;
import com.carebears.Response;

import java.io.PrintWriter;

public abstract class CareBearServlet {

    public abstract String getPath();

    public void doPost(Request req, Response res) {
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.0 404");
        writer.flush();
    }

    public void doGet(Request req, Response res) {
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.0 404");
        writer.flush();
    }

    public void doPut(Request req, Response res) {
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.0 404");
        writer.flush();
    }

    public void doDelete(Request req, Response res) {
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.0 404");
        writer.flush();
    }

}
