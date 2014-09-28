package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ParametersServlet extends CareBearServlet {
    private String path;

    public ParametersServlet() {
        path = "/parameters";
    }

    @Override
    public void doGet(Request req, Response res) {

        Map<String, String> paramMap;
        try {
            paramMap = req.getParameters();
        } catch(Exception e) {
            paramMap = new HashMap<>();
        }
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.1 200");

        writer.println("\n");
        for (Map.Entry<String, String>param: paramMap.entrySet()) {
            writer.println(param.getKey() + " = " + param.getValue());

        }

        writer.flush();
    }

    @Override
    public String getPath() {
        return path;
    }

}
