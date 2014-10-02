import com.carebears.InternetHttpHandler;
import com.carebears.CareBearServlet;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeInputStream;
import testoutput.fakes.FakeServlet;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class httpHandlerTest {
    InternetHttpHandler handler;
    ByteArrayOutputStream outputStream;

    private ByteArrayOutputStream getTestOutputStream() {
        outputStream = new ByteArrayOutputStream();
        return(outputStream);
    }

    @Before
    public void setsUpHttpHandler() {
        handler = new InternetHttpHandler();
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
    public void ItReturnsMethodNotAllowedForNonServletPaths() throws Exception {
        handler.handle(new FakeInputStream("POST /make_believe_file.mbf HTTP/1.1"), getTestOutputStream());
        assertEquals("HTTP/1.1 405\n", outputStream.toString());

        handler.handle(new FakeInputStream("PUT /make_believe_file.mbf HTTP/1.1"), getTestOutputStream());
        assertEquals("HTTP/1.1 405\n", outputStream.toString());

        handler.handle(new FakeInputStream("DELETE /make_believe_file.mbf HTTP/1.1"), getTestOutputStream());
        assertEquals("HTTP/1.1 405\n", outputStream.toString());

        handler.handle(new FakeInputStream("OPTIONS /make_believe_file.mbf HTTP/1.1"), getTestOutputStream());
        assertEquals("HTTP/1.1 405\n", outputStream.toString());

        handler.handle(new FakeInputStream("PATCH /make_believe_file.mbf HTTP/1.1"), getTestOutputStream());
        assertEquals("HTTP/1.1 404\n", outputStream.toString());

        handler.handle(new FakeInputStream("HEAD /make_believe_file.mbf HTTP/1.1"), getTestOutputStream());
        assertEquals("HTTP/1.1 405\n", outputStream.toString());

        handler.handle(new FakeInputStream("GET /make_believe_file.mbf HTTP/1.1"), getTestOutputStream());
        assertEquals("HTTP/1.1 404\n", outputStream.toString());
    }


}
