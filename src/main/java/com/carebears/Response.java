package com.carebears;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Response {
    private ResponseOutputWriter responseOutputWriter;
    private int statusCode;
    private byte[] body;
    private HashMap<String, String> headers = new HashMap<>();
    private HashMap<String, String> cookies = new HashMap<>();

    public Response(OutputStream outputStream) {
        responseOutputWriter = new ResponseOutputWriter(outputStream);

        statusCode = 404;
        headers.put("Content-Type", "text/html; charset=utf-8");
        headers.put("Server", "CareBearServer/0.0.1");
        headers.put("Accept-Language", "en-US");
    }

    public String getCookie(String cookieName) {
        return cookies.get(cookieName);
    }

    public void setCookie(String cookieName, String cookieValue) {
        cookies.put(cookieName, cookieValue);
    }

    public ResponseOutputWriter getResponseOutputWriter() {
        return responseOutputWriter;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public void setHeader(String header, String mimeType) {
        if (headers.containsKey(header)) {
            headers.replace(header, mimeType);
        } else {
            headers.put(header, mimeType);
        }
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(String body) {
        try {
            this.body = body.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException ex) {
            this.body = body.getBytes();
        }
    }

    public void setBody(String body, String charset) {
        try {
            this.body = body.getBytes(charset);
        }
        catch(UnsupportedEncodingException ex) {
            this.body = body.getBytes();
        }
    }

    public void setBody(byte[] bytes) {
        this.body = new byte[bytes.length];
        System.arraycopy(bytes, 0, this.body, 0, bytes.length);
    }

    public void send() {
        if (!cookies.isEmpty()) {
            Set cookieSet = cookies.entrySet();
            Iterator c = cookieSet.iterator();

            while (c.hasNext()) {
                Map.Entry entry = (Map.Entry) c.next();
                setHeader("Set-Cookie", entry.getKey() + "=" + entry.getValue() + ";");
            }
        }

        StringBuffer outputBuffer = new StringBuffer("HTTP/1.1 " + getStatusCode());
        if (statusCode == 200) {
            outputBuffer.append(" OK");
        }

        try {
            responseOutputWriter.writeln(outputBuffer.toString());

            if (statusCode < 400) {
                outputBuffer = new StringBuffer();
                Set headerSet = headers.entrySet();
                Iterator h = headerSet.iterator();

                while (h.hasNext()) {
                    Map.Entry entry = (Map.Entry) h.next();
                    outputBuffer.append(entry.getKey() + ": " + entry.getValue() + "\n");
                }

                if (outputBuffer.length() > 0) {
                    responseOutputWriter.write(outputBuffer.toString());
                }

                if (body != null && body.length > 0) {
                    responseOutputWriter.write("\n");
                    responseOutputWriter.writeBytes(body);
                }
            }
            responseOutputWriter.flush();
        }
        catch(IOException ex) {
            try {
                responseOutputWriter.write("HTTP/1.1 500\n");
                responseOutputWriter.flush();
            }
            catch(IOException ex2) {
                ex2.printStackTrace();
            }
        }

    }
}
