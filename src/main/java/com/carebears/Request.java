package com.carebears;


import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;

public class Request {
    private String method;
    private String path;
    private String version;
    private HashMap<String, String> headerMap;
    private HashMap<String, String> parametersMap;
    private HashMap<String, String> cookieMap;
    private String docRoot;
    private String firstLine;

    public Request(InputStream inputStream) {
        headerMap = new HashMap<>();
        cookieMap = new HashMap<>();

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

    public HashMap<String, String> getParametersMap() {
        return parametersMap;
    }

    public String getParam(String param) {
        if (parametersMap.containsKey(param)) {
            return parametersMap.get(param);
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
        StringBuilder sb = new StringBuilder();

        try {
            firstLine = reader.readLine();
            parseRequestLine(firstLine);

            String line = reader.readLine();

            while(line != null && !line.isEmpty()) {
                sb.append(line + "\n");
                line = reader.readLine();
            }

            String rawRequest = sb.toString();
            String[] rawRequestArray = rawRequest.split("\n\n");

            parseHeader(rawRequestArray[0]);

            if (rawRequestArray.length > 1) {
                parseBody(rawRequestArray[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parseHeader(String header) {
        if (!header.isEmpty()) {
            String[] parsedHeader = header.split("\n");
            for (int i = 0; i < parsedHeader.length; i++) {
                String[] parsedValues = parsedHeader[i].split(":");
                if (parsedValues.length > 1) {
                    headerMap.put(parsedValues[0], parsedValues[1]);
                }
            }
        }
    }

    private void parseBody(String body) {
        if (!body.isEmpty()) {
            String[] parsedBody = body.split("&");
            for (int i = 0; i < parsedBody.length; i++) {
                String[] parsedValues = parsedBody[i].split("=");
                if (parametersMap.containsKey(parsedValues[0])) {
                    parametersMap.replace(parsedValues[0], parsedValues[1]);
                } else {
                    parametersMap.put(parsedValues[0], parsedValues[1]);
                }

            }
        }
    }

    private void parseParameters(String params) throws UnsupportedEncodingException {
        parametersMap = new HashMap<>();
        String[] paramArray = params.split("&");

        for(String pair: paramArray) {
            int paramIndex = pair.indexOf("=");
            parametersMap.put(URLDecoder.decode(pair.substring(0, paramIndex), "UTF-8"), URLDecoder.decode(pair.substring(paramIndex + 1), "UTF-8"));

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
}
