
import com.carebears.InternetHttpHandler;
import com.carebears.Server;
import org.junit.Test;
import static org.junit.Assert.*;

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

        server.initialize();

        assertTrue(socket.started);
    }

    @Test
    public void itStopsTheServer() throws Exception {
        FakeServerSocket socket = new FakeServerSocket();
        Server server = new Server(socket);

        server.initialize();
        server.stopServer();

        assertEquals(false, socket.started);

    }
    
    @Test
    public void itRegistersAnHTTPHandler() throws Exception {
        FakeServerSocket socket = new FakeServerSocket();
        Server server = new Server(socket);

        assertTrue(server.getHTTPHandler() instanceof InternetHttpHandler);
    }

}
