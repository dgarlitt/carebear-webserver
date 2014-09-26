package com.carebears;

import java.io.PrintWriter;

public abstract class CareBearHttpHandler {
    private String documentRoot;

    public abstract void handle(String request, PrintWriter writer);

    public void registerServlet(CareBearServlet servlet) {}

    public CareBearServlet getServletByPath(String path) {
        return null;
    }

}
