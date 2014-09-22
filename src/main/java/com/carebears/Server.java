package com.carebears;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;

    public static void main(String[] args) throws IOException {
        Server server = new Server(new ServerSocket(5000));
        server.start();
    }

    public Server(ServerSocket sock) {
        this.serverSocket = sock;
    }

    public void start() throws IOException {
        socket = serverSocket.accept();
        processConnection();
    }

    public void processConnection() {
        PrintWriter writer;
        BufferedReader reader;

        try {
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println("HTTP/1.0 404");
            writer.flush();
            socket.close();
        } catch (IOException e) {
            // handle exception here
        }
    }

    public Socket getSocket() {
        return socket;
    }

}
