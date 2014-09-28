package com.carebears;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class Request {
    private String method;
    private String path;
    private String version;
    private HashMap<String, String> parameters;
    private String docRoot;

    public Request(String rawRequest) {
        String[] rParams = rawRequest.split(" ");
        String[] rUrlParameters = rParams[1].split("\\?");
        this.method = rParams[0];
        this.path = rUrlParameters[0];
        if (rUrlParameters.length == 2) {
            try {
                parseParameters(rUrlParameters[1]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
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

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public String getParam(String param) {
        if (parameters.containsKey(param)) {
            return parameters.get(param);
        }
        return "";
    }

    private void parseParameters(String params) throws UnsupportedEncodingException {
        parameters = new HashMap<>();
        String[] paramArray = params.split("&");

        for(String pair: paramArray) {
            int paramIndex = pair.indexOf("=");
            parameters.put(URLDecoder.decode(pair.substring(0, paramIndex), "UTF-8"), URLDecoder.decode(pair.substring(paramIndex + 1), "UTF-8"));

        }
    }


    public void setDocRoot(String documentRoot) {
        docRoot = documentRoot;
    }

    public String getDocRoot() {
        return docRoot;
    }
}
