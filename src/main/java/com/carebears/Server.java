package com.carebears;

import com.carebears.servlets.*;

import java.io.IOException;

public class Server {
    private int port = 80;
    private CareBearServerSocket serverSocket;
    private InternetHttpHandler handler;
    private String documentRoot = "/";

    public Server(CareBearServerSocket sock) {
        this.serverSocket = sock;
        this.handler = new InternetHttpHandler();
        handler.setDocumentRoot(documentRoot);

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

    public void setDocumentRoot(String documentRoot) {
        this.documentRoot = documentRoot;
    }

    public String getDocumentRoot() {
        return documentRoot;
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
        String path = "/";
        String portString = "5000";
        int numArgs = args.length;

        if (numArgs == 2 || numArgs == 4) {
            int xctr = 0;

            while(xctr < numArgs) {
                if (args[xctr].equals("-p")) {
                    portString = args[xctr + 1];
                    xctr += 2;
                } else if (args[xctr].equals("-d")) {
                    path = args[xctr + 1];
                    xctr += 2;
                } else {
                    System.out.println("Invalid argument: " + args[xctr]);
                }
            }
        }

        Server server = new Server(new InternetServerSocket());
        server.setPort(Integer.parseInt(portString));
        server.setDocumentRoot(path);
        server.initialize();
    }

}
