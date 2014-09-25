import com.carebears.CareBearHttpHandler;
import com.carebears.CareBearServerSocket;

public class FakeServerSocket implements CareBearServerSocket {
    public boolean started;

    @Override
    public void start(CareBearHttpHandler handler, int port) {
        started = true;
    }

    public void stopSocket() {
        started = false;
    }
}
