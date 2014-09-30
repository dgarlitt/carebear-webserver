package com.carebears;


import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;

public class Request {
    private String method;
    private String path;
    private String version;
    private HashMap<String, String> headerMap;
    private HashMap<String, String> parameters;
    private String docRoot;
    private String firstLine;

    public Request(InputStream inputStream) {
        headerMap = new HashMap<>();

        parseRequest(inputStream);
        parseRequestLine(firstLine);
    }

    public Request(InputStream inputStream, String documentRoot) {
        this(inputStream);
        docRoot = documentRoot;
    }

    public String getHeader(String header) {
        return headerMap.get(header);
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

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public String getParam(String param) {
        if (parameters.containsKey(param)) {
            return parameters.get(param);
        }
        return "";
    }

    public void parseRequestLine(String line) {
        String[] rParams = line.split(" ");
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

    public void parseRequest(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder output = new StringBuilder();
        String headerString = null;
        try {
            firstLine = reader.readLine();
            headerString = reader.readLine();

            while (headerString != null && !headerString.isEmpty()) {
                if (headerString.indexOf(":") > -1) {
                    String[] parsedHeader = headerString.split(":");
                    headerMap.put(parsedHeader[0], parsedHeader[1]);
                }
                headerString = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
