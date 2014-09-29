package com.carebears;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerBear implements Runnable {

    private Socket workerSocket = null;
    private String serverText = null;

    public WorkerBear(Socket workerSocket, String serverText) {
        this.workerSocket = workerSocket;
        this.serverText = serverText;

    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        String input;
        try {
            out = new PrintWriter(workerSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(workerSocket.getInputStream()));

            Server.CONFIG.getHandler().handle(in, out);
            workerSocket.close();

        } catch(IOException e) {
            throw(new RuntimeException("Client socket failed to load"));

        }
        finally {
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
