package com.carebears;

public class CareBearConfig {

    private String documentRoot = "/";
    private int port = 5000;
    private CareBearHttpHandler handler;
    private CareBearServerSocket serverSocket;

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

}
