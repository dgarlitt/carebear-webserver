package com.carebears;


public class Request {
    private String method;
    private String path;
    private String version;
    private String docRoot;

    public Request(String rawRequest) {
        String[] rParams = rawRequest.split(" ");
        this.method = rParams[0];
        this.path = rParams[1].split("\\?")[0];
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

    public void setDocRoot(String documentRoot) {
        docRoot = documentRoot;
    }

    public String getDocRoot() {
        return docRoot;
    }
}
