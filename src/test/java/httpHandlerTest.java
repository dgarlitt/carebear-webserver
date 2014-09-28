import com.carebears.InternetHttpHandler;
import com.carebears.CareBearServlet;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class httpHandlerTest {
    InternetHttpHandler handler;
    StringWriter sw;

    @Before
    public void setsUpHttpHandler() {
        handler = new InternetHttpHandler();
    }

    private PrintWriter getWriter() {
        sw = null;

        sw = new StringWriter();
        return new PrintWriter(sw);
    }

    @Test
    public void ItAddsAServlet() throws Exception {
        FakeServlet fakeServlet = new FakeServlet();
        fakeServlet.setPath("/fake");

        handler.registerServlet(fakeServlet);

        CareBearServlet servlet = handler.getServletByPath("/fake");

        assertEquals(fakeServlet, servlet);
    }

    @Test
    public void ItReturnsMethodNotAllowed() throws Exception {
        handler.handle("POST /make_believe_file.mbf HTTP/1.1", getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle("PUT /file.txt HTTP/1.1", getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle("DELETE /file.txt HTTP/1.1", getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle("OPTIONS /file.txt HTTP/1.1", getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle("HEAD /file.txt HTTP/1.1", getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle("GET /file.txt HTTP/1.1", getWriter());
        assertEquals("HTTP/1.1 404\n", sw.getBuffer().toString());
    }


}
