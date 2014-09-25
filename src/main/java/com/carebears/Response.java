package com.carebears;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Response {

    private PrintWriter writer;
    private int statusCode;
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body = "";

    public Response(PrintWriter writer) {
        this.writer = writer;

        statusCode = 404;

        headers.put("Content-Type", "text/html; charset=utf-8");
        headers.put("Server", "CareBearServer/0.0.1");
        headers.put("Accept-Language", "en-US");
    }

    public PrintWriter getWriter() {
        return writer;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void send() {
        Set headerSet = headers.entrySet();
        Iterator h = headerSet.iterator();

        writer.print("HTTP/1.0 " + getStatusCode());
        if (statusCode == 200) {
            writer.print(" OK");
        }
        writer.print("\n");

        if (statusCode < 400) {
            while (h.hasNext()) {
                Map.Entry entry = (Map.Entry) h.next();
                writer.println(entry.getKey() + ": " + entry.getValue());
            }

            if (!getBody().isEmpty()) {
                writer.println("");
                writer.print(getBody());
            }
        }

        writer.flush();
    }
}
