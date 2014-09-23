
import org.junit.Test;
import static org.junit.Assert.*;
import com.carebears.Server;

public class ServerTest {


    @Test
    public void itWillReturnThePortNumber() throws Exception {
//        ServerSocket socket1 = new java.FakeServerSocket();
//        int port1 = socket1.getLocalPort();
//
//        assertEquals(5000, port1);
//
//        ServerSocket socket2 = new java.FakeServerSocket();
//        int port2 = socket2.getLocalPort();
//
//        assertEquals(5100, port2);
    }

    @Test
    public void itStartsTheServer() throws Exception {
        FakeServerSocket socket = new FakeServerSocket();
        Server server = new Server(socket);

        server.start();

        assertTrue(socket.started);
    }

}
