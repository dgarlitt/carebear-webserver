package com.carebears;

import java.io.PrintWriter;

public abstract class CareBearHttpHandler {
    private String documentRoot;

    public abstract void handle(String request, PrintWriter writer);

    public void setDocumentRoot(String documentRoot) {
        this.documentRoot = documentRoot;
    }

    public String getDocumentRoot() {
        return documentRoot;
    }

}
