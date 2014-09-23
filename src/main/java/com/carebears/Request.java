package com.carebears;

/**
 * Created by daniel.arlitt on 9/23/14.
 */
public class Request {
    private String method;
    private String path;
    private String version;

    public Request(String rawRequest) {
        String[] rParams = rawRequest.split(" ");
        this.method = rParams[0];
        this.path = rParams[1];
        this.version = rParams[2];
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
}
