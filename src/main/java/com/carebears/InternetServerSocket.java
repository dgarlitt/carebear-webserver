package com.carebears;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;

public class InternetServerSocket extends Thread implements CareBearServerSocket {
    private ServerSocket serverSocket = null;
    private Boolean running = true;

    public InternetServerSocket() {}

    @Override
    public void start() {
        serverSocket = null;

        try {
            serverSocket = new ServerSocket(Server.CONFIG.getPort());
            super.start();

        } catch (IOException e) {
            stopSocket();
        }
    }

    public void stopSocket() {
        try {
            serverSocket.close();
        }
        catch(Exception e) {
            System.out.println("InternetServerSocket: Exception closing server socket");
            e.printStackTrace();
        }
    }

    public void run() {
        while(isRunning()) {
            try {
                createWorkerThread(serverSocket.accept());
            }
            catch(SocketException e) {
                System.out.println(e.getMessage());
                break;
            }
            catch(IOException ex) {
                ex.printStackTrace();
                break;
            }
            catch(NullPointerException e) {
                e.printStackTrace();
            }


        }
    }

    public void createWorkerThread(Socket socket) {
        new Thread(new WorkerBear(socket, "CareBearServer")).start();
    }

    public synchronized boolean isRunning() {
        return this.running;
    }

}
