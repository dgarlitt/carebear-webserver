package com.carebears;

import com.carebears.CareBearSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FakeServerSocket implements CareBearSocket {
    public boolean started;

    @Override
    public void start() {
        started = true;
    }
}
