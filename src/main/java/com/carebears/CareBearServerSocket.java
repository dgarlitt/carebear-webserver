package com.carebears;


public interface CareBearServerSocket {

    public void start(CareBearHttpHandler handler, int port);

    public void stopSocket();
}
