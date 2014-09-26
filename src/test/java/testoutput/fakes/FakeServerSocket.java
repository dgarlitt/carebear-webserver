package testoutput.fakes;

import com.carebears.CareBearHttpHandler;
import com.carebears.CareBearServerSocket;

public class FakeServerSocket implements CareBearServerSocket {
    public boolean started;
    private int port;

    @Override
    public void start(CareBearHttpHandler handler) {
        started = true;
    }

    public void stopSocket() {
        started = false;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
