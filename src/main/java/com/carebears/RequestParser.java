package com.carebears;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class RequestParser {

    private Request req;
    private InputStream inputStream;

    public RequestParser(Request req, InputStream inputStream) {
        this.req = req;
        this.inputStream = inputStream;
    }

    public void parse() {
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

    public void parseRequestLine(String line) {
        String[] rParams = line.split(" ");
        if (rParams.length > 1) {
            String[] rUrlParameters = rParams[1].split("\\?");
            req.setMethod(rParams[0]);
            req.setPath(rUrlParameters[0]);
            if (rUrlParameters.length == 2) {
                try {
                    parseParameters(rUrlParameters[1]);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            req.setVersion(rParams[2]);
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
                req.setHeader(parsedValues[0], parsedValues[1]);
            }
        }
    }

    private void parseBody(BufferedInputStream inputStream) throws IOException {
        String s_contentLen = req.getHeader("Content-Length");
        if (s_contentLen != null) {
            int contentLength = Integer.parseInt(s_contentLen);
            byte[] byteArr = new byte[contentLength];
            inputStream.read(byteArr, 0, contentLength);
            req.setBody(new String(byteArr));

            parseParameters(req.getBody());
        }
    }

    private void parseParameters(String params) throws UnsupportedEncodingException {
        String[] paramArray = params.split("&");

        for(String pair: paramArray) {
            int paramIndex = pair.indexOf("=");
            if (paramIndex > -1) {
                req.setParam(URLDecoder.decode(pair.substring(0, paramIndex), "UTF-8"), URLDecoder.decode(pair.substring(paramIndex + 1), "UTF-8"));
            }
        }
    }

}
