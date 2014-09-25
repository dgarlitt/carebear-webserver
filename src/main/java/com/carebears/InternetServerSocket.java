package com.carebears;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;

public class InternetServerSocket extends Thread implements CareBearServerSocket {
    private Socket socket;
    private ServerSocket serverSocket = null;
    private CareBearHttpHandler handler = null;

    public InternetServerSocket() {
    }

    @Override
    public void start(CareBearHttpHandler handler, int port) {
        serverSocket = null;
        this.handler = handler;

        try {
            serverSocket = new ServerSocket(port);
            this.start();
        }
        catch (Exception e)
        {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
    }

    public void run() {
        while(true) {
            try {
                socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String input = in.readLine();

                handler.handle(input, out);
                socket.close();
            }
            catch(IOException ex) {

            }
        }
    }

}
