import com.carebears.CareBearSocket;
import com.carebears.InternetSocket;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

public class InternetSocketTest {
    @Test
    public void StartsTheSocket() throws Exception
    {
        InetAddress host = InetAddress.getLocalHost();
        final CareBearSocket socket = new InternetSocket();

        new Thread() {
            public void run() {
                socket.start();
            }
        }.start();

        java.net.Socket client = new java.net.Socket(host.getHostName(), 5000);

        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.println("Test\n");
            out.flush();

            assertEquals("Test", in.readLine());
        }
        finally {
            client.close();
        }

    }

}
