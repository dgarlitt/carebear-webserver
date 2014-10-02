package com.carebears;

import java.io.*;
import java.util.HashMap;

public class Request {
    private String method;
    private String path;
    private String version;
    private String body;
    private HashMap<String, String> headerMap;
    private HashMap<String, String> parametersMap;
    private HashMap<String, String> cookieMap;
    private String docRoot;
    private RequestParser parser;
    private String firstLine;

    public Request(InputStream inputStream) {
        headerMap = new HashMap<>();
        cookieMap = new HashMap<>();
        parametersMap = new HashMap<>();
        parser = new RequestParser(this, inputStream);

        parser.parse();
    }
    public Request(InputStream inputStream, String documentRoot) {
        this(inputStream);
        docRoot = documentRoot;
    }

    protected void setMethod(String method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }

    protected void setVersion(String version) {
        this.version = version;
    }

    public boolean hasHeader(String header) {
        return headerMap.containsKey(header);
    }

    public String getHeader(String header) {
        return headerMap.get(header);
    }

    public void setHeader(String key, String value) {
        key = key.trim();
        value = value.trim();
        if (headerMap.containsKey(key)) {
            headerMap.replace(key, value);
        } else {
            headerMap.put(key, value);
        }
    }

    public String getFirstRequestLine() {
        return firstLine;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }

    public HashMap<String, String> getParametersMap() {
        return parametersMap;
    }

    public String getParam(String param) {
        if (parametersMap.containsKey(param)) {
            return parametersMap.get(param);
        }
        return "";
    }

    public void setParam(String key, String value) {
        key = key.trim();
        value = value.trim();
        if (parametersMap.containsKey(key)) {
            parametersMap.replace(key, value);
        } else {
            parametersMap.put(key, value);
        }
    }


    public void setDocRoot(String documentRoot) {
        docRoot = documentRoot;
    }

    public String getDocRoot() {
        return docRoot;
    }

    public String getCookie(String cookie) {
        if (cookieMap.isEmpty()) {
            String cookies = getHeader("Cookie");
            String cookieArray[] = new String[]{};

            if (cookies != null) {
                cookieArray = cookies.split(";");
            }

            for (int i = 0; i < cookieArray.length; i++) {
                String[] cookieValue = cookieArray[i].split("=");
                cookieMap.put(cookieValue[0], cookieValue[1]);
            }
        }

        return cookieMap.get(cookie);
    }

    protected void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
