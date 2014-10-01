package com.carebears;

public class ConfigBear {

    private String documentRoot = "/";
    private int port = 5000;
    private CareBearHttpHandler handler;
    private CareBearServerSocket serverSocket;
    private Session session;

    public String getDocumentRoot() {
        return documentRoot;
    }

    public void setDocumentRoot(String documentRoot) {
        this.documentRoot = documentRoot;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public CareBearHttpHandler getHandler() {
        return handler;
    }

    public void setHandler(CareBearHttpHandler handler) {
        this.handler = handler;
    }

    public void setServerSocket(CareBearServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public CareBearServerSocket getServerSocket() {
        return serverSocket;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
