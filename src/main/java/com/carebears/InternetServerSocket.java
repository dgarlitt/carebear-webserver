package com.carebears;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class InternetServerSocket extends Thread implements CareBearServerSocket {
    private int port;
    private Socket socket;
    private ServerSocket serverSocket = null;
    private CareBearHttpHandler handler = null;
    private Boolean running = true;
    public InternetServerSocket() {}

    @Override
    public void start(CareBearHttpHandler handler) {
        serverSocket = null;
        this.handler = handler;

        try {
            serverSocket = new ServerSocket(Server.CONFIG.getPort());
            this.start();

        } catch (IOException e) {
            stopSocket();
        }
    }

    public void stopSocket() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            }
            catch(IOException ex) {
                System.out.println("InternetServerSocket: Exception closing server socket");
            }
        }
    }

    public void run() {
        while(isRunning()) {
            try {
                socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String input = in.readLine();

                handler.handle(input, out);
                socket.close();
                Thread.sleep(100);
            }
            catch(IOException ex) {
                ex.printStackTrace();
                break;
            }
            catch(InterruptedException ex) {
                stopSocket();
                break;
            }
            catch(NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public synchronized boolean isRunning() {
        return this.running;
    }

}
