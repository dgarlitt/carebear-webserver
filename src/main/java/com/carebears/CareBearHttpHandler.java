package com.carebears;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public abstract class CareBearHttpHandler {
    private String documentRoot;

    public abstract void handle(InputStream inputStream, OutputStream outputStream);

    public void registerServlet(CareBearServlet servlet) {}

    public CareBearServlet getServletByPath(String path) {
        return null;
    }

}
