package com.carebears;

import java.io.*;
import java.net.Socket;

public class WorkerBear implements Runnable {

    private Socket workerSocket = null;
    private String serverText = null;

    public WorkerBear(Socket workerSocket, String serverText) {
        this.workerSocket = workerSocket;
        this.serverText = serverText;

    }

    public void run() {
        OutputStream out = null;
        InputStream in = null;
        String input;
        try {
            out = workerSocket.getOutputStream();
            in = workerSocket.getInputStream();

            Server.CONFIG.getHandler().handle(in, out);
            workerSocket.close();

        } catch(IOException e) {
            throw(new RuntimeException("Client socket failed to load"));

        }
        finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

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
