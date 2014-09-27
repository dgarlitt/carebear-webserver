package com.carebears;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerBear implements Runnable {

    private Socket clientSocket = null;
    private String serverText = null;

    public WorkerBear(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        String input;
        try {
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            input = in.readLine();
            Server.CONFIG.getHandler().handle(input, out);
            clientSocket.close();

        } catch(IOException e) {
            throw(new RuntimeException("Client socket failed to load"));

        } finally {
            if (out != null)
                out.close();

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
