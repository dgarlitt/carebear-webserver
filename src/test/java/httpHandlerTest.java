import com.carebears.InternetHttpHandler;
import com.carebears.CareBearServlet;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeServlet;

import static org.junit.Assert.assertEquals;

public class httpHandlerTest {
    InternetHttpHandler handler;

    @Before
    public void setsUpHttpHandler() {
        handler = new InternetHttpHandler();
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
