import com.carebears.CareBearHttpHandler;
import com.carebears.CareBearServerSocket;
import com.carebears.Server;
import testoutput.fakes.FakeHttpHandler;
import com.carebears.InternetServerSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InternetSocketTest {
    CareBearHttpHandler handler;

    @Before
    public void setup() {
        handler = new FakeHttpHandler();
    }

    @Test
    public void StartsTheSocketAndCreatesWorkers() throws Exception
    {
        Thread thread;
        PrintWriter out = null;
        BufferedReader in = null;
        InetAddress host = InetAddress.getLocalHost();
        WorkerlessServerSocket server = new WorkerlessServerSocket(); // see inner class below
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
            assertTrue(server.workersWereCreated());

        }
        finally {
            in.close();
            out.close();
            thread.join();
            client.close();
            server.stopSocket();
        }

    }

    // Created inner class that overrides the worker thread creation
    // to avoid unwanted threads being spawned during tests
    private class WorkerlessServerSocket extends InternetServerSocket {
        private boolean workersCreated = false;

        @Override
        public void createWorkerThread(Socket socket) {
            PrintWriter out;
            BufferedReader in;
            String input;

            workersCreated = true;

            try {
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                input = in.readLine();
                out.println(input);
                out.flush();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean workersWereCreated() {
            return workersCreated;
        }

    }

}
