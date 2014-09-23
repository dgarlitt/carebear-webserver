import com.carebears.CareBearHttpHandler;
import com.carebears.servlets.CareBearServlet;
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
        String stringResponse = handler.handle("GET / HTTP/1.0");
        assertEquals("HTTP/1.0 200", stringResponse);
    }

    @Test
    public void ItHandles404Request() throws Exception {
        String stringResponse = handler.handle("GET /foobar HTTP/1.0");
        assertEquals("HTTP/1.0 404", stringResponse);
    }

    @Test
    public void ItHandlesUnreconizedRequest() throws Exception {
        String stringResponse = handler.handle("POST / HTTP/1.0");
        assertEquals("HTTP/1.0 404", stringResponse);
    }

    @Test
    public void DocumentRootTest() throws Exception {
        handler.setDocumentRoot("/foo");
        assertEquals("/foo", handler.getDocumentRoot());
    }

    @Test
    public void ItAddsAServlet() throws Exception {
        FakeServlet fakeServlet = new FakeServlet();
        fakeServlet.setPath("/fake");

        handler.registerServlet(fakeServlet);

        CareBearServlet servlet = handler.getServletByPath("/fake");

        assertEquals(fakeServlet, servlet);
    }


}
