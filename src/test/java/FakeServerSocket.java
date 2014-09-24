import com.carebears.CareBearHttpHandler;
import com.carebears.CareBearSocket;

public class FakeServerSocket implements CareBearSocket {
    public boolean started;

    @Override
    public void start(CareBearHttpHandler handler) {
        started = true;
    }
}
