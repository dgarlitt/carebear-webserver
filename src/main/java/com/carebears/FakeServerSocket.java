package com.carebears;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FakeServerSocket extends ServerSocket {
    private int port;

    public FakeServerSocket(int port) throws IOException {
        this.port = port;
    }

    @Override
    public Socket accept() {
        return new Socket();
    }

    @Override
    public int getLocalPort() {
        return port;
    }
}
