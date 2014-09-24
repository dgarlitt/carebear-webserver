package com.carebears;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class InternetSocket implements CareBearSocket {
     private java.net.Socket socket;

    public InternetSocket() {
    }

    @Override
    public void start(CareBearHttpHandler handler) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
            socket = serverSocket.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input = in.readLine();

            handler.handle(input, out);

//            out.println(input);
//            out.flush();
            socket.close();
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

}
