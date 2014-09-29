import com.carebears.InternetHttpHandler;
import com.carebears.CareBearServlet;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeServlet;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class httpHandlerTest {
    InternetHttpHandler handler;
    StringWriter sw;

    private StringReader sr;
    private BufferedReader getReader(String input) {
        sr = null;
        sr = new StringReader(input);
        return new BufferedReader(sr);

    }

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
        handler.handle(getReader("POST /make_believe_file.mbf HTTP/1.1"), getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle(getReader("PUT /file.txt HTTP/1.1"), getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle(getReader("DELETE /file.txt HTTP/1.1"), getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle(getReader("OPTIONS /file.txt HTTP/1.1"), getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle(getReader("HEAD /file.txt HTTP/1.1"), getWriter());
        assertEquals("HTTP/1.1 405\n", sw.getBuffer().toString());

        handler.handle(getReader("GET /file.txt HTTP/1.1"), getWriter());
        assertEquals("HTTP/1.1 404\n", sw.getBuffer().toString());
    }


}
