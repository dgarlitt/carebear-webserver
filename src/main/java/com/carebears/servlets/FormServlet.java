package com.carebears.servlets;

import com.carebears.CareBearServlet;

public class FormServlet extends CareBearServlet {
    private String path;

    public FormServlet() {
        path = "/form";
    }

    @Override
    public String getPath() {
        return path;
    }

}
