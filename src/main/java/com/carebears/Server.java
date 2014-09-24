package com.carebears;

import com.carebears.servlets.FormServlet;

import java.io.IOException;
import java.net.Socket;

public class Server {

    private CareBearSocket serverSocket;
    private Socket socket;
    private InternetHttpHandler handler;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private int port;

    public static void main(String[] args) throws IOException {
        Server server = new Server(new InternetSocket());
        server.start();
    }

    public Server(CareBearSocket sock) {
        this.serverSocket = sock;
        this.handler = new InternetHttpHandler();

        handler.registerServlet(new FormServlet());
    }

    public void start() throws IOException {
        serverSocket.start(handler);
        //socket = serverSocket.accept();
//        processConnection();
    }

    public InternetHttpHandler getHTTPHandler() {
        return handler;
    }

//    public void processConnection() {
//        PrintWriter writer;
//        BufferedReader reader;
//
//        try {
//            writer = new PrintWriter(socket.getOutputStream());
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            String request = reader.readLine();
////            String[] rParams = request.split(" ");
////            String method = rParams[0];
////            String path = rParams[1];
////            String version = rParams[2];
////
////            System.out.println(path);
////
////            if (method.equals("GET") && path.equals("/")) {
////                writer.println("HTTP/1.0 200 OK");
////            } else if (method.equals("POST") && path.equals("/form")) {
////                writer.println("HTTP/1.0 200 OK");
////            } else {
////                writer.println("HTTP/1.0 404");
////            }
////            writer.flush();
//            socket.close();
//        } catch (IOException e) {
//            // handle exception here
//        }
//    }

    public Socket getSocket() {
        return socket;
    }

}
