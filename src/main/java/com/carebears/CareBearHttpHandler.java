package com.carebears;

import com.carebears.servlets.CareBearServlet;

import java.util.ArrayList;

public class CareBearHttpHandler {
    private String documentRoot;
    private ArrayList<CareBearServlet> servlets = new ArrayList<CareBearServlet>();

    public String handle(String request) {
        String[] rParams = request.split(" ");
        String method = rParams[0];
        String path = rParams[1];
        String version = rParams[2];


        if (request.equals("GET / HTTP/1.0")) {
            return("HTTP/1.0 200");
        }
        else if (request.equals("GET /foobar HTTP/1.0")) {
            return("HTTP/1.0 404");
        }
        else if (request.equals("POST / HTTP/1.0")) {
            return("HTTP/1.0 404");
        }

        return null;
    }

    public void setDocumentRoot(String documentRoot) {
        this.documentRoot = documentRoot;
    }

    public String getDocumentRoot() {
        return documentRoot;
    }

}
