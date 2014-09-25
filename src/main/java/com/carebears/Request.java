package com.carebears;


public class Request {
    private String method;
    private String path;
    private String version;
    private String parameters;
    private String docRoot;

    public Request(String rawRequest) {
        String[] rParams = rawRequest.split(" ");
        String[] rUrlParameters = rParams[1].split("\\?");
        this.method = rParams[0];
        this.path = rUrlParameters[0];
        if (rUrlParameters.length == 2) {
            parameters = rUrlParameters[1];
        }
        this.version = rParams[2];
    }

    public Request(String rawRequest, String documentRoot) {
        this(rawRequest);
        docRoot = documentRoot;
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

    public String getUrlParameters() {return parameters; }

    public void setDocRoot(String documentRoot) {
        docRoot = documentRoot;
    }
}
