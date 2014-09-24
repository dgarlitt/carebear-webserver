package com.carebears;

import java.io.PrintWriter;

public class Response {
    private PrintWriter writer;

    public Response(PrintWriter writer) {
        this.writer = writer;
    }

    public PrintWriter getWriter() {
        return writer;
    }
}
