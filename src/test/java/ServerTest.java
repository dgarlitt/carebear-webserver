
import org.junit.Test;
import static org.junit.Assert.*;
import com.carebears.Server;

public class ServerTest {
    @Test
    public void itWillReturnThePortNumber() throws Exception {
        FakeServerSocket socket = new FakeServerSocket();
        Server server = new Server(socket);
        server.setPort(5000);
        assertEquals(5000, server.getPort());
    }

    @Test
    public void itStartsTheServer() throws Exception {
        FakeServerSocket socket = new FakeServerSocket();
        Server server = new Server(socket);

        server.start();

        assertTrue(socket.started);
    }

}
