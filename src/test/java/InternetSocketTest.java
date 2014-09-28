import com.carebears.CareBearHttpHandler;
import com.carebears.CareBearServerSocket;
import com.carebears.Server;
import testoutput.fakes.FakeHttpHandler;
import com.carebears.InternetServerSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

public class InternetSocketTest {
    CareBearHttpHandler handler;

    @Before
    public void setup() {
        handler = new FakeHttpHandler();
    }

    @Test
    public void StartsTheSocket() throws Exception
    {
        Thread thread;
        PrintWriter out = null;
        BufferedReader in = null;
        InetAddress host = InetAddress.getLocalHost();
        CareBearServerSocket server = new InternetServerSocket();
        Server.CONFIG.setHandler(new FakeHttpHandler());

        thread = new Thread() {
            public void run() {
                server.start(handler);
            }
        };

        thread.start();

        java.net.Socket client = new java.net.Socket(host.getHostName(), Server.CONFIG.getPort());

        try {
            out = new PrintWriter(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.println("Test");
            out.flush();

            assertEquals("Test", in.readLine());
        }
        finally {
            in.close();
            out.close();
            thread.join();
            client.close();
            server.stopSocket();
        }

    }

}
