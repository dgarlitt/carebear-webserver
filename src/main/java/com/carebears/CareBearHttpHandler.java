package com.carebears;

import java.io.BufferedReader;
import java.io.PrintWriter;

public abstract class CareBearHttpHandler {
    private String documentRoot;

    public abstract void handle(BufferedReader request, PrintWriter writer);

    public void registerServlet(CareBearServlet servlet) {}

    public CareBearServlet getServletByPath(String path) {
        return null;
    }

}
