import org.junit.Test;
import static org.junit.Assert.*;
import com.carebears.*;

import com.carebears.FakeServerSocket;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

    @Test
    public void itWillReturnThePortNumber() throws Exception {
        ServerSocket socket1 = new FakeServerSocket(5000);
        int port1 = socket1.getLocalPort();

        assertEquals(5000, port1);

        ServerSocket socket2 = new FakeServerSocket(5100);
        int port2 = socket2.getLocalPort();

        assertEquals(5100, port2);
    }

    @Test
    public void itStartsTheServer() throws Exception {
        ServerSocket socket = new FakeServerSocket(5000);
        Server server = new Server(socket);

        server.start();

        Socket returnSock = server.getSocket();

        assertTrue(returnSock instanceof Socket);
    }

}
