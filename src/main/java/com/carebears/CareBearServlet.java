package com.carebears;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CareBearServlet {
    private Map<String, String> parametersMap;

    public abstract String getPath();

    public Map<String, String> getParameters(Request req) throws UnsupportedEncodingException {
        parametersMap = new LinkedHashMap<>();
        String[] paramArray = req.getUrlParameters().split("&");

        for(String pair: paramArray) {
            int paramIndex = pair.indexOf("=");
            parametersMap.put(URLDecoder.decode(pair.substring(0, paramIndex), "UTF-8"), URLDecoder.decode(pair.substring(paramIndex + 1), "UTF-8"));

        }

        return parametersMap;
    }

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
