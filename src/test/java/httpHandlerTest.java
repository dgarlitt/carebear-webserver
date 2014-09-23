import com.carebears.CareBearHttpHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class httpHandlerTest {
    CareBearHttpHandler handler;

    @Before
    public void setsUpHttpHandler() {
        handler = new CareBearHttpHandler();
    }
    @Test
    public void ItHandlesRequest() throws Exception {
        String stringResponse = handler.handle("GET /");
        assertEquals("HTTP/1.0 200", stringResponse);
    }

    @Test
    public void ItHandles404Request() throws Exception {
        String stringResponse = handler.handle("GET /foobar");
        assertEquals("HTTP/1.0 404", stringResponse);
    }

    @Test
    public void ItHandlesUnreconizedRequest() throws Exception {
        String stringResponse = handler.handle("POST /");
        assertEquals("HTTP/1.0 404", stringResponse);
    }

    @Test
    public void DocumentRootTest() throws Exception {
        handler.setDocumentRoot("/foo");
        assertEquals("/foo", handler.getDocumentRoot());
    }

}
