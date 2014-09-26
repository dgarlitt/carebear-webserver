package com.carebears;


public interface CareBearServerSocket {

    public void start(CareBearHttpHandler handler);

    public void stopSocket();

}
