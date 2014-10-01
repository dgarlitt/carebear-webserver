package com.carebears.servlets;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

import java.util.HashMap;
import java.util.Map;

public class ParametersServlet extends CareBearServlet {

    @Override
    public void doGet(Request req, Response res) {
        Map<String, String> paramMap;
        String body = "";

        try {
            paramMap = req.getParametersMap();
        } catch(Exception e) {
            paramMap = new HashMap<>();
        }
        res.setStatusCode(200);

        for (Map.Entry<String, String>param: paramMap.entrySet()) {
            body += param.getKey() + " = " + param.getValue() + "\n";
        }
        res.setBody(body);
        res.send();
    }

    @Override
    public String getPath() {
        return "/parameters";
    }

}
