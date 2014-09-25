package com.carebears;

import com.carebears.servlets.FormServlet;
import com.carebears.servlets.RedirectServlet;
import com.carebears.servlets.RootServlet;

import java.io.IOException;

public class Server {
    private int port = 80;
    private CareBearServerSocket serverSocket;
    private InternetHttpHandler handler;

    public Server(CareBearServerSocket sock) {
        this.serverSocket = sock;
        this.handler = new InternetHttpHandler();

        handler.registerServlet(new FormServlet());
        handler.registerServlet(new RootServlet());
        handler.registerServlet(new RedirectServlet());
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void initialize() throws IOException {
        serverSocket.start(handler, port);
    }

    public void stopServer() {
        serverSocket.stopSocket();
    }

    public InternetHttpHandler getHTTPHandler() {
        return handler;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(new InternetServerSocket());
        server.setPort(5000);
        server.initialize();
    }

}
