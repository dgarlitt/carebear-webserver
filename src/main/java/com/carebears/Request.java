package com.carebears;


import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        parametersMap = new HashMap<>();


        parseRequest(inputStream);
    }

    public Request(InputStream inputStream, String documentRoot) {
        this(inputStream);
        docRoot = documentRoot;
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
        String formattedInput;
        String[] stringArray;
        BufferedInputStream reader = new BufferedInputStream(inputStream);

        try {
            formattedInput = getHeaderAsString(reader);
            stringArray = formattedInput.split("\n");

            for (int i = 0; i < stringArray.length; i++) {
                if (i == 0) {
                    parseRequestLine(stringArray[0]);
                } else {
                    parseHeader(stringArray[i]);
                }
            }

            parseBody(reader);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getHeaderAsString(BufferedInputStream is) throws IOException {
        List<Byte> byteL = new ArrayList<>();
        byte[] byteArray;
        boolean newLine = false;

        int b;
        while ((b = is.read()) != -1) {
            if (b != 13) {
                if (b == 10) {
                    if (newLine) {
                        break;
                    }
                    newLine = true;
                } else {
                    newLine = false;
                }
                byteL.add((byte) b);
            }
        }

        byteArray = new byte[byteL.size()];

        for (int i = 0; i < byteL.size(); i++) {
            byteArray[i] = byteL.get(i);
        }

        return (new String(byteArray, "UTF-8"));
    }

    private void parseHeader(String header) {
        if (!header.isEmpty()) {
            String[] parsedValues = header.split(":");
            if (parsedValues.length > 1) {
                setHeader(parsedValues[0], parsedValues[1]);
            }
        }
    }

    private void parseBody(String body) {
        if (!body.isEmpty()) {
            String[] parsedBody = body.split("&");
            for (int i = 0; i < parsedBody.length; i++) {
                String[] parsedValues = parsedBody[i].split("=");
                setParam(parsedValues[0], parsedValues[1]);
            }
        }
    }

    private void parseBody(BufferedInputStream inputStream) throws IOException {
        String s_contentLen = getHeader("Content-Length");
        if (s_contentLen != null) {
            int contentLength = Integer.parseInt(s_contentLen);
            byte[] byteArr = new byte[contentLength];
            inputStream.read(byteArr, 0, contentLength);
            parseBody(new String(byteArr));
        }
    }

    private void parseParameters(String params) throws UnsupportedEncodingException {
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
