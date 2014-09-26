
import com.carebears.CareBearConfig;
import com.carebears.InternetHttpHandler;
import com.carebears.Server;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeHttpHandler;
import testoutput.fakes.FakeServerSocket;

import static org.junit.Assert.*;

public class ConfigTest {
    CareBearConfig config;

    @Before
    public void setup() {
        config = new CareBearConfig();
    }

    @Test
    public void SetAndGetHandler() throws Exception {
        FakeHttpHandler handler = new FakeHttpHandler();
        config.setHandler(handler);
        assertEquals(handler, config.getHandler());
    }

    @Test
    public void SetAndGetServerSocket() throws Exception {
        FakeServerSocket sock = new FakeServerSocket();
        config.setServerSocket(sock);
        assertEquals(sock, config.getServerSocket());
    }

    @Test
    public void SetAndGetPort() throws Exception {
        assertEquals(5000, config.getPort());
        config.setPort(2000);
        assertEquals(2000, config.getPort());
    }

    @Test
    public void SetAndGetDocumentRoot() throws Exception {
        assertEquals("/", config.getDocumentRoot());
        config.setDocumentRoot("/blah");
        assertEquals("/blah", config.getDocumentRoot());
    }
}
