package com.carebears;

public interface CareBearClentSocket {
    public void close() throws java.io.IOException;

    public String getInput();

    public void sendOutput(String output);

}
